package Graph;

/**
 * The only difference between this and the regular graph interface is the names
 * @param <T>
 */
public interface UndirectedGraph<T> extends Graph<T> {
    @Override
    void addEdge(T vertexOne, T vertexTwo, int weight);

    @Override
    boolean hasEdge(T vertexOne, T vertexTwo);

    @Override
    void removeEdge(T vertexOne, T vertexTwo);

    @Override
    int weightIs(T vertexOne, T vertexTwo);
}
