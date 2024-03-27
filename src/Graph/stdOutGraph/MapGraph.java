package Graph.stdOutGraph;

import Graph.WeightedGraph;

import java.util.*;

public class MapGraph<T> implements WeightedGraph<T> {
    int size;
    Map<T, Map<T, Integer>> vertices;

    public MapGraph() {
        vertices = new HashMap<>();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addVertex(T vertex) {
        if(vertex == null){
            throw new NullPointerException("You cannot add a null vertex to the graph.");
        }
        vertices.put(vertex, new HashMap<>());
        size++;
    }

    @Override
    public void removeVertex(T vertex) {
        vertices.remove(vertex);
        size --;
    }

    @Override
    public boolean hasVertex(T vertex) {
        if(vertex == null){
            throw new NullPointerException("Null is not allowed here.");
        }
        return vertices.containsKey(vertex);
    }

    /**
     * Adds an edge between fromVertex to toVertex with the given weight.
     * If such an edge already exists, replaces the weight with the given weight
     * @param fromVertex
     * @param toVertex
     * @param weight
     */
    @Override
    public void addEdge(T fromVertex, T toVertex, int weight) {
        if (fromVertex.equals(toVertex)) {
            throw new IllegalArgumentException("You cannot add an edge from a vertex to itself.");
        }
        if(! (hasVertex(fromVertex) && hasVertex(toVertex))){
            throw new IllegalStateException("One of the vertices is not in the graph");
        }
        vertices.get(fromVertex).put(toVertex, weight);
    }

    @Override
    public void removeEdge(T fromVertex, T toVertex) {
        Map<T, Integer> map = vertices.get(fromVertex);
        map.remove(toVertex);
    }

    /**
     * Returns the weight of the edge from one vertex to another, or -1 if there is no such edge.
     * If the fromVertex and ToVertex are the same, returns 0;
     * @param fromVertex
     * @param toVertex
     * @return
     */
    @Override
    public int weightIs(T fromVertex, T toVertex) {
        if(fromVertex.equals(toVertex)){
            return 0;
        }
        Integer result = vertices.get(fromVertex).get(toVertex);
        return (result == null) ? -1: result;
    }

    /**
     * This method returns true if there is an edge from fromVertex to toVertex.
     * If the two vertices are the same, the method returns true.
     * @param fromVertex
     * @param toVertex
     * @return
     */
    @Override
    public boolean hasEdge(T fromVertex, T toVertex){
        if(fromVertex.equals(toVertex)){
            return true;
        }
        return vertices.get(fromVertex).containsKey(toVertex);
    }

    @Override
    public List<T> getAllVertices() {
        return List.copyOf(vertices.keySet());
    }


    class Edge<T>{
        T toVertex;
        int weight;

        public Edge(T endingVertex, int weight) {
            this.toVertex = endingVertex;
            this.weight = weight;
        }

        public T getToVertex() {
            return toVertex;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
