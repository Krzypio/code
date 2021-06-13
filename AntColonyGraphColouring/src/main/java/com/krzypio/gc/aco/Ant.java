package com.krzypio.gc.aco;

public class Ant {
    protected int trailSize;
    protected int trail[];
    protected boolean visited[];

    public Ant(int vertexSize) {
        this.trailSize = vertexSize;
        this.trail = new int[vertexSize];
        this.visited = new boolean[vertexSize];
    }

    protected void visit(int currentIndex, int nextIndex){
        trail[currentIndex + 1] = nextIndex; //add to trail
        visited[nextIndex] = true; //update flag
    }

    protected boolean visited(int i){
        return visited[i];
    }

    protected double getTrailLength(double graph[][]){
        double length = graph[trail[trailSize-1]][trail[0]];
        for (int i=0; i<trailSize-1; i++){
            length += graph[trail[i]][trail[i+1]];
        }//for
        return length;
    }

    protected void clear()
    {
        for (int i = 0; i < trailSize; i++)
            visited[i] = false;
    }
}
