import java.io.IOException;

/**
 * Implementation of greedy algorithm for colouring graph. Fast run 8h. To obtain right results:
 * - the generated graph must be saved to a file
 * - load graph from file
 * - use graph as argument for AlgorithmGreedy constructor
 */
public class GraphColoring {
    public static void main(String[] args) throws IOException {

        //gen graph
        Generator genGraph = new Generator(100, 50);
        //create file name
        StringBuilder newFileName = new StringBuilder();
        newFileName.append("GC15_");
        newFileName.append("size"+String.valueOf(genGraph.getSize())+"_sat"+String.valueOf(genGraph.getSaturation()));
        newFileName.append(".txt");
        //save file
        ReadWrite rw = new ReadWrite(newFileName.toString());

        //read file
        rw.write(genGraph.toString());
        //solve graph
        Graph graph = new Graph(rw.read());
        AlgorithmGreedy greedy = new AlgorithmGreedy(graph);
        System.out.println("Uzyto " + (greedy.getHighestUsedColor()+1) + " kolorów:");
        System.out.println(greedy.toStringColoredVertexes());
    }
}
