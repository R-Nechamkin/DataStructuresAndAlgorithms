package Graph.ManualTesting;

import Graph.*;
import Graph.graphTraverser.GraphTraverser;
import Graph.graphTraverser.TraversalObject;
import Graph.traversableGraph.TraversableGraphDecorator;
import Graph.undirectedMapGraph.MapGraph;
import Graph.UndirectedGraph;

import java.util.Queue;

/**
 * Tests the graph classes and graph traversers by creating a graph representing the Ticket to Ride board game
 */
public class ClientCode {
    public static void main(String[] args) {
        Graph<String> graph = createGraph();

        System.out.println("Printing representation of graph as adjacency list:\n");
        System.out.println(getGraphAdjacencyListRepresentation(graph));

        System.out.println("\nLet's explore paths from one city to another.");
        GraphTraverser<String> traverser = new GraphTraverser<>(new TraversableGraphDecorator<String>(graph));

        String vancouverToSaltLake = viewPathFrom(traverser, "Vancouver", "Salt Lake City");
        System.out.println("\n" + vancouverToSaltLake);
        String calgaryToDenver = viewPathFrom(traverser, "Calgary", "Denver");
        System.out.println("\n" + calgaryToDenver);
        String lasVegasToVancouver = viewPathFrom(traverser, "Las Vegas", "Vancouver");
        System.out.println("\n" + lasVegasToVancouver);
    }

    private static Graph<String> createGraph(){
        TTRBoardGraph ttrBoard = new TTRBoardGraph(new MapGraph<>());
        ttrBoard.addSomeCities();
        return ttrBoard.getGraph();
    }

    private static String getGraphAdjacencyListRepresentation(Graph<String> graph){
        StringBuilder sb = new StringBuilder();
        for(String city: graph.getAllVertices()){
            sb.append(city + " -> ");
            if(graph.getAdjacentVertices(city).isEmpty()){
                sb.append("No connections");
            }
            for(String neighbor: graph.getAdjacentVertices(city)){
                sb.append(neighbor + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String viewPathFrom(GraphTraverser<String> traverser, String startCity, String endCity){
        TraversalObject<String> path = traverser.getBreadthFirstPath(startCity, endCity);
        StringBuilder sb = new StringBuilder();
        if(!path.pathExists()){
            sb.append("No path exists from ").append(startCity + " to " + endCity);
        }
        else {
            for (String vertex: path.getPath()){
                sb.append("Go to " + vertex + "\n");
            }
        }
        return sb.toString();
    }

}

