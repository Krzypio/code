package com.krzypio.gc.aco;

import java.util.*;
import java.util.stream.IntStream;

public class AntColonyOptimization {

    private double c = 1.0; //original number of trails, at the start of the simulation
    private double alpha = 1; //pheromone importance coefficient
    private double beta = 5; //cost importance coefficient
    private double evaporation = 0.5; //how much pheromone evaporate ecery iteration
    private double Q = 500; //total amount of pheromone left on the trail by each Ant
    private double antFactor = 0.8; //how many ants we will use per vertex
    private double randomFactor = 0.01; //randomness in our simulation
    private int maxIterations = 100;

    private double graph[][]; //our adjacency graph
    private int numberOfVertexes;
    private int numberOfAnts;
    private double trails[][];
    private double probabilities[];
    private List<Ant> ants = new ArrayList<>();

    private Random random = new Random();
    private int currentIndex;

    private int[] bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimization(int[][] adjacencyMatrix) {
        //copy
        graph = new double[adjacencyMatrix.length][adjacencyMatrix[0].length];
        for (int i=0; i<adjacencyMatrix.length; i++){
            for (int j=0; j<adjacencyMatrix[0].length; j++) {
                graph[i][j] = (double) adjacencyMatrix[i][j];
            }//for j
        }//for i

        this.numberOfVertexes = this.graph.length;
        this.numberOfAnts = (int) (numberOfVertexes * antFactor);

        this.trails = new double[numberOfVertexes][numberOfVertexes];
        this.probabilities = new double[numberOfVertexes];

        IntStream.range(0, numberOfAnts).forEach(i -> ants.add(new Ant(numberOfVertexes)));

        setupAnts();
        run();
    }

    public void run() {
        IntStream.range(0, maxIterations).forEach(i -> {
            moveAnts();
            updateTrails();
            updateBest();
        });
    }//run()

    private void updateBest() {
        if (bestTourOrder == null) {
            bestTourOrder = ants.get(0).trail;
            bestTourLength = ants.get(0).getTrailLength(graph);
        }//if
        for (Ant a : ants) {
            if (a.getTrailLength(graph) < bestTourLength) {
                bestTourLength = a.getTrailLength(graph);
                bestTourOrder = a.trail.clone();
            }//if
        }//for ant
    }//updateBest()

    public void updateTrails() {
        for (int i = 0; i < numberOfVertexes; i++) {
            for (int j = 0; j < numberOfVertexes; j++) {
                trails[i][j] *= evaporation;
            }
        }
        for (Ant a : ants) {
            double contribution = Q / a.getTrailLength(graph);
            for (int i = 0; i < numberOfVertexes - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfVertexes - 1]][a.trail[0]] += contribution;
        }
    }

    public void moveAnts() {
        IntStream.range(currentIndex, numberOfVertexes - 1).forEach(i -> {
            ants.forEach(ant -> {
                ant.visit(currentIndex, selectNext(ant));
            });
            currentIndex++;
        });
    }

    private int selectNext(Ant ant) {
        int t = random.nextInt(numberOfVertexes - currentIndex);
        if (random.nextDouble() < randomFactor) {
            OptionalInt vectorIndex = IntStream.range(0, numberOfVertexes)
                    .filter(i -> i == t && !ant.visited(i))
                    .findFirst();
            if (vectorIndex.isPresent()) {
                return vectorIndex.getAsInt();
            }//if
        }//if
        calculateProbabilities(ant);
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfVertexes; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }//if
        }//for i
        throw new RuntimeException("There are no other cities");
    }//selectNext()

    public void calculateProbabilities(Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;
        for (int l = 0; l < numberOfVertexes; l++) {
            if (!ant.visited(l)){
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }//if
        }//for l
        for (int j = 0; j < numberOfVertexes; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probabilities[j] = numerator / pheromone;
            }//else
        }//for
    }//calculateProbabilities()

    public void setupAnts() {
        IntStream.range(0, numberOfAnts)
                .forEach(i -> {
                    ants.forEach(ant -> {
                        ant.clear();
                        ant.visit(-1, random.nextInt(numberOfVertexes));
                    });
                });
        this.currentIndex = 0;
    }

    public void printGraph() {
        for (double[] array : graph) {
            for (double value : array) {
                System.out.print(", " + Double.toString(value));
            }//for value
            System.out.print("\n");
        }//for array
    }//printGraph()
}
