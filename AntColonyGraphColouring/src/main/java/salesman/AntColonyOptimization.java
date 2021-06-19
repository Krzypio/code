package salesman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class AntColonyOptimization {
    private AtomicDouble[][] pheromoneLevelsMatrix = null;
    private double[][] distancesMatrix = null;
    private ArrayList<City> cities = Driver.initialRoute;
    private int citiesSize = Driver.initialRoute.size();

    public AntColonyOptimization(){
        initializeDistances();
        initializePheromoneLevels();
    }

    private void initializePheromoneLevels() {
        pheromoneLevelsMatrix = new AtomicDouble[citiesSize][citiesSize];
        Random random = new Random();
        IntStream.range(0,citiesSize).forEach(x -> {
            IntStream.range(0, citiesSize).forEach(y ->
                    //pheromoneLevelsMatrix[x][y] = new AtomicDouble(random.nextDouble())
                    pheromoneLevelsMatrix[x][y] = new AtomicDouble(1.0)
            );//foreach y
        });//forEach x
    }

    private void initializeDistances() {
        distancesMatrix = new double[citiesSize][citiesSize];
        IntStream.range(0, citiesSize).forEach(x -> {
            City cityY = cities.get(x);
            IntStream.range(0, citiesSize).forEach(y ->
                distancesMatrix[x][y] = cityY.measureDistance(cities.get(y)) );//forEach X
        });//for Y
    }

    public AtomicDouble[][] getPheromoneLevelsMatrix() {
        return pheromoneLevelsMatrix;
    }

    public double[][] getDistancesMatrix() {
        return distancesMatrix;
    }

    /*public void evaporate(double value) {
        IntStream.range(0, citiesSize).forEach(x -> {
            IntStream.range(0, citiesSize).forEach(y -> {
                double actualPheromoneLevel = pheromoneLevelsMatrix[x][y].doubleValue();
                if(actualPheromoneLevel>0){
                    double updatedPheromoneLevel = (1-value) * actualPheromoneLevel;
                    pheromoneLevelsMatrix[x][y].compareAndSet(updatedPheromoneLevel);
                }//if
            });
        });
    }*/
}
