package salesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Ant implements Callable<Ant> {
    public static final double Q = 0.005; //parameter used for adjusting the amount of pheromone deposited. The value between 0 and 1
    public static final double EVAPORATION = 0.2; //parameter used for varying the level of pheromone evaporation. The value between 0 and 1
    public static final double ALPHA = 0.01; //parameter used for controlling the importance of the pheromone trail. The value is bigger than 0
    public static final double BETA = 9.5; //parameter used for controlling the importance of the distance between source and destination. The value is not less than 1
    private AntColonyOptimization aco;
    private int antNum;
    private int iterNum;
    private Route route = null;
    static int invalidCityIndex = -1;
    static int numbOfCitites = Driver.initialRoute.size();

    public Route getRoute(){return route;};

    public Ant(AntColonyOptimization aco, int antNum, int iterNum) {
        this.aco = aco;
        this.antNum = antNum;
        this.iterNum = iterNum;
    }

    public Ant call() throws Exception {
        int originatingCityIndex = ThreadLocalRandom.current().nextInt(numbOfCitites);
        ArrayList<City> routeCities = new ArrayList<City>(numbOfCitites);
        HashMap<Integer, Boolean> visitedCities = new HashMap<Integer, Boolean>(numbOfCitites);
        IntStream.range(0, numbOfCitites).forEach(x -> visitedCities.put(Driver.initialRoute.get(x).getId(), false));
        int numbOfVisitedCities = 0;
        visitedCities.put(Driver.initialRoute.get(originatingCityIndex).getId(), true);
        double routeDistance = 0.0;
        int actualCityIndex = originatingCityIndex;
        int nextCityIndex = invalidCityIndex;
        if(numbOfVisitedCities != numbOfCitites) nextCityIndex = getNextCityToVisit(actualCityIndex, visitedCities);
        while(nextCityIndex != invalidCityIndex){
            //-------------------------------------------------------------------------------------------- konstrukcja add?
            routeCities.add(numbOfVisitedCities++, Driver.initialRoute.get(actualCityIndex));
            routeDistance += aco.getDistancesMatrix()[actualCityIndex][nextCityIndex];
            adjustPheromoneLevel(actualCityIndex, nextCityIndex, routeDistance);
            visitedCities.put(Driver.initialRoute.get(nextCityIndex).getId(), true);
            actualCityIndex = nextCityIndex;
            if (numbOfVisitedCities != numbOfCitites) nextCityIndex = getNextCityToVisit(actualCityIndex, visitedCities);
            else nextCityIndex = invalidCityIndex;
        }//while
        routeDistance += aco.getDistancesMatrix()[actualCityIndex][originatingCityIndex];
        routeCities.add(numbOfVisitedCities, Driver.initialRoute.get(actualCityIndex));
        route = new Route(routeCities, routeDistance);
        return this;
    }

    private void adjustPheromoneLevel(int actualCityIndex, int nextCityIndex, double routeDistance) {
        boolean flag = false;
        while(!flag){
            double currentPheromoneLevel = aco.getPheromoneLevelsMatrix()[actualCityIndex][nextCityIndex].doubleValue();
            double updatedPheromoneLevel = (1-EVAPORATION) * currentPheromoneLevel + Q/routeDistance;
            if (updatedPheromoneLevel < 0.00) flag = aco.getPheromoneLevelsMatrix()[actualCityIndex][nextCityIndex].compareAndSet(0);
            else flag = aco.getPheromoneLevelsMatrix()[actualCityIndex][nextCityIndex].compareAndSet(updatedPheromoneLevel);
        }//while
    }

    private int getNextCityToVisit(int actualCity, HashMap<Integer, Boolean> visitedCities) {
        int nextCity = invalidCityIndex;
        double random = ThreadLocalRandom.current().nextDouble();
        ArrayList<Double> transitionProbabilities = getTransitionProbabilities(actualCity, visitedCities);
        for (int y=0; y< numbOfCitites; y++){
            if(transitionProbabilities.get(y) > random){
                nextCity = y;
                break;
            } else random -= transitionProbabilities.get(y);
        }//for y
        return nextCity;
    }

    private ArrayList<Double> getTransitionProbabilities(int actualCity, HashMap<Integer, Boolean> visitedCities) {
        ArrayList<Double> transitionProbabilities = new ArrayList<Double>(numbOfCitites);
        IntStream.range(0, numbOfCitites).forEach(i -> transitionProbabilities.add(0.0));
        double denominator = getTPDenominator(transitionProbabilities, actualCity, visitedCities);
        IntStream.range(0, numbOfCitites).forEach(y -> transitionProbabilities.set(y, transitionProbabilities.get(y)/denominator));
        return transitionProbabilities;
    }

    private double getTPDenominator(ArrayList<Double> transitionProbabilities, int actualCity, HashMap<Integer, Boolean> visitedCities){
        double denominator = 0.0;
            for(int y=0; y<numbOfCitites; y++){
                if(!visitedCities.get(Driver.initialRoute.get(y).getId())){
                    if (actualCity == y) transitionProbabilities.set(y, 0.0);
                    else transitionProbabilities.set(y, getTPNumerator(actualCity, y));
                    denominator += transitionProbabilities.get(y);
                }//if
            }//for y
        return denominator;
    }

    private double getTPNumerator(int actualCity, int nextCity){
        double numerator = 0.0;
        double pheromoneLevel = aco.getPheromoneLevelsMatrix()[actualCity][nextCity].doubleValue();
        if(pheromoneLevel != 0.0) numerator = Math.pow(pheromoneLevel, ALPHA) * Math.pow(1/aco.getDistancesMatrix()[actualCity][nextCity], BETA);
        return numerator;
    }

    public int getAntNum() {
        return antNum;
    }

    public int getIterNum() {
        return iterNum;
    }
}
