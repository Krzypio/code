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
            IntStream.range(0, citiesSize).forEach(y -> pheromoneLevelsMatrix[x][y] = new AtomicDouble(random.nextDouble()));
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
}
