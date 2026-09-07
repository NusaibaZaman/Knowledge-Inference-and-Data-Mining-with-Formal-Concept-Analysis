import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lirmm.fca4j.algo.Lattice_AddIntent;
import fr.lirmm.fca4j.cli.io.MyCSVReader;
import fr.lirmm.fca4j.core.IBinaryContext;
import fr.lirmm.fca4j.core.IConceptOrder;
import fr.lirmm.fca4j.iset.ISet;
import fr.lirmm.fca4j.iset.ISetFactory;
import fr.lirmm.fca4j.iset.std.BitSetFactory;

//builds the concept lattice of a small worked-example context and writes a readable Graphviz DOT (Figure 3.2)
public class ExampleLattice {
    public static void main(String[] args) throws Exception {
        String csv = args.length > 0 ? args[0] : "context_example.csv";
        String dot = args.length > 1 ? args[1] : "example_lattice.dot";

        ISetFactory factory = new BitSetFactory();
        IBinaryContext ctx = MyCSVReader.read(new File(csv), ';', factory);
        Lattice_AddIntent algo = new Lattice_AddIntent(ctx);
        algo.run();
        IConceptOrder lattice = algo.getResult();

        System.out.printf("%d objects x %d attributes -> %d concepts, %d edges%n",
                ctx.getObjectCount(), ctx.getAttributeCount(),
                lattice.getConceptCount(), lattice.getEdgeCount());

        PrintWriter out = new PrintWriter(new FileWriter(dot));
        out.println("digraph Lattice {");
        out.println("rankdir=TB;");
        out.println("node [shape=record, style=filled, fillcolor=\"#dbe5f1\", fontname=\"Helvetica\", fontsize=11];");
        out.println("edge [dir=none, color=\"#555555\"];");

        //one node per concept: full intent on top, full extent on the bottom
        for (int c : lattice.getConcepts()) {
            String intent = String.join(", ", names(lattice.getConceptIntent(c), true, ctx));
            String extent = String.join(", ", names(lattice.getConceptExtent(c), false, ctx));
            String label = intent.isEmpty() ? extent : "{" + intent + "|" + extent + "}";
            out.printf("c%d [label=\"%s\"];%n", c, label);
        }

        //one edge from each concept to the concept just below it
        for (int c : lattice.getConcepts()) {
            for (Iterator<Integer> it = lattice.getLowerCover(c).iterator(); it.hasNext(); ) {
                out.printf("c%d -> c%d;%n", c, it.next());
            }
        }

        out.println("}");
        out.close();
    }

    //attribute or object names on one side of a concept
    static List<String> names(ISet set, boolean attribute, IBinaryContext ctx) {
        List<String> list = new ArrayList<>();
        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
            int i = it.next();
            list.add(attribute ? ctx.getAttributeName(i) : ctx.getObjectName(i));
        }
        return list;
    }
}
