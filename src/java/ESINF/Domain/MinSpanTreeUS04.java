package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Edge;
import ESINF.Graph.Map.MapGraph;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Us 04.
 */
public class MinSpanTreeUS04 {

    /**
     * The Network builder.
     */
    MapGraph<Hub, Integer> networkBuilder;

    /**
     * Instantiates a new Us 04.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public MinSpanTreeUS04(MapGraph<Hub, Integer> graph) throws FileNotFoundException {
        this.networkBuilder = graph;
    }


    /**
     * Gets minimum spanning tree.
     *
     * @param graph the graph
     * @return the minimum spanning tree
     */
    public static MapGraph<Hub, Integer> getMinimumSpanningTree(MapGraph<Hub, Integer> graph) {
        if (graph == null) {
            return null;
        } else {
            return Algorithms.minSpanTree(graph, Integer::compare, Integer::sum, 0);
        }
    }

    /**
     * Execute.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void execute() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = networkBuilder;
        MapGraph<Hub, Integer> minSpanTree = getMinimumSpanningTree(graph);
        Set<Edge<Hub, Integer>> visitedEdges = new HashSet<>();
        double totalDistance = 0;
        System.out.println("Rede que liga todas as localidades com uma distância total mínima:");
        for (Hub hub : minSpanTree.vertices()) {
           for (Hub hub1 : minSpanTree.adjVertices(hub)) {
               if (hub != hub1) {
                   Edge<Hub, Integer> edge = graph.edge(hub, hub1);
                   if (!visitedEdges.contains(edge)) {
                       totalDistance += edge.getWeight();
                       visitedEdges.add(edge);
                       System.out.printf(edge.getVOrig().getLocalId() +" -> " + edge.getVDest().getLocalId() +"\n Distância: "+ edge.getWeight() +  " metros\n");
                   }
               }
           }
        }
        System.out.printf("Distância total: " + (int) totalDistance + " metros\n");
    }
}