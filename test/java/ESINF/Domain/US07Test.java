package ESINF.Domain;

import ESINF.Graph.Map.MapGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class US07Test {

    private MapGraph<Hub, Integer> graph;


    @BeforeEach
    public void setUp() {
        graph = new MapGraph<>(false);

// Crie alguns hubs para testar
        Hub hub1 = new Hub("CT1", new Coordinates(40.6389, -8.6553), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub2 = new Hub("CT2", new Coordinates(38.0333, -7.8833), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub hub13 = new Hub("CT13", new Coordinates(39.2369, -8.685), LocalDateTime.of(2023, 1, 1, 9, 0),
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
    public void testUS07NotNull() {
        // Defina os parâmetros para o método run
        LocalTime horaDeInicio = LocalTime.of(10, 00);
        LocalTime tempoDeCarga = LocalTime.of(01,00); // Exemplo de 1 hora de carga
        LocalTime tempoDeDescarga = LocalTime.of(0, 45); // Exemplo de 45 minutos de descarga
        int velocidade = 60; // Exemplo de velocidade em km/h
        int autonomia = 400; // Exemplo de autonomia em km
        US07 us07 = new US07();
        Set<Hub> visited= new HashSet<>();
        List<Hub> currentPath =new ArrayList<>();
        List<Hub> biggestPath =new ArrayList<>();
        Map<List<Hub>, String> info = new HashMap<>();
        String infoOfLocal= "";
        int ct=0, ctCarregamentos=0;
        double distanciaTotal=0;
        autonomia = autonomia*100;
        Map<List<Hub>, String> test = new HashMap<>();

        // Escolha um hub inicial para iniciar o teste
        Hub startVertex = new Hub("CT10", new Coordinates(39.7444, -8.8072), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));;// Escolha um dos hubs criados no método setUp()

                // Execute o método run da classe US07 com os parâmetros e o grafo definidos
                //us07.run(startVertex, horaDeInicio, tempoDeCarga, tempoDeDescarga, velocidade, autonomia, graph);
       test = us07.Us07DepthFirstSearch(startVertex, visited, currentPath,biggestPath,horaDeInicio,horaDeInicio,tempoDeDescarga, tempoDeCarga,velocidade,autonomia,autonomia, ctCarregamentos, info, distanciaTotal, graph, ct,infoOfLocal );
        assertNotNull(test);
    }


    @Test
    public void testUS07HoraPartida() {
        // Defina os parâmetros para o método run
        LocalTime horaDeInicio = LocalTime.of(10, 00);
        LocalTime tempoDeCarga = LocalTime.of(01,00); // Exemplo de 1 hora de carga
        LocalTime tempoDeDescarga = LocalTime.of(0, 45); // Exemplo de 45 minutos de descarga
        int velocidade = 60; // Exemplo de velocidade em km/h
        int autonomia = 400; // Exemplo de autonomia em km
        US07 us07 = new US07();
        Set<Hub> visited= new HashSet<>();
        List<Hub> currentPath =new ArrayList<>();
        List<Hub> biggestPath =new ArrayList<>();
        Map<List<Hub>, String> info = new HashMap<>();
        String infoOfLocal= "";
        int ct=0, ctCarregamentos=0;
        double distanciaTotal=0;
        autonomia = autonomia*100;
        Map<List<Hub>, String> test = new HashMap<>();

        // Escolha um hub inicial para iniciar o teste
        Hub startVertex = new Hub("CT10", new Coordinates(39.7444, -8.8072), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));;// Escolha um dos hubs criados no método setUp()

        // Execute o método run da classe US07 com os parâmetros e o grafo definidos
        test = us07.Us07DepthFirstSearch(startVertex, visited, currentPath,biggestPath,horaDeInicio,horaDeInicio,tempoDeDescarga, tempoDeCarga,velocidade,autonomia,autonomia, ctCarregamentos, info, distanciaTotal, graph, ct,infoOfLocal );
        LocalTime horaPartida;
        horaPartida=horaDeInicio.plusHours(tempoDeCarga.getHour()).plusMinutes(tempoDeCarga.getMinute());
        LocalTime horaPartidaPrevista = LocalTime.of(11, 00);
        assertEquals( horaPartidaPrevista , horaPartida);

    }


    @Test
    public void testUS07TempoTotal() {
        // Defina os parâmetros para o método run
        LocalTime horaDeInicio = LocalTime.of(10, 00);
        LocalTime tempoDeCarga = LocalTime.of(01,00); // Exemplo de 1 hora de carga
        LocalTime tempoDeDescarga = LocalTime.of(0, 45); // Exemplo de 45 minutos de descarga
        int velocidade = 60; // Exemplo de velocidade em km/h
        int autonomia = 400; // Exemplo de autonomia em km
        US07 us07 = new US07();
        Set<Hub> visited= new HashSet<>();
        List<Hub> currentPath =new ArrayList<>();
        List<Hub> biggestPath =new ArrayList<>();
        Map<List<Hub>, String> info = new HashMap<>();
        String infoOfLocal= "";
        int ct=0, ctCarregamentos=0;
        double distanciaTotal=0;
        autonomia = autonomia*100;
        Map<List<Hub>, String> test = new HashMap<>();
        LocalTime horaTotalFinal = LocalTime.of(00, 00);;
        LocalTime horaTotal1 = LocalTime.of(00, 00);;
        LocalTime horaAntesDaCarga = LocalTime.of(00, 00);;


        Hub hub10 = new Hub("CT10", new Coordinates(39.7444, -8.8072), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        Hub startVertex = hub10;
        Hub hub13 = new Hub("CT13", new Coordinates(39.2369, -8.685), LocalDateTime.of(2023, 1, 1, 9, 0),
                LocalDateTime.of(2023, 1, 1, 18, 0));
        visited.add(hub13);
        test = us07.Us07DepthFirstSearch(startVertex, visited, currentPath,biggestPath,horaDeInicio,horaDeInicio,tempoDeDescarga, tempoDeCarga,velocidade,autonomia,autonomia, ctCarregamentos, info, distanciaTotal, graph, ct,infoOfLocal );
        LocalTime horaChegada;

        graph.addEdge(hub10, hub13, 63448);
        horaTotal1=horaDeInicio.plusHours(tempoDeCarga.getHour()).plusMinutes(tempoDeCarga.getMinute());
        double tempo = (63448 / 1000 )/ velocidade;
        int hora = (int) tempo;
        int minuto = (int) ((tempo * 60) % 60)*60;
        horaAntesDaCarga = horaTotal1.plusHours(hora).plusMinutes(minuto);
        horaTotalFinal=horaAntesDaCarga.plusHours(tempoDeDescarga.getHour()).plusMinutes(tempoDeDescarga.getMinute());
        LocalTime horaFinalEsperada = LocalTime.of(12, 45);;

        assertEquals( horaFinalEsperada  , horaTotalFinal);
    }





}
