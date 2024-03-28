package Graph.ManualTesting;

import Graph.UndirectedGraph;

public class TTRBoardGraph {
    UndirectedGraph<String> graph;

    public TTRBoardGraph(UndirectedGraph<String> graph) {
        this.graph = graph;
        addSomeCities();
    }

    public UndirectedGraph<String> getGraph(){
        return graph;
    }

    public void addSomeCities(){
        String[] arrayOfNames = {"Vancouver", "Seattle", "Portland", "San Francisco",
                "Los Angeles", "Las Vegas", "Salt Lake City", "Helena", "Calgary", "Winnipeg"
                , "Denver"};

        for(String name: arrayOfNames){
            graph.addVertex(name);
        }

        graph.addEdge("Vancouver", "Seattle", 1);
        graph.addEdge("Vancouver", "Calgary", 3);
        graph.addEdge("Seattle", "Portland", 1);
        graph.addEdge("Portland", "San Francisco", 5);
        graph.addEdge("San Francisco", "Los Angeles", 3);
        graph.addEdge("Los Angeles", "Las Vegas", 2);
        graph.addEdge("Las Vegas", "Salt Lake City", 1);
        graph.addEdge("San Francisco", "Salt Lake City", 5);
        graph.addEdge("Portland", "Salt Lake City", 6);
        graph.addEdge("Salt Lake City", "Helena", 3);
        graph.addEdge("Seattle", "Helena", 6);
        graph.addEdge("Portland", "Calgary", 3);
        graph.addEdge("Seattle", "Calgary", 4);
        graph.addEdge("Calgary", "Helena", 4);
        graph.addEdge("Calgary", "Winnipeg", 6);
        graph.addEdge("Helena", "Winnipeg", 4);
        graph.addEdge("Helena", "Denver", 4);
        graph.addEdge("Salt Lake City", "Denver", 3);
    }
}
