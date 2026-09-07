import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.lirmm.fca4j.algo.Lattice_AddIntent;
import fr.lirmm.fca4j.cli.io.MyCSVReader;
import fr.lirmm.fca4j.core.IBinaryContext;
import fr.lirmm.fca4j.core.IConceptOrder;
import fr.lirmm.fca4j.iset.ISet;
import fr.lirmm.fca4j.iset.ISetFactory;
import fr.lirmm.fca4j.iset.std.BitSetFactory;

public class Lattice {
    public static void main(String[] args) throws Exception {
        String csv = args.length > 0 ? args[0] : "context_clinical.csv";
        int minSupport = args.length > 1 ? Integer.parseInt(args[1]) : 0;
        double minConfidence = args.length > 2 ? Double.parseDouble(args[2]) : 0.0;
        String targetsPath = args.length > 3 ? args[3] : "targets.txt";

        ISetFactory factory = new BitSetFactory();
        IBinaryContext ctx = MyCSVReader.read(new File(csv), ';', factory);

        //full concept lattice
        Lattice_AddIntent latticeAlgo = new Lattice_AddIntent(ctx);
        latticeAlgo.run();
        IConceptOrder lattice = latticeAlgo.getResult();

        System.out.printf("context: %s  (%d patients x %d attributes)%n%n",
                csv, ctx.getObjectCount(), ctx.getAttributeCount());

        //structural metrics: full lattice
        int[] depth = depthsFromTop(lattice);
        int height = 0;
        Map<Integer, Integer> levelSize = new HashMap<>();
        for (int c : lattice.getConcepts()) {
            height = Math.max(height, depth[c]);
            levelSize.merge(depth[c], 1, Integer::sum);
        }
        int widest = 0;
        for (int n : levelSize.values()) widest = Math.max(widest, n);
        System.out.printf("full lattice: %d concepts, %d edges, height %d, widest level %d%n%n",
                lattice.getConceptCount(), lattice.getEdgeCount(), height, widest);

        //diagnostic association rules from the full lattice
        Map<String, Integer> targetOf = readTargets(targetsPath);
        int[] label = new int[ctx.getObjectCount()];
        for (int o = 0; o < ctx.getObjectCount(); o++) label[o] = targetOf.get(ctx.getObjectName(o));

        //mine, filter and prune rules from the lattice
        List<Rule> latticeRules = prune(mineRules(lattice, ctx, label, minSupport, minConfidence));

        System.out.printf("pruned diagnostic rules from FULL LATTICE (support >= %d, confidence >= %.2f):%n",
                minSupport, minConfidence);
        printRules(latticeRules);
        System.out.printf("%ntotal pruned rules: %d%n", latticeRules.size());
    }

    //one diagnostic rule: a premise set implying an outcome, with support and confidence
    static class Rule {
        final Set<String> premise;
        final String outcome;
        final int support;
        final double confidence;

        Rule(Set<String> premise, String outcome, int support, double confidence) {
            this.premise = premise;
            this.outcome = outcome;
            this.support = support;
            this.confidence = confidence;
        }
    }

    //keep a rule only when no more general rule reaches the same outcome at least as confidently
    static List<Rule> prune(List<Rule> rules) {
        List<Rule> ordered = new ArrayList<>(rules);
        ordered.sort(Comparator.comparingInt(r -> r.premise.size()));
        List<Rule> kept = new ArrayList<>();
        for (Rule r : ordered) {
            boolean redundant = false;
            for (Rule k : kept) {
                if (k.outcome.equals(r.outcome) && r.premise.containsAll(k.premise) && k.confidence >= r.confidence) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) kept.add(r);
        }
        return kept;
    }

    //mine diagnostic rules from the full lattice
    static List<Rule> mineRules(IConceptOrder order, IBinaryContext ctx, int[] label,
                                int minSupport, double minConfidence) {
        List<Rule> rules = new ArrayList<>();
        for (int c : order.getConcepts()) {
            ISet intent = order.getConceptIntent(c);
            if (intent.isEmpty()) continue;
            ISet extent = order.getConceptExtent(c);
            int support = extent.cardinality();
            if (support < minSupport) continue;
            int diseased = 0;
            for (Iterator<Integer> it = extent.iterator(); it.hasNext(); ) {
                if (label[it.next()] == 1) diseased++;
            }
            double diseaseConfidence = (double) diseased / support;
            String outcome = diseaseConfidence >= 0.5 ? "disease" : "healthy";
            double confidence = Math.max(diseaseConfidence, 1 - diseaseConfidence);
            if (confidence < minConfidence) continue;
            Set<String> premise = new LinkedHashSet<>();
            for (Iterator<Integer> it = intent.iterator(); it.hasNext(); ) {
                premise.add(ctx.getAttributeName(it.next()));
            }
            rules.add(new Rule(premise, outcome, support, confidence));
        }
        return rules;
    }

    static void printRules(List<Rule> rules) {
        for (Rule r : rules) {
            System.out.printf("  %s -> %s  (support %d, confidence %.2f)%n",
                    String.join(", ", r.premise), r.outcome, r.support, r.confidence);
        }
        if (rules.isEmpty()) System.out.println("  (none at this support/confidence)");
    }

    //shortest-edge depth of every concept from the top
    static int[] depthsFromTop(IConceptOrder order) {
        int maxId = 0;
        for (int c : order.getConcepts()) maxId = Math.max(maxId, c);
        int[] depth = new int[maxId + 1];
        Arrays.fill(depth, -1);
        int top = order.getTop();
        depth[top] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(top);
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (Iterator<Integer> it = order.getLowerCover(c).iterator(); it.hasNext(); ) {
                int lower = it.next();
                if (depth[lower] < 0) {
                    depth[lower] = depth[c] + 1;
                    queue.add(lower);
                }
            }
        }
        return depth;
    }

    //patient id -> outcome (0 healthy, 1 disease)
    static Map<String, Integer> readTargets(String path) throws Exception {
        Map<String, Integer> targetOf = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            targetOf.put(parts[0], Integer.parseInt(parts[1]));
        }
        reader.close();
        return targetOf;
    }

}