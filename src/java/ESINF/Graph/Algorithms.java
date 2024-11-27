package ESINF.Graph;


import ESINF.Domain.Hub;
import ESINF.Graph.Map.MapGraph;
import ESINF.Graph.Matrix.MatrixGraph;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * The type Algorithms.
 *
 * @author DEI -ISEP
 */
public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param <V>  the type parameter
     * @param <E>  the type parameter
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> result = new LinkedList<>();
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();

        if (g.validVertex(vert)) {
            queue.offer(vert);
            visited.add(vert);

            while (!queue.isEmpty()) {
                V currentVertex = queue.poll();
                result.add(currentVertex);

                for (Edge<V, E> edge : g.outgoingEdges(currentVertex)) {
                    V neighbor = edge.getVDest();

                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }


        }
        return result;
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vOrig vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs return LinkedList with vertices of depth-first search
     */
    public static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        visited[g.key(vOrig)] = true; // Mark the current vertex as visited
        qdfs.add(vOrig); // Add the current vertex to the result list

        for (V vAdj : g.adjVertices(vOrig)) {
            int index = g.key(vAdj);
            if (!visited[index]) {
                // Recursively perform DFS on adjacent vertices
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param <V>  the type parameter
     * @param <E>  the type parameter
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }

        boolean[] visited = new boolean[g.numVertices()]; // Array to track visited vertices
        LinkedList<V> qdfs = new LinkedList<>(); // Result list for DFS traversal

        DepthFirstSearch(g, vert, visited, qdfs);

        return qdfs;
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */

        private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
            visited[g.key(vOrig)] = true;
            path.add(vOrig);

            if (vOrig.equals(vDest)) {
                paths.add(new LinkedList<>(path));
            } else {
                for (V vAdj : g.adjVertices(vOrig)) {
                    int index = g.key(vAdj);
                    if (!visited[index]) {
                        allPaths(g, vAdj, vDest, visited, path, paths);
                    }
                }
            }

            visited[g.key(vOrig)] = false;
            path.removeLast();
        }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param <V>   the type parameter
     * @param <E>   the type parameter
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
            if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
                throw new IllegalArgumentException("Invalid vertices in the graph");
            }

            boolean[] visited = new boolean[g.numVertices()];
            LinkedList<V> path = new LinkedList<>();
            ArrayList<LinkedList<V>> paths = new ArrayList<>();

            allPaths(g, vOrig, vDest, visited, path, paths);

            return paths;
        }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> Map<V, LinkedList<V>> shortestPathDijkstra(Graph<V, E> g, V vOrig, Comparator<E> ce, BinaryOperator<E> sum, E zero, Map<V, LinkedList<V>> pathKeys, Map<V, E> dist) {
        boolean[] visited = new boolean[g.numVertices()];
        int vKey = g.key(vOrig);

        dist.clear();
        pathKeys.clear();

        for (int i = 0; i < g.numVertices(); i++) {
            pathKeys.put(g.vertex(i), null);
            dist.put(g.vertex(i), null);
        }
        dist.put(vOrig, zero);

        LinkedList<V> path = new LinkedList<>();
        path.add(vOrig);
        pathKeys.put(vOrig, path);

        while (vKey != -1) {
            vOrig = g.vertex(vKey);
            visited[vKey] = true;

            for (V vAdj : g.adjVertices(vOrig)) {
                int vKeyAdj = g.key(vAdj);
                Edge<V, E> edge = g.edge(vOrig, vAdj);

                if (!visited[vKeyAdj]) {
                    if (pathKeys.get(vAdj) == null) {
                        path = new LinkedList<>(pathKeys.get(vOrig));
                        path.add(vAdj);
                        pathKeys.put(vAdj, path);
                        dist.put(vAdj, sum.apply(dist.get(vOrig), edge.getWeight()));

                    } else {
                        E oldDist = dist.get(vAdj);
                        E newDist = sum.apply(dist.get(vOrig), edge.getWeight());
                        if (ce.compare(oldDist, newDist) > 0) {
                            dist.put(vAdj, sum.apply(dist.get(vOrig), edge.getWeight()));
                            path = new LinkedList<>(pathKeys.get(vOrig));
                            path.add(vAdj);
                            pathKeys.put(vAdj, path);
                        }
                    }
                }
            }
            E minDist = null;
            vKey = -1;
            for (int i = 0; i < g.numVertices(); i++) {
                V vertex = g.vertex(i);
                if (!visited[i] && pathKeys.get(vertex) != null) {
                    if (vKey == -1) {
                        minDist = dist.get(vertex);
                        vKey = i;
                    } else if (ce.compare(minDist, dist.get(vertex)) > 0) {
                        minDist = dist.get(vertex);
                        vKey = i;
                    }
                }
            }
        }
        return pathKeys;
    }


    /**
     * Shortest-path between two vertices
     *
     * @param <V>       the type parameter
     * @param <E>       the type parameter
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest, Comparator<E> ce, BinaryOperator<E> sum, E zero, LinkedList<V> shortPath) {
        int vOrigKey = g.key(vOrig);
        int vDestKey = g.key(vDest);

        shortPath.clear();

        if(vOrigKey == -1 || vDestKey == -1){
            return null;
        }

        if(vDestKey == vOrigKey ){
            shortPath.add(vOrig);
            return zero;
        }


        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        ArrayList<E> dists = new ArrayList<>();

        shortestPaths(g,vOrig,ce,sum,zero,paths,dists);

        if (dists.get(vDestKey) == null)
            return null;

        LinkedList<V> shortestPath = paths.get(vDestKey);

        shortPath.addAll(shortestPath);

        return dists.get(vDestKey);
    }


    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param <V>   the type parameter
     * @param <E>   the type parameter
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, Comparator<E> ce, BinaryOperator<E> sum, E zero, ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        Map<V, E> distMap = new HashMap<>();
        Map<V,LinkedList<V>> pathKeys = new HashMap<>();
        shortestPathDijkstra(g,vOrig,ce,sum,zero, pathKeys, distMap);
        paths.clear();
        dists.clear();

        for(int x = 0; x < g.numVertices(); x++){
            V v = g.vertex(x);

            paths.add(pathKeys.get(v));

            dists.add(distMap.get(v));
        }
        return true;
    }


    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V [] pathKeys, LinkedList<V> path) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param <V> the type parameter
     * @param <E> the type parameter
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V,E> MatrixGraph<V,E> minDistGraph(Graph <V,E> g, Comparator<E> ce, BinaryOperator<E> sum) {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Min span tree map graph.
     *
     * @param <V>  the type parameter
     * @param <E>  the type parameter
     * @param g    the g
     * @param ce   the ce
     * @param sum  the sum
     * @param zero the zero
     * @return the map graph
     */
//USEI04
    public static <V, E> MapGraph<V, E> minSpanTree(MapGraph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum, E zero) {
        Set<V> visitedVertices = new HashSet<>();

        PriorityQueue<Edge<V, E>> edgeQueue = new PriorityQueue<>(Comparator.comparing(e -> e.getWeight(), ce));

        MapGraph<V, E> minimumSpanningTree = new MapGraph<>(true);

        V startVertex = g.vertices().iterator().next();
        visitedVertices.add(startVertex);

        for (Edge<V, E> edge : g.outgoingEdges(startVertex)) {
            edgeQueue.add(edge);
        }

        while (visitedVertices.size() < g.numVertices()) {

            Edge<V, E> minEdge = edgeQueue.poll();

            if (minEdge == null) {
                break;
            }


            V destVertex = minEdge.getVDest();

            if (!visitedVertices.contains(destVertex)) {

                visitedVertices.add(destVertex);


                minimumSpanningTree.addEdge(minEdge.getVOrig(), destVertex, minEdge.getWeight());

                for (Edge<V, E> edge : g.outgoingEdges(destVertex)) {
                    edgeQueue.add(edge);
                }
            }
        }
        return minimumSpanningTree;
    }
}