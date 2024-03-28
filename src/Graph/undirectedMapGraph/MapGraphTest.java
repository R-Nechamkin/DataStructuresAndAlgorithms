package Graph.undirectedMapGraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Graph.TestingUtilityMethods.addLotsOfEdgesToStringGraph;
import static org.junit.jupiter.api.Assertions.*;



class MapGraphTest {

    MapGraph<String> graph;
    String[] array;
    @BeforeEach
    void setUp() {
        graph = new MapGraph<>();
        array = new String[]{"Cucumber", "Apple", "Beet", "Orange", "Lettuce", "Grape", "Grapefruit",
                "Carrot", "Date", "Peach", "Nectarine"};
    }

    @Test
    void beforeAddElementsSizeReturnsZero(){
        assertEquals(0, graph.size());
    }

    @Test
    void afterAddOneVertexSizeReturnsOne(){
        graph.addVertex(array[0]);
        assertEquals(1, graph.size());
    }

    @Test
    void afterAddMultipleVerticesSizeReturnsCorrectAmount(){
        addSampleArrayToGraph();
        assertEquals(array.length, graph.size());
    }




    @Test
    void afterAddMultipleVerticesAndRemoveSomeButNotAllSizeReturnsCorrectAmoung(){
        addSampleArrayToGraph();
        final int AMOUNT_TO_LEAVE_IN_GRAPH = 3;
        removeSomeOfSampleArrayFromGraph(0, array.length - AMOUNT_TO_LEAVE_IN_GRAPH);
        assertEquals(AMOUNT_TO_LEAVE_IN_GRAPH, graph.size());
    }

    @Test
    void beforeAddElementsIsEmptyReturnsTrue(){
        assertTrue(graph.isEmpty());
    }

    @Test
    void afterAddOneVertexIsEmptyReturnsFalse(){
        graph.addVertex(array[0]);
        assertFalse(graph.isEmpty());
    }

    @Test
    void afterAddMultipleVerticesIsEmptyReturnsFalse(){
        addSampleArrayToGraph();
        assertFalse(graph.isEmpty());
    }

    @Test
    void afterAddMulipleVerticesAndRemoveAllIsEmptyReturnsFalse(){
        addSampleArrayToGraph();
        removeSampleArrayFromGraph();
        assertTrue(graph.isEmpty());
    }

    @Test
    void afterAddMultipleVerticesAndRemoveSomeButNotAllIsEmptyReturnsFalse(){
        addSampleArrayToGraph();
        removeSomeOfSampleArrayFromGraph(0, array.length -3);
        assertFalse(graph.isEmpty());
    }

    @Test
    void afterAddVertexGraphListOfVerticesContainsThatVertex(){
        graph.addVertex(array[0]);
        assertTrue(graphContainsVertex(array[0]));
    }

    @Test
    void afterAddAndRemoveVertexListOfVerticesDoesNotContainVertex(){
        graph.addVertex(array[0]);
        graph.removeVertex(array[0]);
        assertFalse(graphContainsVertex(array[0]));
    }


    @Test
    void tryingToAddEdgeInEmptyGraphThrowsException(){
        assertThrows(Exception.class, () -> graph.addEdge(array[0], array[1], 20));
    }

    @Test
    void tryingToAddEdgesBetweenVerticesNotInGraphThrowsException(){
        addSampleArrayToGraph();
        assertThrows(Exception.class, () -> graph.addEdge("Jump", "Run", 20));
    }

    @Test
    void afterAddVertexHasVertexReturnsTrue(){
        final String vertex = array[0];
        graph.addVertex(vertex);
        assertTrue(graph.hasVertex(vertex));
    }

    @Test
    void afterAddManyVerticesHasVertexReturnsTrueForAll(){
        addSampleArrayToGraph();
        for (int i = 0; i < array.length; i++) {
            assertTrue(graph.hasVertex(array[i]), "Could not find element " + i + " : " + array[i]);
        }
    }

    @Test
    void afterAddManyVerticesAndRemoveOneHasVertexReturnsTrueForAllButNotRemovedOne(){
        addSampleArrayToGraph();
        int indexToRemove = 3;
        graph.removeVertex(array[indexToRemove]);
        for (int i = 0; i < array.length; i++) {
            if(i == indexToRemove){
                assertFalse(graph.hasVertex(array[indexToRemove]));
            }
            else {
                assertTrue(graph.hasVertex(array[i]));
            }
        }
    }

    @Test
    void afterAddOneEdgeAndRemoveItWeightIsReturnsNegativeOne(){
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        graph.removeEdge(from, to);
        assertEquals(-1, graph.weightIs(from, to));
    }





    @Test
    void afterAddVerticesAndWeightIsWeightReturnsCorrectValue(){
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        assertEquals(WEIGHT, graph.weightIs(from, to));
    }

    @Test
    void afterAddOneEdgeHasEdgeReturnsTrue() {
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        assertTrue(graph.hasEdge(from, to));
    }

    @Test
    void afterAddAndRemoveOneEdgeHasEdgeReturnsFalse() {
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        graph.removeEdge(from, to);
        assertFalse(graph.hasEdge(from, to));
    }

    @Test
    void afterAddOneEdgeWeightIsReturnsCorrectWeight() {
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        assertEquals(WEIGHT, graph.weightIs(from, to));
    }

    @Test
    void afterAddAndRemoveOneEdgeWeightIsReturnsNegativeOne() {
        addSampleArrayToGraph();
        final int WEIGHT = 5;
        final String from = array[0];
        final String to = array[1];
        graph.addEdge(from, to, WEIGHT);
        graph.removeEdge(from, to);
        assertEquals(-1, graph.weightIs(from, to));
    }

    @Test
    void afterAddMultipleEdgesHasEdgeReturnsTrue() {
        addSampleArrayToGraph();
        final String from = array[0];
        final String to1 = array[1];
        final String to2 = array[2];
        final String to3 = array[3];

        graph.addEdge(from, to1, 5);
        graph.addEdge(from, to2, 7);
        graph.addEdge(from, to3, 9);

        assertTrue(graph.hasEdge(from, to1));
        assertTrue(graph.hasEdge(from, to2));
        assertTrue(graph.hasEdge(from, to3));
    }

    @Test
    void afterAddMultipleEdgesWeightIsReturnsCorrectWeight() {
        addSampleArrayToGraph();
        final String from = array[0];
        final String to1 = array[1];
        final String to2 = array[2];
        final String to3 = array[3];

        graph.addEdge(from, to1, 5);
        graph.addEdge(from, to2, 7);
        graph.addEdge(from, to3, 9);

        assertEquals(5, graph.weightIs(from, to1));
        assertEquals(7, graph.weightIs(from, to2));
        assertEquals(9, graph.weightIs(from, to3));
    }

    @Test
    void addingEdgeToItselfThrowsException(){
        addSampleArrayToGraph();
        String vertex = array[0];
        assertThrows(Exception.class, () -> graph.addEdge(vertex, vertex, 5));
    }

    @Test
    void WeightIsReturnsZeroWhenToAndFromVertexAreTheSame(){
        addSampleArrayToGraph();
        String vertex = array[0];
        assertEquals(0, graph.weightIs(vertex, vertex));
    }

    @Test
    void afterAddLotsOfEdgesHasEdgeReturnsTrueForAll(){
        final int WEIGHT = 5;
        addSampleArrayToGraph();
        int[][] edges = addLotsOfEdgesToStringGraph(graph, array, WEIGHT);
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                assertTrue(graph.hasEdge(array[i], array[edges[i][j]]),
                        "Edge from " + array[i] + " to " + array[j] +" was not found." );
            }
        }
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


    boolean graphContainsVertex(String vertex){
        return graph.vertices.containsKey(vertex);
    }





}