package Graph.graphTraverser;

import Graph.traversableGraph.TraversableGraphDecorator;


import java.util.*;

public class GraphTraverser<T> {
    TraversableGraphDecorator<T> graph;

    public GraphTraverser(TraversableGraphDecorator<T> graph) {
        this.graph = graph;
    }

    public TraversableGraphDecorator<T> getGraph() {
        return graph;
    }

    public void setGraph(TraversableGraphDecorator<T> graph) {
        this.graph = graph;
    }


    /**
     * This method attempts to find a path from startVertex to endVertex.
     *
     * If there is no path, it will return a TraversalObject<T> with a null queue.
     * If the starting vertex and ending vertex are the same, path will consist of only one vertex - that one.
     *
     * @param startVertex
     * @param endVertex
     * @return
     */
    public TraversalObject<T> getBreadthFirstPath(T startVertex, T endVertex){
        if (!graph.hasVertex(startVertex) || !graph.hasVertex(endVertex)) {
            throw new IllegalArgumentException("One of the vertices is not in the graph");
        }
        graph.clearMarks();
        Map<T, T> previousMap = new HashMap<>();
        Queue<T> q = new ArrayDeque<>();
        boolean found = false;

        Queue<T> path = null;

        T curr;

        graph.markVertex(startVertex);
        q.add(startVertex);
        previousMap.put(startVertex, null);

        do{
            curr = q.remove();
            if(curr.equals(endVertex)){
                path = constructPath(endVertex, previousMap);
                found = true;
            }
            else {
                for(T neighbor: graph.getAdjacentVertices(curr)){
                    if (!graph.isMarked(neighbor)) {
                        graph.markVertex(neighbor);
                        q.add(neighbor);
                        previousMap.put(neighbor, curr);
                    }
                }
            }
        }while (!q.isEmpty() && !found);

        return new TraversalObject<>(path, startVertex, endVertex);
    }

    private Queue<T> constructPath(T endVertex, Map<T, T> prevMap) {
        Deque<T> path = new ArrayDeque<>();
        T current = endVertex;
        while (current != null) {
            path.addFirst(current);
            current = prevMap.get(current);
        }
        return path;
    }

}
