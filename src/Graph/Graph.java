package Graph;

import java.util.Queue;

public interface Graph<T> {

    boolean isEmpty();
    boolean isFull();
    void addVertex(T vertex);
    boolean hasVertex(T vertex);
    void addEdge(T fromVertex, T toVertex, int weight);
    int weightIs(T fromVertex, T toVertex);
    Queue<T> getToVertices(T vertex);
    void clearMarks();
    void markVertex();
    boolean isMarked(T vertex);
    T getUnmarked();
}
