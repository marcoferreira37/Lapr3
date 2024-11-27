package ESINF.Functionality;

import ESINF.Domain.Hub;
import ESINF.Graph.Map.MapGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ImportFileUSEI11 {
    private final MapGraph<Hub, Integer> mapGraph;

    public ImportFileUSEI11(MapGraph<Hub, Integer> mapGraph) {
        this.mapGraph = mapGraph;
    }

    /**
        * Import a file with the information of the users.
        *
        * @param filename name of the file to import
        * @return void
        */
        public void importFile(String filename) throws FileNotFoundException {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String id = parts[0];
                String[] startingTime = parts[1].split(":");
                String[] endingTime = parts[2].split(":");
                boolean found = false;
                for (Hub hub: mapGraph.vertices()) {
                    if (hub.getLocalId().equals(id)){
                        hub.setStartingTime(LocalDateTime.of(0,1,1,Integer.parseInt(startingTime[0]),Integer.parseInt(startingTime[1])));
                        hub.setEndingTime(LocalDateTime.of(0,1,1,Integer.parseInt(endingTime[0]),Integer.parseInt(endingTime[1])));
                        found = true;
                    }
                }
                if (!found){
                    System.out.println("Hub" + id + "does not exist.");
                }

            }
            sc.close();
        }

}
