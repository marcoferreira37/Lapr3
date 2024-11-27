package ESINF.Functionality;

import ESINF.Domain.Hub;
import ESINF.Graph.Algorithms;
import ESINF.Graph.Map.MapGraph;
import LAPR.ui.utils.Utils;
import org.apache.velocity.util.introspection.ChainableUberspector;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PrimitiveIterator;

public class FindAllPathsFromALocationToHub {
    private static MapGraph<Hub, Integer> locations = new MapGraph<>(false);
    private static double velocity = 80; // V km/h

    public FindAllPathsFromALocationToHub(MapGraph<Hub, Integer> locations) {
        this.locations = locations;
    }

    public void execute() {
        List<Hub> locals= new ArrayList<>();
        List<Hub> hubs = new ArrayList<>();

        seperateLocalsAndHubs(locals, hubs);

        int n =   Utils.showAndSelectIndex(locals, "Select a location to find all paths to a hub");
        Hub origin = locals.get(n);

        if(!locations.validVertex(origin)){
            System.out.println("Invalid location");
            return;
        }

        n = Utils.showAndSelectIndex(hubs, "Select a hub");

        Hub destination = hubs.get(n);

        if(!locations.validVertex(destination)){
            System.out.println("Invalid hub: " + destination);
            return;
        }
        int autonomy = Utils.readIntegerFromConsole("Insert the autonomy of the vehicle in km:");
        ArrayList<LinkedList<Hub>> allPaths = Algorithms.allPaths(locations, origin, destination);
        for (LinkedList<Hub> path : allPaths) {
            int distance = calculateDistance(path);
            if (distance > autonomy) {
                allPaths.remove(path);
                continue;
            }
            int time = (int) (distance / velocity);

            writePath(path, distance, time);
        }

    }

    private void writePath(LinkedList<Hub> path, int distance, int time) {
        System.out.println("==============================================");
        System.out.println("Origin location: " + path.getFirst().getLocalId());
        System.out.println("Path: ");
        for (int i = 0; i < path.size() - 1; i++) {
            Hub local = path.get(i);
            Hub nextLocal = path.get(i + 1);
            String isHub ="";
            if (nextLocal.isPromoted()) isHub = "(HUB) ";
            System.out.print(local.getLocalId() +" -> " + isHub + nextLocal.getLocalId());
            System.out.println( " distance: (" + locations.edge(local, nextLocal).getWeight()/100 + "km) ");

        }
        System.out.println("Total Distance: " + distance + "km" + "  Time: " + time + "h");
        System.out.println("==============================================");
    }

    int calculateDistance(LinkedList<Hub> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Hub local = path.get(i);
            Hub nextLocal = path.get(i + 1);
            if (locations.edge(local, nextLocal) == null) {
                return -1;
            }
            distance += locations.edge(local, nextLocal).getWeight();
        }

        return distance/100;
    }

    void seperateLocalsAndHubs(List<Hub> locals, List<Hub> hubs) {
        for (Hub hub : locations.vertices()) {
            if (hub.isPromoted()) {
                hubs.add(hub);
            } else {
                locals.add(hub);
            }
        }
    }

}
