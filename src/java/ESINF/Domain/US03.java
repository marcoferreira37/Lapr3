package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Edge;
import ESINF.Graph.Map.MapGraph;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BinaryOperator;

import static java.lang.Integer.MAX_VALUE;

public class US03 {

    public MapGraph<Hub, Integer> graph;
    public US03(MapGraph<Hub, Integer> graph) throws FileNotFoundException {
        this.graph = graph;
    }


    public void print() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        double autonomia;

        System.out.println("Insira a autonomia do veículo: (m)");
        autonomia = scanner.nextInt();

        int min = 0;
        Hub hDest = null;
        Hub hOrigMax = null;
        int[] pathMax = null;

        for (int j = 0; j < graph.numVertices(); j++) {
            Hub hOrig = graph.vertex(j);
            boolean[] visited = new boolean[graph.numVertices()];
            int[] path = new int[graph.numVertices()];
            int[] distances = new int[graph.numVertices()];
            dijkstra(graph, hOrig, visited, path, distances);

            for (int i = j + 1; i < graph.numVertices(); i++) {

                if (!(graph.vertex(i) == hOrig)) {
                    if (distances[i] > min) {
                        min = distances[i];
                        hDest = graph.vertex(i);
                        hOrigMax = hOrig;
                        pathMax = path;

                    }
                }
            }
        }
        System.out.println("Vertex: " + hDest + " Distance: " + min);
        assert hDest != null;
        LinkedList<Hub> pathInOrder = buildShortestPath(graph, hOrigMax, hDest, pathMax);

        System.out.println("Shortest Path in Order with Edge Weights:");
        Map<Edge, Double> edgeWeightMap = shortestPathCarVei(pathInOrder, autonomia, hOrigMax);
        System.out.println(edgeWeightMap.toString());

    }

    public void dijkstra(MapGraph<Hub, Integer> graph, Hub hOrig, boolean[] visited, int[] path, int[] distances) {

        for (int i = 0; i < graph.numVertices(); i++) {
            visited[i] = false;
            distances[i] = MAX_VALUE;
            path[i] = -1;
        }
        int i, j;
        double weight;
        distances[graph.key(hOrig)] = 0;

        while (hOrig != null) {
            i = graph.key(hOrig);
            visited[i] = true;
            for (Hub hAdj : graph.adjVertices(hOrig)) {
                weight = graph.edge(hOrig, hAdj).getWeight();
                j = graph.key(hAdj);

                if (!visited[j] && distances[j] > distances[i] + weight) {
                    distances[j] = (int) (distances[i] + weight);
                    path[j] = i;
                }
            }
            int nextVertex = getVertMinDist(distances, visited);
            if (nextVertex != -1) {
                hOrig = graph.vertex(nextVertex);
            } else {
                hOrig = null;
            }
        }
    }

    private int getVertMinDist(int[] distances, boolean[] visited) {
        int min = MAX_VALUE;
        int index = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && min > distances[i]) {
                min = distances[i];
                index = i;
            }
        }
        return index;
    }


    public LinkedList<Hub> buildShortestPath(MapGraph<Hub, Integer> graph, Hub hOrig, Hub hDest, int[] path) {
        int destIndex = graph.key(hDest);
        LinkedList<Hub> pathInOrder = new LinkedList<>();

        while (destIndex != -1 && !hOrig.equals(hDest)) {
            Hub vertex = graph.vertex(destIndex);

            int prevIndex = path[destIndex];
            if (prevIndex != -1) {

                pathInOrder.addFirst(vertex);

            }

            destIndex = prevIndex;
        }
        return pathInOrder;

    }

    public Map<Edge, Double> shortestPathCarVei(LinkedList<Hub> pathInOrder, double autonomia, Hub hOrigMax) {
        Hub prevVertex = null;
        Map<Edge, Double> edgeWeightMap = new TreeMap<>(new EdgeComparator());

        double sum = 0;
        double sumAutonomia = 0;
        int count = 0;
        if (!pathInOrder.isEmpty()) {
            Edge<Hub,Integer> firstEdge = graph.edge(hOrigMax, pathInOrder.getFirst());
            int firstEdgeWeight =  firstEdge.getWeight();
            sum = sum + firstEdgeWeight;
            Map<Edge, Double> map = new HashMap<>();
            map.put(firstEdge, sum);
            edgeWeightMap.put(firstEdge, sum);
            System.out.println("Edge: " + hOrigMax.getLocalId() + " to " + pathInOrder.getFirst().getLocalId() + " Weight: " + sum);
        }

        for (Hub vertex : pathInOrder) {
            if (prevVertex != null) {
                Edge<Hub, Integer> edge = graph.edge(prevVertex, vertex);
                int edgeWeight =  edge.getWeight();
                sum = sum + edgeWeight;
                sumAutonomia = sumAutonomia + edgeWeight;
                if (autonomia < edgeWeight) {
                    count++;
                }
                if (sumAutonomia > autonomia && autonomia > edgeWeight) {
                    System.out.println("Carregue o veículo na rede de distribuição: " + prevVertex.getLocalId());
                    sumAutonomia = edgeWeight;
                }
                edgeWeightMap.put(edge, sum);
                System.out.println("Edge: " + prevVertex.getLocalId() + " to " + vertex.getLocalId() + " Weight: " + sum);
            }
            prevVertex = vertex;
        }
        if (count > 0) {
            System.out.println("Não é possível fazer esta viagem com um veículo com esta autonomia.");
        }
        List<Map.Entry<Edge, Double>> list = new ArrayList<>(edgeWeightMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        // Criar um LinkedHashMap para manter a ordem de inserção após a classificação
        Map<Edge, Double> sortedEdgeWeightMap = new LinkedHashMap<>();
        for (Map.Entry<Edge, Double> entry : list) {
            sortedEdgeWeightMap.put(entry.getKey(), entry.getValue());
        }

        return sortedEdgeWeightMap;
    }

    public class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge edge1, Edge edge2) {

            // Aqui está a comparar com base no peso da aresta
            int weight1 = (int) edge1.getWeight();
            int weight2 = (int) edge2.getWeight();

            if (weight1 < weight2) {
                return -1;
            } else if (weight1 > weight2) {
                return 1;
            } else {
                return edge1.toString().compareTo(edge2.toString());
            }
        }
    }

}
