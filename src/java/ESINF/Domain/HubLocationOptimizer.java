package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Graph;
import ESINF.Graph.Edge;
import ESINF.Domain.Hub;
import ESINF.Graph.Map.MapGraph;
import ESINF.IO.InputInfo;

import java.io.FileNotFoundException;
import java.util.*;

public class HubLocationOptimizer {

    public static List<HubOptimizationCriteria> optimizeHubs(Graph<Hub, Integer> graph) {

        List<HubOptimizationCriteria> optimizationCriteria = new ArrayList<>();

        for (Hub hub : graph.vertices()) {
            double prox = 0;
            double centrality = 0;
            int distance = 0;
            ArrayList<LinkedList<Hub>> paths = new ArrayList<>();
            ArrayList<Integer> dists = new ArrayList<>();

            if (Algorithms.shortestPaths(graph, hub, Integer::compare, Integer::sum, 0, paths, dists)) {


                for (int d : dists) {
                    if (d > 0) {
                        distance += d;
                    }
                }

                prox = distance;

                centrality = calculateCentrality(graph, hub);

            }

            HubOptimizationCriteria hubOptimizationCriteria = new HubOptimizationCriteria(hub);
            hubOptimizationCriteria.setInfluence(graph.outDegree(hub));
            hubOptimizationCriteria.setProximity(prox);
            hubOptimizationCriteria.setCentrality(centrality);
            optimizationCriteria.add(hubOptimizationCriteria);

        }

        return  orderListByDecreasinglyOrderOfCentralityAndInfluence(optimizationCriteria);
    }

    public static double calculateCentrality(Graph<Hub, Integer> graph, Hub hub) {
        double centrality = 0;
        for (Hub hubie: graph.vertices()) {
            ArrayList<LinkedList<Hub>> pathsOfHubie = new ArrayList<>();
            ArrayList<Integer> dists = new ArrayList<>();
            Algorithms.shortestPaths(graph, hubie, Integer::compare, Integer::sum, 0, pathsOfHubie, dists);
            for (LinkedList<Hub> path : pathsOfHubie) {
                if (path.contains(hub)) {
                    centrality++;
                }
            }
        }
        return centrality;
    }

    public static List<HubOptimizationCriteria> orderListByDecreasinglyOrderOfCentralityAndInfluence(List<HubOptimizationCriteria> list){
        Collections.sort(list, new Comparator<HubOptimizationCriteria>() {
            @Override
            public int compare(HubOptimizationCriteria o1, HubOptimizationCriteria o2) {
                if (o1.getCentrality() > o2.getCentrality()) {
                    return -1;
                } else if (o1.getCentrality() < o2.getCentrality()) {
                    return 1;
                } else {
                    if (o1.getInfluence() > o2.getInfluence()) {
                        return -1;
                    } else if (o1.getInfluence() < o2.getInfluence()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        return list;
    }

    public static void USES02(MapGraph<Hub, Integer> graph,int n) throws FileNotFoundException {
       List<HubOptimizationCriteria> hubs = optimizeHubs( graph);
       for (HubOptimizationCriteria hub : hubs.subList(0,n)) {
           hub.getHub().promoteToHub();
           System.out.printf("Hub:%5s | Influence: %5d | Proximity: %10d | Centrality: %5d\n",
                   hub.getHub().getLocalId(),
                   (int) hub.getInfluence(),
                   (int) hub.getProximity(),
                   (int) hub.getCentrality());       }
    }
}
