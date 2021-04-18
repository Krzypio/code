import java.util.*;

public class Generator extends Graph {
    private Random random;
    private int saturation;
    private int counter;
    
    private int arcTargetNo;
    private int arcActualNo = 0;
    
    public Generator(int size, int saturation) {
        super(size);
        this.counter = 0;
        initializeGenerator(saturation);
        connectVertexes();
        addEdgesToSaturation();

        int maxEdges = getSize()*(getSize()-1)/2;
        saturation = arcActualNo*100/maxEdges;
        //System.out.println("target: " + arcTargetNo + " actual: " + arcActualNo + " sat: "+saturation);
    }

    private void addEdgesToSaturation() {
        while(arcActualNo<arcTargetNo){
            int firstVertex = random.nextInt(size);
            List<Integer> unconnectedVertexes = new ArrayList<>();
            for(int i=0; i<size; i++){
                if (adjacencyMatrix[firstVertex][i] == 0 && adjacencyMatrix[i][firstVertex] == 0)
                    unconnectedVertexes.add(i);
            }
            //if we can add edge
            if (unconnectedVertexes.size()>0){
                //add to matrix
                Collections.shuffle(unconnectedVertexes);
                int secondVertex = unconnectedVertexes.get(0);
                if(firstVertex<secondVertex){
                    adjacencyMatrix[firstVertex][secondVertex] = 1;
                    arcActualNo++;
                } else if(firstVertex>secondVertex){
                    adjacencyMatrix[secondVertex][firstVertex] = 1;
                    arcActualNo++;
                }//else
            }
        }//while
    }

    private void connectVertexes() {
        List<Integer> vertexes = new ArrayList<>();
        //generate 1,2...size-1
        for (int i=0; i<getSize(); i++){
            vertexes.add(i);
        }//for
        //Collections.shuffle(vertexes);

        //create connection between n and n+1
        for(int i=1; i<getSize(); i++){
            int vertexA = vertexes.get(i-1);
            int vertexB = vertexes.get(i);
            if (vertexA<vertexB)
                adjacencyMatrix[vertexA][vertexB] = 1;
            else
                adjacencyMatrix[vertexB][vertexA] = 1;
            arcActualNo++;
        }
    }

    private void initializeGenerator(int saturation){
        random = new Random();
        if(saturation>100) saturation = 100;
        this.saturation = saturation;
        //Ile jedynek dla danego saturation
        float tmp = (getSize()*(getSize()-1))*(float)saturation/100/2; ///2 for edges undirected
        arcTargetNo = (int) tmp;
        //minimum by polaczyc caly graf
        if (arcTargetNo<getSize()-1) arcTargetNo = getSize()-1;
    }//initializeGenerator()

    public int getSaturation() {
        return saturation;
    }
}
