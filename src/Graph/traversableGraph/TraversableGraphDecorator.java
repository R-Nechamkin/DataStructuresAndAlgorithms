package Graph.traversableGraph;

import Graph.Graph;

import java.util.*;

public class TraversableGraphDecorator<T>  implements Graph<T> {
    Set<T> marked;
    Graph<T> internalGraph;

    public TraversableGraphDecorator(Graph<T> graph) {
        this.marked = new HashSet<>();
        this.internalGraph = graph;
    }

    @Override
    public boolean isEmpty() {
        return internalGraph.isEmpty();
    }

    @Override
    public boolean isFull() {
        return internalGraph.isFull();
    }

    @Override
    public int size() {
        return internalGraph.size();
    }

    @Override
    public void addVertex(T vertex) {
        internalGraph.addVertex(vertex);
    }

    @Override
    public void removeVertex(T vertex) {
        internalGraph.removeVertex(vertex);
    }

    @Override
    public boolean hasVertex(T vertex) {
        return internalGraph.hasVertex(vertex);
    }

    @Override
    public void addEdge(T fromVertex, T toVertex, int weight) {
        internalGraph.addEdge(fromVertex, toVertex, weight);
    }

    @Override
    public void removeEdge(T fromVertex, T toVertex) {
        internalGraph.removeEdge(fromVertex, toVertex);
    }

    @Override
    public int weightIs(T fromVertex, T toVertex) {
        return internalGraph.weightIs(fromVertex, toVertex);
    }

    @Override
    public boolean hasEdge(T fromVertex,T toVertex) {
        return internalGraph.hasEdge(fromVertex, toVertex);
    }

    @Override
    public List<T> getAllVertices() {
        return internalGraph.getAllVertices();
    }

    @Override
    public Set<T> getAdjacentVertices(T vertex) {
        return internalGraph.getAdjacentVertices(vertex);
    }

    public void clearMarks() {
        marked = new HashSet<>();
    }


    public void markVertex(T vertex) {
        marked.add(vertex);
    }


    public boolean isMarked(T vertex) {
        return marked.contains(vertex);
    }

    /**
     * Returns a random unmarked vertex if any vertices are unmarked.
     * If all vertices are marked, returns null
     * @return  an unmarked vertex or null
     */
    public T getUnmarked() {
        for(T vertex: internalGraph.getAllVertices()){
            if(!marked.contains(vertex)){
                return vertex;
            }
        }
        return null;
    }

}
