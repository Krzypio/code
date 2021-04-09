package com.krzypio.GraphColoring.Graph;

import java.util.Comparator;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    private static long nextId = 0;
    private static long counter = 0;
    private long id;

    /***
     * Container for vertexes
     */
    public Vertex() {
        this.id = nextId;
        nextId++;
        counter++;
    }

    public Vertex(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    //Getters and setters
    public static long getNextId() {
        return nextId;
    }

    private static void setNextId(long nextId) {
        Vertex.nextId = nextId;
    }

    public static long getCounter() {
        return counter;
    }

    private static void setCounter(long counter) {
        Vertex.counter = counter;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return getId().equals(vertex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Vertex other) {
        return (int) (this.id - other.id);
    }
}
