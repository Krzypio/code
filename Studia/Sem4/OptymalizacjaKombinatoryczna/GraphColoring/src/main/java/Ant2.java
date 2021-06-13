import java.io.IOException;

//create new ant
public class Ant2 {
    int[][] graph;
    int[] colors;
    //start?
    int[] visited;
    int[] unvisited;
    double alpha;
    double beta;
    int distance = 0; //number of used colors on valid solution
    int numberColisions=0; //only for consistency chceck, should be always 0
    int[] colorsAvaliable;
    int[] colorsAssigned;

    public static void main(String[] args) throws IOException {
        //read file
        ReadWrite fileRW = new ReadWrite("queen6.txt");

        //solve graph
        Graph g = new Graph(fileRW.read());
        initialize();
    }

    private static void initialize(Graph g) {
        int size = g.getSize();
        this.colors
    }
}
