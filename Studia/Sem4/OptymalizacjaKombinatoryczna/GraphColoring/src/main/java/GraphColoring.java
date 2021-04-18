import java.io.IOException;

public class GraphColoring {
    public static void main(String[] args) throws IOException {

        //load graph
        //ReadWrite rw = new ReadWrite("test.txt");
        ReadWrite rw = new ReadWrite("queen6.txt");
        Graph g1 = new Graph(rw.read());
        System.out.println(g1);
        AlgorithmGreedy a1 = new AlgorithmGreedy(g1);
        //algorytm zachłanny
                System.out.println("---------------------------------");
        System.out.println("Najwyzszy kolor " + a1.getHighestUsedColor() + ", liczba kolorow " + (a1.getHighestUsedColor()+1));
        for ( int value: a1.getCtVertexColors()) {
            System.out.print(" " + value);
        }//for value
    }
}
