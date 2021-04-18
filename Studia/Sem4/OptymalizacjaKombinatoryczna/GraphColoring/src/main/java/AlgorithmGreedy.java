public class AlgorithmGreedy {
    Graph graph;

    private int[] ctVertexColors;
    private boolean[] cUsedColors;
    int iC;
    int uVertex, vVertex;
    int highestUsedColor;

    int size;
    private int[][] adjacencyMatrix;

    public AlgorithmGreedy(Graph graph) {
        this.graph = graph;
        this.size = graph.getSize();
        adjacencyMatrix = graph.getAdjacencyMatrix();
        ctVertexColors = new int[graph.getSize()];
        cUsedColors = new boolean[graph.getSize()];
        this.highestUsedColor = -1;

        //set begin values
        for(int i=0; i<graph.getSize(); i++){
            ctVertexColors[i] = -1;
        }

        //start
        ctVertexColors[0] = 0;
        //for each vertex
        for(int v=1; v<size; v++){
            clearUsedColors();
            //for each adjacent
            for(int u=0; u<size; u++){
                if (adjacencyMatrix[v][u] > 0){
                    if(ctVertexColors[u]>-1)
                        cUsedColors[ctVertexColors[u]] = true;
                }//if adjacent exist
            }//u

            //szukamy najnizszego z dostepnych kolorow
            iC = 0;
            while (cUsedColors[iC]) iC++;

            //przypisujemy znaleziony kolor wierzcholkowi v
            ctVertexColors[v] = iC;

            //zapisz najwyzszy uzyty kolor
            if (iC > highestUsedColor) highestUsedColor = iC;
        }//v
    }

    public int[] getCtVertexColors() {
        return ctVertexColors;
    }

    public int getHighestUsedColor() {
        return highestUsedColor;
    }

    private void clearUsedColors(){
        for(int i=0; i<size; i++){
            cUsedColors[i] = false;
        }//for
    }
}
