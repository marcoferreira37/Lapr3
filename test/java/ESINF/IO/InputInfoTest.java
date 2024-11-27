package ESINF.IO;

import ESINF.Domain.Coordinates;
import ESINF.Domain.Hub;
import ESINF.Graph.Edge;
import ESINF.Graph.Graph;
import ESINF.Graph.Map.MapGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public class InputInfoTest {

    @Test
    public void testImportMapGraph() {
        try {
            MapGraph<Hub, Integer> mapGraph = InputInfo.importMapGraph(1);

            // Verify that the mapGraph is not null
            Assertions.assertNotNull(mapGraph);

            // Verify that the mapGraph contains the expected vertices
            Collection<Hub> vertices = mapGraph.vertices();
            Assertions.assertEquals(17, vertices.size());

            // Verify that the mapGraph contains the expected edges
            Collection<Edge<Hub, Integer>> edges = mapGraph.edges();
            Assertions.assertEquals(66, edges.size());

            // Verify that the mapGraph has the correct adjacency vertices
            Hub hubCT1 = new Hub("CT1", new Coordinates(40.6389, -8.6553), LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),9,0), LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),14,0));
            Hub hubCT10 = new Hub("CT10", new Coordinates(39.7444, -8.8072),LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),9,0), LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),14,0));
            Hub hubCT12 = new Hub("CT12", new Coordinates(41.1495, -8.6108),LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),9,0), LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),14,0));
            Hub hubCT13 = new Hub("CT13", new Coordinates(39.2369, -8.685),LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),9,0), LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),LocalDateTime.now().getDayOfMonth(),14,0));

            Assertions.assertTrue(mapGraph.adjVertices(hubCT1).contains(hubCT10));
            Assertions.assertTrue(mapGraph.adjVertices(hubCT1).contains(hubCT12));
            Assertions.assertTrue(mapGraph.adjVertices(hubCT10).contains(hubCT13));
            Assertions.assertTrue(mapGraph.adjVertices(hubCT12).contains(hubCT1));


        } catch (FileNotFoundException e) {
            Assertions.fail("File not found exception occurred");
        }
    }
}
