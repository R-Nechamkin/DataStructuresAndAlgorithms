package Graph;

public class TestingUtilityMethods {

    public static int[][] addLotsOfEdgesToStringGraph(Graph<String> graph, String[] array, int WEIGHT){
        int[][] edges = createListOfEdges(array);

        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                graph.addEdge(array[i], array[edges[i][j]], WEIGHT);
            }
        }
        return edges;
    }

    public static int[][] createListOfEdges(String[] array){
        int[][] edges = new int[array.length][];
        edges[0] = new int[] {2,3,4,7,8};
        edges[1] = new int[] {0,5,7};
        edges[2] = new int[] {1,3,4,5};
        edges[3] = new int[] {0,2,5,6};
        edges[4] = new int[] {0,2,6,8};
        edges[5] = new int[] {1,2,3};
        edges[6] = new int[] {3,4};
        edges[7] = new int[] {0,1,8};
        edges[8] = new int[] {0,4,7};
        edges[9] = new int[] {10};
        edges[10] = new int[] {9};

        return edges;
    }
}
