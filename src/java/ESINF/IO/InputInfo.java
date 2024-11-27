package ESINF.IO;

import ESINF.Domain.Coordinates;
import ESINF.Domain.Hub;
import ESINF.Graph.Graph;
import ESINF.Graph.Map.MapGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputInfo {
    public static void importData(Graph<Hub, Integer> graph, int option) throws FileNotFoundException {
        Map<String, Hub> map = new HashMap<>();
        File fileLocais;
        File fileDistancias;
        switch (option){
            case 1:
                fileLocais = new File("src/resources/ESINF/locais_small.csv");
                fileDistancias = new File("src/resources/ESINF/distancias_small.csv");
                break;
            case 2:
                 fileLocais = new File("src/resources/ESINF/locais_big.csv");
                 fileDistancias = new File("src/resources/ESINF/distancias_big.csv");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

        Scanner input = new Scanner(fileLocais);
        String header = input.nextLine();
        while (input.hasNextLine()){
            String info = input.nextLine();
            String[] line = info.split(",");
            String hubNumberInString = line[0];
            int hubNumber = Integer.parseInt(hubNumberInString.substring(2));
            LocalDateTime startingTime = null;
            LocalDateTime endingTime = null;
           if (hubNumber <= 105) {
               startingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 9, 0);
               endingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 14, 0);
           } else if ( hubNumber <= 216 && hubNumber > 105) {
                startingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 11, 0);
                endingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 16, 0);
           }else if (hubNumber <= 323 && hubNumber > 216) {
                startingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 12, 0);
                endingTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 17, 0);
           }
            Hub h = new Hub(line[0], new Coordinates(Double.parseDouble(line[1]), Double.parseDouble(line[2])), startingTime, endingTime);
            boolean f = graph.addVertex(h);
            if (f) {
                map.put(h.getLocalId(), h);
            }
        }
        input.close();
        input = new Scanner(fileDistancias);
        header = input.nextLine();
        while (input.hasNextLine()){
            String info = input.nextLine();
            String[] line = info.split(",");
            Hub v1 = map.get(line[0]);
            Hub v2 = map.get(line[1]);
            int weight = Integer.parseInt(line[2]);
            graph.addEdge(v1, v2, weight);
        }
        input.close();
    }
    public static MapGraph<Hub, Integer> importMapGraph(int option) throws FileNotFoundException {
        MapGraph<Hub, Integer> result = new MapGraph<>(false);
        importData(result, option);
        return result;
    }
    public static MapGraph<Hub, Integer> USEI01(int option) throws FileNotFoundException {
        MapGraph<Hub, Integer> mapGraph = importMapGraph(option);
        return mapGraph;
    }
}
