package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Map.MapGraph;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinSpanTreeUS04Test {


    @Test
    void minSpanTree_NegativeWeights() {
        MapGraph<Hub, Integer> graphWithNegWeights = new MapGraph<>(false);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        Hub C = new Hub("CT10", new Coordinates(39.7444,-8.8072),null, null);
        graphWithNegWeights.addVertex(A);
        graphWithNegWeights.addVertex(B);
        graphWithNegWeights.addVertex(C);
        graphWithNegWeights.addEdge(A, B, -2);
        graphWithNegWeights.addEdge(A, C, 3);
        graphWithNegWeights.addEdge(B, C, 2);

        MapGraph<Hub, Integer> minSpanTree = Algorithms.minSpanTree(graphWithNegWeights, Integer::compare, Integer::sum, 0);

        assertNotNull(minSpanTree);
        assertEquals(graphWithNegWeights.numVertices(), minSpanTree.numVertices());
        assertEquals(graphWithNegWeights.numVertices() - 1, minSpanTree.numEdges());
    }
    @Test
    void minSpanTree_EqualWeights() {
        MapGraph<Hub, Integer> graphWithEqualWeights = new MapGraph<>(false);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        Hub C = new Hub("CT10", new Coordinates(39.7444,-8.8072),null, null);
        graphWithEqualWeights.addVertex(A);
        graphWithEqualWeights.addVertex(B);
        graphWithEqualWeights.addVertex(C);
        graphWithEqualWeights.addEdge(A, B, 2);
        graphWithEqualWeights.addEdge(A, C, 2);
        graphWithEqualWeights.addEdge(B, C, 2);

        MapGraph<Hub, Integer> minSpanTree = Algorithms.minSpanTree(graphWithEqualWeights, Integer::compare, Integer::sum, 0);

        assertNotNull(minSpanTree);
        assertEquals(graphWithEqualWeights.numVertices(), minSpanTree.numVertices());
        assertEquals(graphWithEqualWeights.numVertices() - 1, minSpanTree.numEdges());
    }
    @Test
    void minSpanTree_ConnectedGraph() {
        MapGraph<Hub, Integer> connectedGraph = new MapGraph<>(false);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        Hub C = new Hub("CT10", new Coordinates(39.7444,-8.8072),null, null);
        Hub D = new Hub("CT14", new Coordinates(38.5243,-8.8926),null, null);
        connectedGraph.addVertex(A);
        connectedGraph.addVertex(B);
        connectedGraph.addVertex(C);
        connectedGraph.addVertex(D);
        connectedGraph.addEdge(A, B, 1);
        connectedGraph.addEdge(A, C, 3);
        connectedGraph.addEdge(B, C, 2);
        connectedGraph.addEdge(B, D, 4);
        connectedGraph.addEdge(C, D, 5);

        MapGraph<Hub, Integer> minSpanTree = Algorithms.minSpanTree(connectedGraph, Integer::compare, Integer::sum, 0);

        assertNotNull(minSpanTree);
        assertEquals(connectedGraph.numVertices(), minSpanTree.numVertices());
        assertEquals(connectedGraph.numVertices() - 1, minSpanTree.numEdges());
    }

    @Test
    void minSpanTree_LargeGraph() {
        MapGraph<Integer, Double> largeGraph = new MapGraph<>(false);
        for (int i = 1; i <= 1000; i++) {
            largeGraph.addVertex(i);
        }
        for (int i = 1; i <= 1000; i++) {
            for (int j = i + 1; j <= 1000; j++) {
                double weight = Math.random() * 100;
                largeGraph.addEdge(i, j, weight);
            }
        }

        MapGraph<Integer, Double> minSpanTree = Algorithms.minSpanTree(largeGraph, Double::compare, Double::sum, 0.0);

        assertNotNull(minSpanTree);
        assertEquals(largeGraph.numVertices(), minSpanTree.numVertices());
        assertEquals(largeGraph.numVertices() - 1, minSpanTree.numEdges());
    }
    @Test
    void minSpanTree_DuplicateEdges() {
        MapGraph<Hub, Integer> graphWithDuplicates = new MapGraph<>(false);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        Hub C = new Hub("CT10", new Coordinates(39.7444,-8.8072),null, null);
        graphWithDuplicates.addVertex(A);
        graphWithDuplicates.addVertex(B);
        graphWithDuplicates.addVertex(C);
        graphWithDuplicates.addEdge(A, B, 2);
        graphWithDuplicates.addEdge(A, B, 3);
        graphWithDuplicates.addEdge(A, C, 4);

        MapGraph<Hub, Integer> minSpanTree = Algorithms.minSpanTree(graphWithDuplicates, Integer::compare, Integer::sum, 0);

        assertNotNull(minSpanTree);
        assertEquals(graphWithDuplicates.numVertices(), minSpanTree.numVertices());
        assertEquals(graphWithDuplicates.numVertices() - 1, minSpanTree.numEdges());
    }

    @Test
    void minSpanTree_UndirectedGraph() {
        MapGraph<Hub, Integer> undirectedGraph = new MapGraph<>(true);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        Hub C = new Hub("CT10", new Coordinates(39.7444,-8.8072),null, null);
        undirectedGraph.addVertex(A);
        undirectedGraph.addVertex(B);
        undirectedGraph.addVertex(C);
        undirectedGraph.addEdge(A, B, 2);
        undirectedGraph.addEdge(A, C, 3);
        undirectedGraph.addEdge(B, C, 1);

        MapGraph<Hub, Integer> minSpanTree = Algorithms.minSpanTree(undirectedGraph, Integer::compare, Integer::sum, 0);

        assertNotNull(minSpanTree);
        assertEquals(undirectedGraph.numVertices(), minSpanTree.numVertices());
        assertEquals(undirectedGraph.numVertices() - 1, minSpanTree.numEdges());
    }

    @Test
    void minSpanTree_NonNumericWeights() {
        MapGraph<Hub, String> graphWithNonNumericWeights = new MapGraph<>(false);
        Hub A = new Hub("CT1", new Coordinates(40.6389,-8.6553),null, null );
        Hub B = new Hub ("CT13", new Coordinates(39.2369,-8.685),null, null);
        graphWithNonNumericWeights.addVertex(A);
        graphWithNonNumericWeights.addVertex(B);
        graphWithNonNumericWeights.addEdge(A, B, "low");

        MapGraph<Hub, String> minSpanTree = Algorithms.minSpanTree(graphWithNonNumericWeights, String::compareTo, String::concat, "");

        assertNotNull(minSpanTree);
        assertEquals(graphWithNonNumericWeights.numVertices(), minSpanTree.numVertices());
        assertEquals(graphWithNonNumericWeights.numVertices() - 1, minSpanTree.numEdges());
    }
}