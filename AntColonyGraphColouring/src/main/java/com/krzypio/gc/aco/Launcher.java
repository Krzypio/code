package com.krzypio.gc.aco;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        //read file
        ReadWrite fileRW = new ReadWrite("queen6.txt");

        //load file to graph
        Graph graph = new Graph(fileRW.read());

        AntColonyOptimization aco = new AntColonyOptimization(graph.getAdjacencyMatrix());
        aco.printGraph();
    }
}
