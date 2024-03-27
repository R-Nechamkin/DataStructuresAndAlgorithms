package Graph;

import java.util.List;
import java.util.Set;

public interface WeightedDirectionalGraph<T> {

    boolean isEmpty();
    boolean isFull();

    /**
     * Returns the number of vertices in the graph
     * @return
     */
    int size();
    void addVertex(T vertex);
    void removeVertex(T vertex);
    boolean hasVertex(T vertex);
    void addEdge(T fromVertex, T toVertex, int weight);
    void removeEdge(T fromVertex, T toVertex);
    int weightIs(T fromVertex, T toVertex);

    boolean hasEdge(T fromVertex, T toVertex);

    /**
     * Returns a list of all vertices in the graph.
     * @return
     */
    List<T> getAllVertices();
    /**
     * Returns a set of all vertices which the vertex is adjacent to.
     * @param vertex
     * @return
     */
    Set<T> getAdjacentVertices(T vertex);

}
