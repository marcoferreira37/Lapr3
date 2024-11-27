package ESINF.Domain;

import ESINF.Graph.Map.MapGraph;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HubForLocationTest {

    private MapGraph<Hub, Integer> graph;


    @BeforeEach
    public void setUp() {
        graph = new MapGraph<>(false);

// Crie alguns hubs para testar
        Hub hub1 = new Hub("CT1", new Coordinates(40.6389, -8.6553), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub2 = new Hub("CT2", new Coordinates(38.0333, -7.8833), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub13 = new Hub("CT3", new Coordinates(39.2369, -8.685), LocalDateTime.of(2023, 1, 1, 9, 0),
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

        // Adicione os hubs ao grafo
        graph.addVertex(hub1);
        graph.addVertex(hub2);
        graph.addVertex(hub13);
        graph.addVertex(hub14);
        graph.addVertex(hub5);
        graph.addVertex(hub7);
        graph.addVertex(hub10);
        graph.addVertex(hub8);



        // Adicione algumas arestas (exemplo: conexões entre hubs)
        graph.addEdge(hub10, hub1, 110848);
        graph.addEdge(hub10, hub5, 125041);
        graph.addEdge(hub10, hub13, 63448);
        graph.addEdge(hub14, hub2, 114913);
        graph.addEdge(hub14, hub7, 95957);
        graph.addEdge(hub13, hub7, 111686);
        graph.addEdge(hub2, hub7, 65574);
        graph.addEdge(hub2, hub8, 125105);

        hub2.promoteToHub();
        hub10.promoteToHub();


    }


    @Test
    public void testConstructor() {
        HubForLocation hubForLocation = new HubForLocation(graph);
        assertNotNull(hubForLocation);
    }

    @Test
    public void testHubForLocationNotNull() {
        HubForLocation hubForLocation = new HubForLocation(graph);

        // Verificar se os clusters foram gerados
        Map<Hub, Set<Hub>> clustersMap = hubForLocation.clusterize(graph);
        assertNotNull(clustersMap);

     }

    @Test
    public void testHubForLocation() {
        HubForLocation hubForLocation = new HubForLocation(graph);

        // Verificar se os clusters foram gerados
        Map<Hub, Set<Hub>> clustersMap = hubForLocation.clusterize(graph);
        int numberOfClusters = clustersMap.size();
        assertEquals(2, numberOfClusters);

    }

    @Test
    public void testNumberOfLocationsInClusterHub10() {
        HubForLocation hubForLocation = new HubForLocation(graph);

        // Verificar se os clusters foram gerados
        Map<Hub, Set<Hub>> clustersMap = hubForLocation.clusterize(graph);

        Hub hub10 = null;
        for (Hub hub : graph.vertices()) {
            if (hub.getLocalId().equals("CT10")) { // Verifica se o identificador é "CT10" para o hub10
                hub10 = hub;
                break;
            }
        }

        Set<Hub> locationsInClusterOfHub10 = clustersMap.get(hub10);
        int actualNumberOfLocationsInClusterOfHub10 = locationsInClusterOfHub10 != null ? locationsInClusterOfHub10.size() : 0;
        assertEquals(4, actualNumberOfLocationsInClusterOfHub10);

    }



}