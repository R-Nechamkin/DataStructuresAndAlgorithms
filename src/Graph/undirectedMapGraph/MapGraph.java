package Graph.undirectedMapGraph;

import Graph.DirectedGraph;
import Graph.UndirectedGraph;

import java.util.*;

public class MapGraph<T> implements UndirectedGraph<T> {
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
     * Adds an edge between vertexOne to vertexTwo with the given weight.
     * If such an edge already exists, replaces the weight with the given weight
     * @param vertexOne
     * @param vertexTwo
     * @param weight
     */
    @Override
    public void addEdge(T vertexOne, T vertexTwo, int weight) {
        if (vertexOne.equals(vertexTwo)) {
            throw new IllegalArgumentException("You cannot add an edge from a vertex to itself.");
        }
        if(! (hasVertex(vertexOne) && hasVertex(vertexTwo))){
            throw new IllegalStateException("One of the vertices is not in the graph");
        }
        vertices.get(vertexOne).put(vertexTwo, weight);
        vertices.get(vertexTwo).put(vertexOne, weight);
    }

    @Override
    public void removeEdge(T vertexOne, T vertexTwo) {
        Map<T, Integer> map = vertices.get(vertexOne);
        map.remove(vertexTwo);

        map = vertices.get(vertexTwo);
        map.remove(vertexOne);
    }

    /**
     * Returns the weight of the edge from one vertex to another, or -1 if there is no such edge.
     * If the vertexOne and ToVertex are the same, returns 0;
     * @param vertexOne
     * @param vertexTwo
     * @return
     */
    @Override
    public int weightIs(T vertexOne, T vertexTwo) {
        if(vertexOne.equals(vertexTwo)){
            return 0;
        }
        Integer result = vertices.get(vertexOne).get(vertexTwo);
        return (result == null) ? -1: result;
    }

    /**
     * This method returns true if there is an edge from vertexOne to vertexTwo.
     * If the two vertices are the same, the method returns true.
     * @param vertexOne
     * @param vertexTwo
     * @return
     */
    @Override
    public boolean hasEdge(T vertexOne, T vertexTwo){
        if(vertexOne.equals(vertexTwo)){
            return true;
        }
        return vertices.get(vertexOne).containsKey(vertexTwo);
    }

    @Override
    public List<T> getAllVertices() {
        return List.copyOf(vertices.keySet());
    }

    @Override
    public Set<T> getAdjacentVertices(T vertex) {
        return vertices.get(vertex).keySet();
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
