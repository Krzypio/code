import java.util.Arrays;
import java.util.List;

public class Graph {
    protected int size;
    protected int[][] adjacencyMatrix;

    protected Graph(int size){
        this.size = size;
        adjacencyMatrix = new int [size][size];
        clearGraph();
    }

    public Graph(String instanceString){
        List<String> rows = Arrays.asList(instanceString.split("[\\r\\n]+"));
        size = Integer.parseInt(rows.get(0));

        //initializeAdjacencyMatrix
        adjacencyMatrix = new int [size][size];
        clearGraph();

        //from String to graph
        for (int i=1; i<rows.size(); i++) {
            List<String> vertexes = Arrays.asList(rows.get(i).split(" "));
            int vertexA = Integer.parseInt(vertexes.get(0))-1;  //numeration of vertexes start on 1
            int vertexB = Integer.parseInt(vertexes.get(1))-1;  //numeration of vertexes start on 1
            setEdge(vertexA, vertexB, 1);
        }//for
    }

    public void clearGraph(){
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                adjacencyMatrix[i][j] = 0;
            }//j
        }//i
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(size + "\n");

        for (int i=0; i<size; i++){
            for (int j=i; j<size; j++){
                if (adjacencyMatrix[i][j] == 1)
                    sb.append((i+1) + " " + (j+1) + "\n");
            }//j
        }//i
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    protected void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    private void setEdge(int v1, int v2, int value){
        adjacencyMatrix[v1][v2] = value;
        adjacencyMatrix[v2][v1] = value;
    }
}
