package ESINF.Domain;

import ESINF.Graph.Map.MapGraph;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.time.LocalDateTime;


import static org.junit.Assert.*;


public class FindCircuitUS08Test {
    private MapGraph<Hub, Integer> graph;
    int nHubs = 0;

    @Before
    public void setUp() {
        graph = new MapGraph<>(false);

        // Crie alguns hubs para testar
        Hub hub1 = new Hub("CT1", new Coordinates(40.6389, -8.6553), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub2 = new Hub("CT2", new Coordinates(38.0333, -7.8833), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub3 = new Hub("CT3", new Coordinates(39.2369, -8.685), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub14 = new Hub("CT14", new Coordinates(38.5243, -8.8926), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub5 = new Hub("CT5", new Coordinates(39.823, -7.4931), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub7 = new Hub("CT7", new Coordinates(38.5667, -7.9), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub10 = new Hub("CT10", new Coordinates(39.7444, -8.8072), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub8 = new Hub("CT8", new Coordinates(37.0161, -7.935), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub15 = new Hub("CT15", new Coordinates(41.7, -8.8333), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));


        graph.addVertex(hub1);
        graph.addVertex(hub2);
        graph.addVertex(hub3);
        graph.addVertex(hub14);
        graph.addVertex(hub5);
        graph.addVertex(hub7);
        graph.addVertex(hub10);
        graph.addVertex(hub8);
        graph.addVertex(hub15);

        graph.addEdge(hub10, hub1, 110848);
        graph.addEdge(hub10, hub5, 125041);
        graph.addEdge(hub10, hub3, 63448);
        graph.addEdge(hub14, hub2, 114913);
        graph.addEdge(hub14, hub7, 95957);
        graph.addEdge(hub3, hub7, 111686);
        graph.addEdge(hub2, hub7, 65574);
        graph.addEdge(hub2, hub8, 125105);
        graph.addEdge(hub15, hub3, 43598);
        graph.addEdge(hub2, hub15, 70717);

        nHubs = 4;

    }

    @Test
    public void testConstructor() {
        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);
        assertNotNull(circuitFinder);
    }

    @Test
    public void testPrintCircuitForId14() {

        String input = "4\nct2\n400\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);
        circuitFinder.printCircuit();

        double totalDist = circuitFinder.getTotalDist();
        double temp = circuitFinder.calcTempoCircuito(totalDist);
        int carregadores = circuitFinder.calcNumeroCarregadores(400);
        Hub destination = circuitFinder.getDestination();

        double delta = 0.0;
        assertEquals(605.61, totalDist, delta);
        assertEquals(10.09, temp, delta);
        assertEquals(2, carregadores, delta);
        assertEquals("CT2", destination.getLocalId());
    }


    //ver as contas pq este grafo é mais pequeno
    @Test
    public void testPrintCircuitForId1() {
        String input = "2\nct1\n200\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);
        circuitFinder.printCircuit();

        double totalDist = circuitFinder.getTotalDist();
        double temp = circuitFinder.calcTempoCircuito(totalDist);
        int carregadores = circuitFinder.calcNumeroCarregadores(200);
        Hub destination = circuitFinder.getDestination();

        double delta = 0.0;
        assertEquals(471.78, totalDist, delta);
        assertEquals(7.86, temp, delta);
        assertEquals(3, carregadores, delta);
        assertEquals("CT1", destination.getLocalId());
    }

    @Test
    public void testCaminhoIda() {
        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);

        // Ensure that the graph has at least 4 vertices
        assertTrue(graph.numVertices() >= nHubs);

        Hub origin = graph.vertices().get(1);
        circuitFinder.setnHubs(nHubs);

        // Call the method to be tested
        Hub result = circuitFinder.caminhoIda(origin);

        assertNotNull(result);
        assertTrue(graph.validVertex(result));

    }
    @Test
    public void testCaminhoIda2() {
        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);

        // Ensure that the graph has at least 4 vertices
        assertTrue(graph.numVertices() >= nHubs);

        Hub origin = graph.vertices().get(0);
        circuitFinder.setnHubs(2);

        // Call the method to be tested
        Hub result = circuitFinder.caminhoIda(origin);

        assertNotNull(result);
        assertTrue(graph.validVertex(result));

    }

    @Test
    public void testCaminhoVolta() {
        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);

        Hub originStore = graph.vertices().get(4);
        circuitFinder.setOriginStore(originStore);

        Hub previous = graph.vertices().get(1);

        circuitFinder.caminhoVolta(previous);
        Hub destination = circuitFinder.getDestination();

        assertEquals("CT5", destination.getLocalId());

    }
    @Test
    public void testCaminhoVolta2() {
        FindCircuitUS08 circuitFinder = new FindCircuitUS08(graph);

        Hub originStore = graph.vertices().get(1);
        circuitFinder.setOriginStore(originStore);

        Hub previous = graph.vertices().get(6);

        circuitFinder.caminhoVolta(previous);
        Hub destination = circuitFinder.getDestination();

        assertEquals("CT2", destination.getLocalId());

    }

}
