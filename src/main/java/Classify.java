import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
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

//scores one cross-validation fold: mine rules on the training context, then let the fired rules vote on each patient in the test context
public class Classify {
    public static void main(String[] args) throws Exception {
        String trainCsv = args[0];
        String testCsv = args[1];
        String targetsPath = args[2];
        int minSupport = args.length > 3 ? Integer.parseInt(args[3]) : 30;
        double minConfidence = args.length > 4 ? Double.parseDouble(args[4]) : 0.70;
        String outCsv = args.length > 5 ? args[5] : "fca_pred.csv";

        Map<String, Integer> labelOf = readMap(targetsPath);

        //build the training lattice and mine its rules
        ISetFactory factory = new BitSetFactory();
        IBinaryContext ctx = MyCSVReader.read(new File(trainCsv), ';', factory);
        Lattice_AddIntent algo = new Lattice_AddIntent(ctx);
        algo.run();
        List<Rule> rules = prune(mineRules(algo.getResult(), ctx, labelOf, minSupport, minConfidence));

        //prior probability of disease among the training patients
        int trainDiseased = 0;
        for (int o = 0; o < ctx.getObjectCount(); o++) trainDiseased += labelOf.get(ctx.getObjectName(o));
        double prior = (double) trainDiseased / ctx.getObjectCount();
        int fallback = prior >= 0.5 ? 1 : 0;

        //score every patient in the test context
        BufferedReader reader = new BufferedReader(new FileReader(testCsv));
        String[] header = reader.readLine().split(";", -1);
        PrintWriter out = new PrintWriter(new FileWriter(outCsv));
        out.println("patient,score,predicted");
        String line;
        while ((line = reader.readLine()) != null) {
            String[] cells = line.split(";", -1);
            Set<String> attrs = new LinkedHashSet<>();
            for (int i = 1; i < cells.length; i++) {
                if (cells[i].equals("X")) attrs.add(header[i]);
            }

            //rules that fire for this patient
            List<Rule> fired = new ArrayList<>();
            for (Rule r : rules) {
                if (attrs.containsAll(r.premise)) fired.add(r);
            }

            //only the most general fired rules vote, so a rule and its extension do not both count
            double diseaseVote = 0, healthyVote = 0;
            for (Rule r : fired) {
                if (hasMoreGeneral(fired, r)) continue;
                if (r.outcome == 1) diseaseVote += r.confidence;
                else healthyVote += r.confidence;
            }

            double score;
            int predicted;
            if (diseaseVote == 0 && healthyVote == 0) {
                score = prior;
                predicted = fallback;
            } else {
                score = diseaseVote / (diseaseVote + healthyVote);
                predicted = diseaseVote >= healthyVote ? 1 : 0;
            }
            out.printf("%s,%.6f,%d%n", cells[0], score, predicted);
        }
        reader.close();
        out.close();
    }

    //true if another fired rule with the same outcome has a strictly smaller premise contained in this one
    static boolean hasMoreGeneral(List<Rule> fired, Rule r) {
        for (Rule other : fired) {
            if (other.outcome == r.outcome
                    && other.premise.size() < r.premise.size()
                    && r.premise.containsAll(other.premise)) {
                return true;
            }
        }
        return false;
    }

    //one classification rule: premise attributes imply an outcome (1 disease, 0 healthy) with a confidence
    static class Rule {
        final Set<String> premise;
        final int outcome;
        final double confidence;

        Rule(Set<String> premise, int outcome, double confidence) {
            this.premise = premise;
            this.outcome = outcome;
            this.confidence = confidence;
        }
    }

    //turn each concept into a rule: intent as premise, extent's majority label as outcome, filtered by support and confidence
    static List<Rule> mineRules(IConceptOrder order, IBinaryContext ctx, Map<String, Integer> labelOf,
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
                diseased += labelOf.get(ctx.getObjectName(it.next()));
            }
            double diseaseConfidence = (double) diseased / support;
            int outcome = diseaseConfidence >= 0.5 ? 1 : 0;
            double confidence = Math.max(diseaseConfidence, 1 - diseaseConfidence);
            if (confidence < minConfidence) continue;
            Set<String> premise = new LinkedHashSet<>();
            for (Iterator<Integer> it = intent.iterator(); it.hasNext(); ) {
                premise.add(ctx.getAttributeName(it.next()));
            }
            rules.add(new Rule(premise, outcome, confidence));
        }
        return rules;
    }

    //keep a rule only when no more general rule reaches the same outcome at least as confidently
    static List<Rule> prune(List<Rule> rules) {
        List<Rule> ordered = new ArrayList<>(rules);
        ordered.sort(Comparator.comparingInt(r -> r.premise.size()));
        List<Rule> kept = new ArrayList<>();
        for (Rule r : ordered) {
            boolean redundant = false;
            for (Rule k : kept) {
                if (k.outcome == r.outcome && r.premise.containsAll(k.premise) && k.confidence >= r.confidence) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) kept.add(r);
        }
        return kept;
    }

    //read a key;value file into a map of patient id to integer
    static Map<String, Integer> readMap(String path) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            map.put(parts[0], Integer.parseInt(parts[1]));
        }
        reader.close();
        return map;
    }
}
