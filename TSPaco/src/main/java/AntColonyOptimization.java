import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AntColonyOptimization {
    private int nTowns;
    private double[][] distances;   //the length of the path between towns i and j
    private double[][] visibility;  //the quantity 1/(distance between i and j towns)
    private int[][][] antsAmountTownCycleTime;  //the number of ants in town i in cycle c ant time t (for each cycle time starts from 0)
    private double[][][] intensityTrailTownTownCycle; //intensity of trail on edge (i,j) at cycle c
    private List<Town> towns;
    int cCycles;
    private double Q = 500;   //constant responsible for amount of distributed trail depend on length
    private double C = 0.01;   //small positive constraint to initialize intensityTrailMatrix.

    static final double EVAPORATION = 0.005;

    public AntColonyOptimization(List<Town> towns, int cCycles) {
        this.towns = towns;
        this.nTowns = towns.size();
        this.cCycles = cCycles;

        initializeDistances();
        initializeIntensityTrail();
        initializeVisibility();
    }

    private void initializeVisibility() {
        visibility = new double[nTowns][nTowns];
        for (int i=0; i<nTowns; i++){
            for(int j=0; j<nTowns; j++){
                visibility[i][j] = 1/distances[i][j];
            }//for j
        }//for i
    }

    private void initializeIntensityTrail() {
        intensityTrailTownTownCycle = new double[nTowns][nTowns][cCycles];
        for(int i=0; i<nTowns; i++){
            for(int j=0; j<nTowns; j++){
                for(int k=0; k<cCycles; k++){
                    intensityTrailTownTownCycle[i][j][k] = C;
                }//for k
            }//for j
        }//for i
    }

    private void initializeDistances(){
        distances = new double[nTowns][nTowns];
        IntStream.range(0, nTowns).forEach(x -> {
            Town townX = towns.get(x);
            IntStream.range(0, nTowns).forEach(y ->
                distances[x][y] = townX.measureDistance(towns.get(y))
            );//forY
        });//forX
    }

    public void updateIntensityTrail(int cycle, ArrayList<Ant> ants){
        for(int t1=0; t1<nTowns; t1++){
            for(int t2=0; t2<nTowns; t2++){
                double currentValue = 0;
                if (cycle > 0)
                    currentValue = intensityTrailTownTownCycle[t1][t2][cycle-1];

                double deltaValue=0;

                for(Ant ant : ants){
                    if (ant.getRoute().isEdge(towns.get(t1), towns.get(t2)) || ant.getRoute().isEdge(towns.get(t2), towns.get(t1)))
                        deltaValue += Q/ant.getRoute().getDistance();
                }//foreach

                double updateValue = (1-EVAPORATION) * currentValue + deltaValue;
                intensityTrailTownTownCycle[t1][t2][cycle] = updateValue;
            }//for j
        }//for i
    }

    public List<Town> getTowns() {
        return towns;
    }
}
