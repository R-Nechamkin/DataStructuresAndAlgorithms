package Graph.graphTraverser;

import java.util.Queue;

/**
 * This class holds a path from a starting vertex to an ending vertex
 *
 * @param <T>
 */
public class TraversalObject<T> {

    Queue<T> path;
    int totalWeight;
    T startingVertex;
    T endingVertex;

    public TraversalObject(Queue<T> path, int totalWeight, T startingVertex, T endingVertex) {
        this.path = path;
        this.totalWeight = totalWeight;
    }

    public boolean pathExists(){
        return path != null;
    }

    /**
     * Returns a queue of vertices which will lead from the starting vertex to the ending vertex.
     * If there is no path, the queue will be null.
     * If the starting vertex and ending vertex are the same, path will consist of only one vertex - that one.
     * @return
     */
    public Queue<T> getPath() {
        return path;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public T getStartingVertex() {
        return startingVertex;
    }

    public T getEndingVertex() {
        return endingVertex;
    }
}
