package ESINF.Domain;

import ESINF.Graph.Graph;
import ESINF.Graph.Map.MapGraph;
import ESINF.Graph.Matrix.MatrixGraph;
import ESINF.IO.InputInfo;

import java.io.FileNotFoundException;

public class HubNetwork {
    private static MapGraph<Hub, Integer> hubs = new MapGraph<>(false);

    public HubNetwork(int option) throws FileNotFoundException {
        this.hubs = InputInfo.importMapGraph(option);
    }

    public void addHub(Hub hub) {
        hubs.addVertex(hub);
    }

    public void removeHub(Hub hub) {
        hubs.removeVertex(hub);
    }

    public void addConnection(Hub hub1, Hub hub2, double distance) {
        hubs.addEdge(hub1, hub2, (int) distance);
    }

    public void removeConnection(Hub hub1, Hub hub2) {
        hubs.removeEdge(hub1, hub2);
    }

    public static MapGraph<Hub, Integer> getHubs() {
        return hubs;
    }
}
