package com.krzypio.GraphColoring.Graph;

import java.util.*;

public class GraphAdjacencyList {
    private static long nextId = 0;
    private static long counter = 0;
    private Long id;
    private Map<Vertex, List<Vertex>> adjVertices;

    /***
     * Create Graph based on adjacency list
     */
    public GraphAdjacencyList() {
        this.id = nextId;
        nextId++;
        counter++;
        adjVertices = new LinkedHashMap<>();
    }

    /***
     * Create Graph based on adjacency list
     * @param adjVertices adjacency vertices list
     */
    public GraphAdjacencyList(Map<Vertex, List<Vertex>> adjVertices) {
        this();
        this.adjVertices = adjVertices;
    }

    public void addVertex(){
        adjVertices.putIfAbsent(new Vertex(), new ArrayList<>());
    }

    public void addVertex(long id){
        adjVertices.putIfAbsent(new Vertex(id), new ArrayList<>());
    }

    public void removeVertex(long id) {
        Vertex v = new Vertex(id);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(v);
    }

    void addEdge(long id1, long id2) {
        Vertex v1 = new Vertex(id1);
        Vertex v2 = new Vertex(id2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    void removeEdge(long id1, long id2) {
        Vertex v1 = new Vertex(id1);
        Vertex v2 = new Vertex(id2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public void createGraph() {
        adjVertices.clear();

        for (int i=0; i<12; i++){
            addVertex(i);
        }
        addEdge(0, 3);
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(3, 2);
        addEdge(1, 4);
        addEdge(3, 4);

    }

    public void sortAdjLists(){
        for (List<Vertex> list : adjVertices.values()) {
            list.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Graph no " + getId() + ":\n");

        adjVertices.forEach((vertex, adjVertexes) -> {
            sb.append("\t" + vertex + " : " + adjVertexes + "\n");
        });

        return sb.toString();
    }

    public List<Vertex> getAdjVertices (long vertexId){
        return adjVertices.get(new Vertex(vertexId));
    }

    //Getters and setters
    public static long getNextId() {
        return nextId;
    }

    private static void setNextId(long nextId) {
        GraphAdjacencyList.nextId = nextId;
    }

    public static long getCounter() {
        return counter;
    }

    private static void setCounter(long counter) {
        GraphAdjacencyList.counter = counter;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Map<Vertex, List<Vertex>> getAdjVertices() {
        return adjVertices;
    }

    public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphAdjacencyList)) return false;
        GraphAdjacencyList that = (GraphAdjacencyList) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
