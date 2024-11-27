package ESINF.Domain;

import ESINF.Domain.Coordinates;
import ESINF.Domain.Hub;
import ESINF.Domain.HubLocationOptimizer;
import ESINF.Domain.HubOptimizationCriteria;
import ESINF.Graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import ESINF.Graph.Map.MapGraph;
import ESINF.IO.InputInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class HubLocationOptimizerTest {
    private Graph<Hub, Integer> graph = new MapGraph<>(false);
    @BeforeEach
    void setUp() throws FileNotFoundException {

        graph = InputInfo.importMapGraph(1);
    }

    @Test
    void optimizeHubs() {
        List<HubOptimizationCriteria> optimizationCriteria = HubLocationOptimizer.optimizeHubs(graph);

        Assertions.assertEquals(17, optimizationCriteria.size());

        Hub hubCT1 = new Hub("CT1", new Coordinates(40.6389, -8.6553), null, null);
        Hub hubCT10 = new Hub("CT10", new Coordinates(39.7444, -8.8072),null, null);
        Hub hubCT13 = new Hub("CT13", new Coordinates(39.2369, -8.685),null, null);


        HubOptimizationCriteria criteriaA = optimizationCriteria.get(0);
        Assertions.assertEquals(hubCT10, criteriaA.getHub());
        Assertions.assertEquals(5, criteriaA.getInfluence());
        Assertions.assertEquals(2979452, criteriaA.getProximity());
        Assertions.assertEquals(107, criteriaA.getCentrality());

        HubOptimizationCriteria criteriaB = optimizationCriteria.get(1);
        Assertions.assertEquals(hubCT13, criteriaB.getHub());
        Assertions.assertEquals(4, criteriaB.getInfluence());
        Assertions.assertEquals(3335004, criteriaB.getProximity());
        Assertions.assertEquals(101, criteriaB.getCentrality());

        HubOptimizationCriteria criteriaC = optimizationCriteria.get(2);
        Assertions.assertEquals(hubCT1, criteriaC.getHub());
        Assertions.assertEquals(4, criteriaC.getInfluence());
        Assertions.assertEquals(3005527, criteriaC.getProximity());
        Assertions.assertEquals(85, criteriaC.getCentrality());


    }

    @Test
    void orderListByDecreasinglyOrderOfCentralityAndInfluence() {
        List<HubOptimizationCriteria> optimizationCriteria = new ArrayList<>();
        Hub hubCT1 = new Hub("CT1", new Coordinates(40.6389, -8.6553),null, null);
        Hub hubCT10 = new Hub("CT10", new Coordinates(39.7444, -8.8072),null, null);
        Hub hubCT13 = new Hub("CT13", new Coordinates(39.2369, -8.685),null, null);

        HubOptimizationCriteria criteriaA = new HubOptimizationCriteria(hubCT1);
        criteriaA.setInfluence(4);
        criteriaA.setProximity(3005527);
        criteriaA.setCentrality(85);

        HubOptimizationCriteria criteriaB = new HubOptimizationCriteria(hubCT10);
        criteriaB.setInfluence(5);
        criteriaB.setProximity(2979452);
        criteriaB.setCentrality(85);

        HubOptimizationCriteria criteriaC = new HubOptimizationCriteria(hubCT13);
        criteriaC.setInfluence(3);
        criteriaC.setProximity(1);
        criteriaC.setCentrality(100);

        optimizationCriteria.add(criteriaA);
        optimizationCriteria.add(criteriaB);
        optimizationCriteria.add(criteriaC);

        optimizationCriteria = HubLocationOptimizer.orderListByDecreasinglyOrderOfCentralityAndInfluence(optimizationCriteria);

        assertEquals(criteriaC, optimizationCriteria.get(0));
        assertEquals(criteriaB, optimizationCriteria.get(1));
        assertEquals(criteriaA, optimizationCriteria.get(2));
    }
    @Test
    void calculateCentrality() {
        Hub hubCT1 = new Hub("CT1", new Coordinates(40.6389, -8.6553),null, null);
        Hub hubCT10 = new Hub("CT10", new Coordinates(39.7444, -8.8072),null, null);
        Hub hubCT13 = new Hub("CT13", new Coordinates(39.2369, -8.685),null, null);
        Hub hubCT14 = new Hub("CT14", new Coordinates(39.2369, -8.685),null, null);

        MapGraph<Hub, Integer> graph = new MapGraph<>(false);

        graph.addVertex(hubCT1);
        graph.addVertex(hubCT10);
        graph.addVertex(hubCT13);
        graph.addVertex(hubCT14);

        graph.addEdge(hubCT1, hubCT10, 1);
        graph.addEdge(hubCT1, hubCT13, 1);
        graph.addEdge(hubCT10, hubCT13, 1);
        graph.addEdge(hubCT10, hubCT14, 1);
        graph.addEdge(hubCT13, hubCT14, 1);

        double centrality = HubLocationOptimizer.calculateCentrality(graph, hubCT1);
        assertEquals(7, centrality);
        centrality = HubLocationOptimizer.calculateCentrality(graph, hubCT10);
        assertEquals(9, centrality);

    }
}
