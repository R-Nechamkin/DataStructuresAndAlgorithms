package Graph.ManualTesting;


import Graph.UndirectedGraph;

import java.util.HashMap;
import java.util.Map;

public class TTRBoardGraph {
    UndirectedGraph<City> graph;
    Map<String, City> cityNames;

    public TTRBoardGraph(UndirectedGraph<City> graph) {
        this.graph = graph;
        cityNames = new HashMap<>();
        addSomeNamesToCityMap();
    }

    private void addSomeNamesToCityMap(){
        String[] arrayOfNames = {"Vancover", "Seattle", "Portland", "San Francisco",
        "Los Angeles", "Los Vegas", "Salt Lake City", "Helena", "Calgary", "Winnipeg"
        , "Denver"};

        for(String name: arrayOfNames){
            cityNames.put(name, new City(name));
        }
    }

    public void addSomeCities(){
        cityNames.values().forEach(graph::addVertex);
        graph.addEdge(cityNames.get("Vancouver"), cityNames.get("Seattle"), 1);
        graph.addEdge(cityNames.get("Seattle"), cityNames.get("Portland"), 1);
        graph.addEdge(cityNames.get("Portland"), cityNames.get("San Francisco"), 5);
        graph.addEdge(cityNames.get("San Francisco"), cityNames.get("Los Angeles"), 3);
        graph.addEdge(cityNames.get("Los Angeles"), cityNames.get("Las Vegas"), 2);
        graph.addEdge(cityNames.get("Las Vegas"), cityNames.get("Salt Lake City"), 1);
        graph.addEdge(cityNames.get("San Francisco"), cityNames.get("Salt Lake City"), 5);
        graph.addEdge(cityNames.get("Portland"), cityNames.get("Salt Lake City"), 6);
        graph.addEdge(cityNames.get("Salt Lake City"), cityNames.get("Helena"), 3);
        graph.addEdge(cityNames.get("Seattle"), cityNames.get("Helena"), 6);
        graph.addEdge(cityNames.get("Portland"), cityNames.get("Calgary"), 3);
        graph.addEdge(cityNames.get("Seattle"), cityNames.get("Calgary"), 4);
        graph.addEdge(cityNames.get("Calgary"), cityNames.get("Helena"), 4);
        graph.addEdge(cityNames.get("Calgary"), cityNames.get("Winnipeg"), 6);
        graph.addEdge(cityNames.get("Helena"), cityNames.get("Winnipeg"), 4);
        graph.addEdge(cityNames.get("Helena"), cityNames.get("Denver"), 4);
        graph.addEdge(cityNames.get("Salt Lake City"), cityNames.get("Denver"), 3);
    }
}
