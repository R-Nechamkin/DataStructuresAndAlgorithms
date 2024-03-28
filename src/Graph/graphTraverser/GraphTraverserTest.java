package Graph.graphTraverser;

import Graph.stdOutGraph.MapGraph;
import Graph.traversableGraph.TraversableGraphDecorator;
import static Graph.TestingUtilityMethods.addLotsOfEdgesToStringGraph;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.Stack;

class GraphTraverserTest {
    GraphTraverser<String> traverser;
    TraversableGraphDecorator<String> graph;
    String[] array ;// = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K", "L"};

    @BeforeEach
    void setUp(){
        graph = new TraversableGraphDecorator<>(new MapGraph<>());
        traverser = new GraphTraverser<>(graph);
        array = new String[] {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K"};
    }

    @Test
    void breadthFirstReturnsPathWhichGoesFromStartToEnd(){
        addSampleArrayToGraph();
        int[][] edges = addLotsOfEdgesToStringGraph(graph, array, 5);
        int start = 4;
        int mid1 = edges[4][1], mid2 = edges[mid1][0], mid3 = edges[mid2][2];
        int end =edges[mid3][0];

        TraversalObject<String> pathObject = traverser.getBreadthFirstPath(array[start], array[end]);
        assertPathGoesFromStartToEnd(pathObject.getPath(), array[start], array[end]);
    }

    @Test
    void breadthFirstReturnsNullQueueIfNoPathExists(){
        addSampleArrayToGraph();
        String start = array[0];
        String end = array[1];
        TraversalObject<String> pathObject = traverser.getBreadthFirstPath(start, end);
        assertNull(pathObject.getPath());
    }



    void assertPathGoesFromStartToEnd(Queue<String> path, String start, String end){
        if(start.equals(end)){
            assertTrue(path.size() == 1 && path.peek().equals(start));
        }
        String prev, curr;
        curr = path.remove();
        assertEquals(start, curr, "First vertex on path is not equal to starting vertex");

        while(!path.isEmpty()){
            prev = curr;
            curr = path.remove();
            assertTrue(graph.hasEdge(prev, curr));
        }
        assertEquals(end, curr, "Last vertex on path is not equal to ending vertex");

    }



    void addSampleArrayToGraph(){
        addSomeOfSampleArrayToGraph(0, array.length);
    }


    /*
     * start is inclusive, end is exclusive
     */
    void addSomeOfSampleArrayToGraph(int start, int end){
        for (int i = start; i < end; i++) {
            graph.addVertex(array[i]);
        }
    }

    void removeSampleArrayFromGraph(){
        removeSomeOfSampleArrayFromGraph(0, array.length);
    }

    /*
     * start is inclusive, end is exclusive
     */
    void removeSomeOfSampleArrayFromGraph(int start, int end){
        for (int i = start; i < end; i++) {
            graph.removeVertex(array[i]);
        }
    }
}