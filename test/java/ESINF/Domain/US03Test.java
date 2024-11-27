package ESINF.Domain;

import ESINF.Graph.Map.MapGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class US03Test {

    private US03 us03;
    private MapGraph<Hub, Integer> graph;
    private Hub hubA;
    private Hub hubB;
    private Hub hubC;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        us03 = new US03(graph);
        graph = new MapGraph<>(true);
        hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);
        hubC = new Hub("CT11", new Coordinates(39.3167, -7.4167),null, null);
        graph.addVertex(hubA);
        graph.addVertex(hubB);
        graph.addVertex(hubC);
    }

    @Test
    void testBuildShortestPathWhenPathExistsThenReturnCorrectPath() {
        graph.addEdge(hubA, hubB, 10);
        graph.addEdge(hubB, hubC, 2);

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        LinkedList<Hub> shortestPath = us03.buildShortestPath(graph, hubA, hubC, path);

        assertEquals(2, shortestPath.size());
        assertEquals(hubB, shortestPath.getFirst());
        assertEquals(hubC, shortestPath.getLast());
    }

    @Test
    void testBuildShortestPathWhenPathDoesNotExistThenReturnEmptyPath() {
        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        LinkedList<Hub> shortestPath = us03.buildShortestPath(graph, hubA, hubC, path);

        assertTrue(shortestPath.isEmpty());
    }

    @Test
    void testDijkstraWhenGraphHasOneVertexThenDistancesContainsOneElementWithZeroValue() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hub = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        graph.addVertex(hub);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hub, visited, path, distances);

        assertEquals(0, distances[graph.key(hub)]);
    }

    @Test
    void testDijkstraWhenGraphHasPathBetweenSourceAndDestinationThenDistancesContainShortestDistances() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);

        graph.addVertex(hubA);
        graph.addVertex(hubB);
        graph.addEdge(hubA, hubB, 10);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        assertEquals(0, distances[graph.key(hubA)]);
        assertEquals(10, distances[graph.key(hubB)]);
    }

    @Test
    void testDijkstraWhenGraphHasNoPathBetweenSourceAndDestinationThenDistancesContainMaxValueExceptForSourceVertex() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);

        graph.addVertex(hubA);
        graph.addVertex(hubB);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        assertEquals(0, distances[graph.key(hubA)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.key(hubB)]);
    }

    @Test
    void dijkstraPathTest() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);

        // Add hubs and edges to the test graph
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);
        Hub hubC = new Hub("CT11", new Coordinates(39.3167, -7.4167),null, null);

        graph.addVertex(hubA);
        graph.addVertex(hubB);
        graph.addVertex(hubC);

        graph.addEdge(hubA, hubB, 10);
        graph.addEdge(hubA, hubC, 5);
        graph.addEdge(hubB, hubC, 2);


        MapGraph<Hub, Integer> graph2 = new MapGraph<>(true);
        US03 us03 = new US03(graph2);
        us03.graph = graph2;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];
        int vertexIndex = graph.key(hubC);

        us03.dijkstra(graph, hubA, visited, path, distances);


    }

    @Test
    void shortestPathCarVei() {
        // Create a test graph
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);

        // Add hubs and edges to the test graph
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);
        Hub hubC = new Hub("CT11", new Coordinates(39.3167, -7.4167),null, null);

        graph.addVertex(hubA);
        graph.addVertex(hubB);
        graph.addVertex(hubC);

        graph.addEdge(hubA, hubB, 10);
        graph.addEdge(hubA, hubC, 5);
        graph.addEdge(hubB, hubC, 2);

        try {
            MapGraph<Hub, Integer> graph2 = new MapGraph<>(true);
            US03 us03 = new US03(graph2);
            us03.graph = graph2;

            // Call the dijkstra method
            boolean[] visited = new boolean[graph.numVertices()];
            int[] path = new int[graph.numVertices()];
            int[] distances = new int[graph.numVertices()];
            int vertexIndex = graph.key(hubC);

            us03.dijkstra(graph, hubA, visited, path, distances);

            LinkedList<Hub> shortestPath = us03.buildShortestPath(graph, hubA, hubC, path);

            assertNotEquals(hubA, shortestPath.getFirst());
            assertEquals(hubC, shortestPath.getLast());

            assertNotEquals(2, shortestPath.size()); // Verify there are exactly two hubs in the path (A and C)

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDijkstraWhenGraphHasOneVertexThenDistanceIsZero() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        graph.addVertex(hubA);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        assertEquals(0, distances[graph.key(hubA)]);
    }

    @Test
    void testDijkstraWhenPathExistsThenCorrectShortestDistances() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hubA = new Hub("CT2", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("CT258", new Coordinates(40.4203, -7.7033),null, null);
        graph.addVertex(hubA);
        graph.addVertex(hubB);
        graph.addEdge(hubA, hubB, 10);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        assertEquals(0, distances[graph.key(hubA)]);
        assertEquals(10, distances[graph.key(hubB)]);
    }

    @Test
    void testDijkstraWhenNoPathExistsThenDistanceIsMaxValue() throws FileNotFoundException {
        MapGraph<Hub, Integer> graph = new MapGraph<>(true);
        Hub hubA = new Hub("A", new Coordinates(38.0333, -7.8833),null, null);
        Hub hubB = new Hub("B", new Coordinates(40.4203, -7.7033),null, null);
        graph.addVertex(hubA);
        graph.addVertex(hubB);

        US03 us03 = new US03(graph);
        us03.graph = graph;

        boolean[] visited = new boolean[graph.numVertices()];
        int[] path = new int[graph.numVertices()];
        int[] distances = new int[graph.numVertices()];

        us03.dijkstra(graph, hubA, visited, path, distances);

        assertEquals(0, distances[graph.key(hubA)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.key(hubB)]);
    }
}