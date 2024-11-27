package ESINF.Functionality;

import ESINF.Domain.Coordinates;
import ESINF.Domain.Hub;
import ESINF.Graph.Map.MapGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindAllPathsFromALocationToHubTest {

    private static MapGraph<Hub, Integer> mapGraph = new MapGraph<>(false);
    @BeforeAll
    public static void setUpClass() {
        Hub hub1 = new Hub("CT1", new Coordinates(40.6389,-8.6553), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        Hub hub2 = new Hub("CT2", new Coordinates(38.0333,-7.8833), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        Hub hub3 = new Hub("CT3", new Coordinates(41.5333,-8.4167), LocalDateTime.of(0,1,1,9,0), LocalDateTime.of(0,1,1,11,0));
        Hub hubCT10 = new Hub("CT10", new Coordinates(39.7444, -8.8072),null, null);
        Hub hubCT13 = new Hub("CT13", new Coordinates(39.2369, -8.685),null, null);

        mapGraph.addVertex(hub1);
        mapGraph.addVertex(hub2);
        mapGraph.addVertex(hub3);
        mapGraph.addVertex(hubCT10);
        mapGraph.addVertex(hubCT13);

        mapGraph.addEdge(hub1, hub2, 8000);
        mapGraph.addEdge(hub1, hub3, 2000);
        mapGraph.addEdge(hub2, hub3, 5000);
        mapGraph.addEdge(hub2, hubCT10, 1000);
        mapGraph.addEdge(hub3, hubCT10, 1546);
        mapGraph.addEdge(hub3, hubCT13, 7445);

        hub1.promoteToHub();
        hub2.promoteToHub();
        hub3.promoteToHub();

    }

    @Test
    void seperateLocalsAndHubs() {
        FindAllPathsFromALocationToHub test = new FindAllPathsFromALocationToHub(mapGraph);
        List<Hub> locals = new ArrayList<>();
        List <Hub> hubs = new ArrayList<>();


        test.seperateLocalsAndHubs(locals, hubs);
        assertEquals(2,locals.size());
        assertEquals(3,hubs.size());
    }

    @Test
    void calculateDistance() {
        FindAllPathsFromALocationToHub test = new FindAllPathsFromALocationToHub(mapGraph);
        //Fill a linked list with a path of hubs in order to test the method
        LinkedList<Hub> path = new LinkedList<>();
        path.add(mapGraph.vertices().get(0));
        path.add(mapGraph.vertices().get(1));
        path.add(mapGraph.vertices().get(2));
        int distance = test.calculateDistance(path);

        assertEquals(130, distance);
    }

    @Test
    void calculateDistanceButThePathDoesntConnect() {
        FindAllPathsFromALocationToHub test = new FindAllPathsFromALocationToHub(mapGraph);
        //Fill a linked list with a path of hubs in order to test the method
        LinkedList<Hub> path = new LinkedList<>();
        path.add(mapGraph.vertices().get(0));
        path.add(mapGraph.vertices().get(1));
        path.add(mapGraph.vertices().get(4));
        int distance = test.calculateDistance(path);

        assertEquals(-1, distance);
    }
}