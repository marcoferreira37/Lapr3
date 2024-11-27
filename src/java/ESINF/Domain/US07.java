package ESINF.Domain;

import ESINF.Graph.Edge;
import ESINF.Graph.Map.MapGraph;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.*;

public class US07 {
    public void run(Hub startVertex, LocalTime horaDeInicio, LocalTime tempoDeCarga, LocalTime tempoDeDescarga, int velocidade, int autonomia, MapGraph<Hub,Integer> graph){
        Set<Hub> visited= new HashSet<>();
        List<Hub> currentPath =new ArrayList<>();
        List<Hub> biggestPath =new ArrayList<>();
        Map<List<Hub>, String> info = new HashMap<>();
        String infoOfLocal= "";
        int ct=0, ctCarregamentos=0;
        double distanciaTotal=0;
        autonomia = autonomia*100;
        info=Us07DepthFirstSearch(startVertex, visited, currentPath,biggestPath,horaDeInicio,horaDeInicio,tempoDeDescarga, tempoDeCarga,velocidade,autonomia,autonomia, ctCarregamentos, info, distanciaTotal, graph, ct,infoOfLocal );


        System.out.println("Locais:"+ info.keySet().iterator().next().toString()+ "\n");
        System.out.println("Hora de partida de "+ startVertex.getLocalId() + ": "+ horaDeInicio.plusHours(tempoDeCarga.getHour()).plusMinutes(tempoDeCarga.getMinute())+ "\n" + info.entrySet().iterator().next().getValue().toString());
    }

    public Map<List<Hub>,String> Us07DepthFirstSearch (Hub currentVertex, Set<Hub> visitedVertex, List<Hub> currentPath, List<Hub> biggestPath, LocalTime horaInicio, LocalTime horaTotal, LocalTime tempoDescarga, LocalTime tempoCarga, int velocidade, double autonomiaTotal, double currentAutonomia, int ctCarregamentos, Map <List<Hub>, String> informacao, double totalDistance, MapGraph<Hub, Integer> graph, int ct, String info){
        visitedVertex.add(currentVertex);
        currentPath.add(currentVertex);
        LocalTime horaTotal1;
        LocalTime tempoDeChegarHub;
        ct++;
        boolean exists = false, flag2 =false;
        for (Edge<Hub,Integer> edge : graph.edges()) {
            if(edge.getWeight()< currentAutonomia && !visitedVertex.contains(edge.getVDest())){
                exists=true;
                break;
            }
        }
        if(!exists){
            currentAutonomia = autonomiaTotal;
            ctCarregamentos++;
        }
        for (Hub vizinho: graph.adjVertices(currentVertex)){
            if(ct==1){
                horaTotal1=horaTotal.plusHours(tempoCarga.getHour()).plusMinutes(tempoCarga.getMinute());
                ctCarregamentos++;
            } else{
                horaTotal1=horaTotal;
            }
            double dist = graph.edge(currentVertex, vizinho).getWeight();
            double tempo = (dist/1000)/velocidade;
            int hora = (int) tempo;
            int minuto = (int) (tempo%1 *60);
            tempoDeChegarHub = horaTotal1.plusHours(hora).plusMinutes(minuto);
            if(!visitedVertex.contains(vizinho)&& tempoDeChegarHub.isAfter(vizinho.getStartingTime().toLocalTime()) && tempoDeChegarHub.isBefore((vizinho.getEndingTime().toLocalTime()))){
                if(currentAutonomia >= dist){
                    flag2=true;
                    horaTotal1=horaTotal1.plusHours(hora).plusMinutes(minuto);
                    info+= " Hora de chegada ao Hub "+ vizinho.getLocalId()+": "+ horaTotal1;
                    horaTotal1= horaTotal1.plusHours(tempoDescarga.getHour()).plusMinutes(tempoDescarga.getMinute());
                    info+= "\n Hora de partida do Hub "+vizinho.getLocalId()+ ": "+ horaTotal1;
                    info+= "\n-\n";
                    totalDistance += dist;
                    currentAutonomia -= dist;
                    Us07DepthFirstSearch(vizinho, new HashSet<>(visitedVertex),currentPath,biggestPath, horaInicio, horaTotal1,tempoDescarga, tempoCarga, velocidade, autonomiaTotal, currentAutonomia,ctCarregamentos, informacao, totalDistance, graph,ct,info );
                    if(currentPath.size() > biggestPath.size()){
                        biggestPath.clear();
                        biggestPath.addAll(currentPath);
                        LocalTime tempoDescargaTotal=tempoDescarga;
                        LocalTime tempoCargaTotal = tempoCarga;
                        LocalTime tempoTotal=horaTotal1.minusHours(horaInicio.getHour()).minusMinutes(horaInicio.getMinute());
                        for (int i = 1; i<ctCarregamentos;i++) {
                            tempoCargaTotal=tempoCargaTotal.plusHours(tempoCarga.getHour()).plusMinutes(tempoCarga.getMinute());
                        }
                        //começa em 2 porque o 1º não descarrega e o segundo já é contado ao iniciar a variável
                        for (int i = 2; i < currentPath.size() ; i++) {
                            tempoDescargaTotal=tempoDescargaTotal.plusHours(tempoDescarga.getHour()).plusMinutes(tempoDescarga.getMinute());
                        }
                        info+=("\nDistância total= "+ totalDistance + "\nNúmero de carregamentos= " + ctCarregamentos + "\nTempo total= "+ tempoTotal+ "\nTempo em carga= "+ tempoCargaTotal+ " \nTempo em descarga= "+ tempoDescargaTotal+ "\nTempo no percurso= "+ tempoTotal.minusHours(tempoCargaTotal.getHour()).minusHours(tempoDescargaTotal.getHour()).minusMinutes(tempoCargaTotal.getMinute()).minusMinutes(tempoDescargaTotal.getMinute()));
                        informacao.remove(biggestPath);
                        informacao.put(new ArrayList<>(currentPath), info);
                        info="";
                        totalDistance=0;
                    }
                }
            }

        }
        if(!flag2){
            currentPath.remove(currentVertex);
        }
        visitedVertex.remove(currentVertex);
        return informacao;
    }
}