package ESINF.Functionality;

import ESINF.Domain.Coordinates;
import ESINF.Domain.Hub;
import ESINF.Graph.Map.MapGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ImportFileUSEI11Test {
    private static MapGraph<Hub, Integer> mapGraph = new MapGraph<>(false);
    @BeforeAll
    public static void setUpClass() {
        Hub hub1 = new Hub("CT1", new Coordinates(40.6389,-8.6553), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        Hub hub2 = new Hub("CT2", new Coordinates(38.0333,-7.8833), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        Hub hub3 = new Hub("CT3", new Coordinates(41.5333,-8.4167), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        mapGraph.addVertex(hub1);
        mapGraph.addVertex(hub2);
        mapGraph.addVertex(hub3);
    }

    @Test
    void importFile() throws FileNotFoundException {
        ImportFileUSEI11 importFileUSEI11 = new ImportFileUSEI11(mapGraph);
        importFileUSEI11.importFile("src/resources/ESINF/exemploUS11.txt");
        assertEquals(mapGraph.vertices().get(0).getStartingTime(), LocalDateTime.of(0,1,1,14,0));
        assertEquals(mapGraph.vertices().get(0).getEndingTime(), LocalDateTime.of(0,1,1,17,0));
        assertEquals(mapGraph.vertices().get(1).getStartingTime(), LocalDateTime.of(0,1,1,9,0));
        assertEquals(mapGraph.vertices().get(1).getEndingTime(), LocalDateTime.of(0,1,1,11,0));
        assertEquals(mapGraph.vertices().get(2).getStartingTime(), LocalDateTime.of(0,1,1,11,0));
        assertEquals(mapGraph.vertices().get(2).getEndingTime(), LocalDateTime.of(0,1,1,15,30));

    }
}
