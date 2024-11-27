package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Edge;
import ESINF.Graph.Map.MapGraph;

import java.util.*;

public class HubForLocation {

    MapGraph<Hub, Integer> graph;

    public HubForLocation(MapGraph<Hub, Integer> graph) {
        this.graph = graph;
    }

    public void hubForLocation() {
        Map<Hub, Set<Hub>> clusters = clusterize(graph);
        if (clusters != null) {
            displayClusters(clusters);
        } else {
            System.out.println("Error: Unable to form the required number of clusters.");
        }
    }

    public Map<Hub, Set<Hub>> clusterize(MapGraph<Hub, Integer> graph) {
        List<Set<Hub>> clusterList = null;
        Map<Hub, Set<Hub>> clustersMap = new HashMap<>();

        // Identify potential hubs and initialize clusters
        for (Hub hub : graph.vertices()) {
            if (hub.isPromoted()) {
                clustersMap.put(hub, new HashSet<>());
            }
        }
        int numHubs = clustersMap.size();

        // Calculate shortest paths between hubs and locations
        Map<Hub, List<LinkedList<Hub>>> paths = determineShortestPaths(graph);
        clusterList = validateClusters(paths);

        while (clusterList.size() < numHubs) {
            // Find the edge with the highest number of shortest paths
            Map<Edge<Hub, Integer>, Integer> edgeUsageMap = getEdgeUsageMap(graph, paths);

            boolean removedEdge = false;

            while (!removedEdge) {
                MapGraph<Hub, Integer> tempGraph = graph.clone();

                Edge<Hub, Integer> edgeToRemove = selectEdgeToRemove(edgeUsageMap);

                // Remove the edge temporarily
                tempGraph.removeEdge(edgeToRemove.getVOrig(), edgeToRemove.getVDest());

                paths = determineShortestPaths(tempGraph);
                clusterList = validateClusters(paths);

                // Check if every cluster has one or more hubs
                if (clusterList != null) {
                    removedEdge = true;
                    graph = tempGraph;
                }
            }
        }

        // Map clusters to their respective hubs
        for (Set<Hub> cluster : clusterList) {
            for (Hub hub : clustersMap.keySet()) {
                if (cluster.contains(hub)) {
                    clustersMap.put(hub, cluster);
                    break;
                }
            }
        }

        return clustersMap;
    }

    public static Map<Hub, List<LinkedList<Hub>>> determineShortestPaths(MapGraph<Hub, Integer> graph) {
        List<Hub> locations = graph.vertices();
        Map<Hub, List<LinkedList<Hub>>> allPaths = new HashMap<>();

        for (Hub hub : locations) {
            ArrayList<LinkedList<Hub>> paths = new ArrayList<>();
            ArrayList<Integer> distances = new ArrayList<>();
            Algorithms.shortestPaths(graph, hub, Integer::compare, Integer::sum, 0, paths, distances);
            allPaths.put(hub, paths);
        }
        return allPaths;
    }

    public static Map<Edge<Hub, Integer>, Integer> getEdgeUsageMap(MapGraph<Hub, Integer> graph, Map<Hub, List<LinkedList<Hub>>> allPaths) {
        Map<Edge<Hub, Integer>, Integer> edgeUsageCounter = new HashMap<>();

        for (List<LinkedList<Hub>> paths : allPaths.values()) {
            for (LinkedList<Hub> path : paths) {
                if (path != null) {
                    for (int i = 0; i < path.size() - 1; i++) {
                        Edge<Hub, Integer> edge = graph.edge(path.get(i), path.get(i + 1));
                        if (edge != null) {
                            if (edgeUsageCounter.containsKey(edge)) {
                                edgeUsageCounter.put(edge, edgeUsageCounter.get(edge) + 1);
                            } else {
                                edgeUsageCounter.put(edge, 1);
                            }
                        }
                    }
                }
            }
        }

        return edgeUsageCounter;
    }

    public static Edge<Hub, Integer> selectEdgeToRemove(Map<Edge<Hub, Integer>, Integer> edgeUsageCounter) {
        Edge<Hub, Integer> edgeToRemove = null;
        int edgeUses = 0;
        for (Edge<Hub, Integer> edge : edgeUsageCounter.keySet()) {
            if (edgeUses < edgeUsageCounter.get(edge)) {
                edgeToRemove = edge;
                edgeUses = edgeUsageCounter.get(edge);
            }
        }

        edgeUsageCounter.remove(edgeToRemove);
        return edgeToRemove;
    }

    public static List<Set<Hub>> validateClusters(Map<Hub, List<LinkedList<Hub>>> allPaths) {
        List<Set<Hub>> clusters = new ArrayList<>();
        for (Hub hub : allPaths.keySet()) {
            boolean inCluster = false;
            Set<Hub> cluster = null;

            for (Set<Hub> existingCluster : clusters) {
                if (existingCluster.contains(hub)) {
                    inCluster = true;
                    cluster = existingCluster;
                    break;
                }
            }

            if (!inCluster) {
                cluster = new HashSet<>();
                clusters.add(cluster);
            }

            for (LinkedList<Hub> path : allPaths.get(hub)) {
                // Check if a path exists
                if (path != null) {
                    cluster.addAll(path);
                }
            }
        }

        // Verify if each cluster has at least one hub
        for (Set<Hub> cluster : clusters) {
            boolean hasHub = false;
            for (Hub location : cluster) {
                if (location.isPromoted()) {
                    hasHub = true;
                    break;
                }
            }
            if (!hasHub) {
                return null;
            }
        }
        return clusters;
    }

    public void displayClusters(Map<Hub, Set<Hub>> clusters) {
        int clusterIndex = 1;
        for (Map.Entry<Hub, Set<Hub>> entry : clusters.entrySet()) {
            Hub hub = entry.getKey();
            Set<Hub> locations = entry.getValue();
            System.out.println("Cluster " + clusterIndex + " - Hub: " + hub.getLocalId());
            System.out.println("Locations:");
            for (Hub location : locations) {
                if (!location.equals(hub)) {
                    System.out.println("->" + location.getLocalId());
                }
            }
            System.out.println();
            clusterIndex++;
        }
    }
}
