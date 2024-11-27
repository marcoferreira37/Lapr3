package LAPR.ui.menu;



import ESINF.Domain.*;
import ESINF.Functionality.FindAllPathsFromALocationToHub;
import ESINF.Functionality.ImportFileUSEI11;
import ESINF.Graph.Map.MapGraph;
import ESINF.IO.InputInfo;
import LAPR.ui.*;
import LAPR.ui.utils.Utils;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenuUI implements Runnable {

    public MainMenuUI(){
    }

    @Override
    public void run() {
        List<String> options = new ArrayList<String>();
        options.add("LAPR");
        options.add("ESINF");
        options.add("Exit");
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                if (option == 0){
                    menuLAPR();
                }
                else if (option == 1){
                    try {
                        menuESINF();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (option == 2){
                    System.out.println("A sair da aplicação...");
                    break;
                }
            }
        } while (option != -1);

    }

    public void menuLAPR(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Database Connection Test", new DatabaseConnectionTestUI()));
        options.add(new MenuItem("Register Semeadura Operation", new OperacaoSemeaduraUI()));
        options.add(new MenuItem("Register Monda Operation", new OperacaoMondaUI()));
        options.add(new MenuItem("Register Colheita Operation", new OperacaoColheitaUI()));
        options.add(new MenuItem("Register Aplicação Fator Produção Operation", new OperacaoAplicaçãoFatorProduçãoUI()));
        options.add(new MenuItem("Register Fertirrega Operation", new OperacaoFertirregaUI()));
        options.add(new MenuItem("Show list of biggest consume of water for year", new MaiorConsumoAguaUI()));
        options.add(new MenuItem("Obtain substancies not used in a year", new ObterSubstanciasUI()));
        options.add(new MenuItem("Irrigation controller simulation", new WateringPlanningUI()));



        options.add(new MenuItem("Exit", new ExitUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nLAPR Main Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
    private void menuESINF() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        MapGraph<Hub, Integer> mapGraph = null;
        Hub startVertex=null;
        List<String> options = new ArrayList<String>();
        options.add("USEI01 - Carregar dados de um ficheiro de texto e criar um grafo");
        options.add("USEI02 - Calcular a influencia, proximidade e centralidade de um hub");
        options.add("USEI03 - Calcular a menor distância para os hubs mais afastados");
        options.add("USEI04 - Criar um grafo com os hubs mais próximos");
        options.add("USEI06 - Calcular todas as distâsncias entre um local e um hub dentro de uma certa autonomia");
        options.add("USEI08 - Encontrar o melhor circuito de entrega");
        options.add("USEI11 - Mudar time window dos hubs a partir de um ficheiro de texto");
        options.add("USEI09 - Organizar as localidades do grafo em N clusters ");
        options.add("USEI07 - O percurso de entrega que maximiza o número de hubs pelo qual passa");
        options.add("Exit");
        int option = 0;

        boolean us02Execute = false;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nESINF - Main Menu");
            option++;
            if ((option >= 0) && (option < options.size()+1)) {
                if (option == 1){
                    System.out.println("USES01");
                    Scanner scan = new Scanner(System.in);
                    boolean flag = true;
                    while (flag) {
                        System.out.println("Pretende carregar o ficheiro pequeno(1) ou o grande(2)?");
                        int option2 = scan.nextInt();
                        if (option2 == 1 || option2 == 2) {
                         mapGraph = InputInfo.USEI01(option2);
                            flag = false;
                            if (option2 == 1){
                                System.out.println("Ficheiro pequeno carregado com sucesso");
                            }
                            else {
                                System.out.println("Ficheiro grande carregado com sucesso");
                            }
                        } else {
                            System.out.println("Opção inválida, tente novamente\n");
                        }
                    }
                }
                else if (option == 2){
                    if (mapGraph == null){
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    int n = Utils.readIntegerFromConsole("Insira o número de hubs que pretende analisar:");
                    HubLocationOptimizer.USES02(mapGraph,n);
                    us02Execute = true;
                }
                else if (option == 3){
                    if (mapGraph == null){
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    System.out.println("USES03");
                    US03 us03 = new US03(mapGraph);
                    us03.print();
                }
                else if (option == 4){
                    if (mapGraph == null){
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    MinSpanTreeUS04 minSpanTreeUs04 = new MinSpanTreeUS04(mapGraph);
                    minSpanTreeUs04.execute();

                }else if (option == 5){
                    if (mapGraph == null){
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    System.out.println("USES06");
                    if (us02Execute) {
                        FindAllPathsFromALocationToHub us06 = new FindAllPathsFromALocationToHub(mapGraph);
                        us06.execute();
                    } else {
                        System.out.println("É necessário executar a US02 primeiro");
                        break;
                    }

                } else if (option == 6) {
                    if (mapGraph == null) {
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    System.out.println("USES08");
                    FindCircuitUS08 us08 = new FindCircuitUS08(mapGraph);

                    us08.printCircuit();
                }else if (option == 7){
                    if (mapGraph == null){
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    System.out.println("USES11");
                    ImportFileUSEI11 importFileUSEI11 = new ImportFileUSEI11(mapGraph);
                    importFileUSEI11.importFile("src/resources/ESINF/exemploUS11.txt");

                }else if (option == 8) {
                    if (mapGraph == null) {
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01) e definir o número de hubs na USES02");
                        break;
                    }
                    System.out.println("USES09");
                    HubForLocation hubForLocation = new HubForLocation(mapGraph);
                    hubForLocation.hubForLocation();

                }else if (option == 9){
                    if (mapGraph == null) {
                        System.out.println("É necessário carregar o ficheiro primeiro (USES01)");
                        break;
                    }
                    System.out.println("Por qual hub pretende começar?");
                    String hubIdProcurado = scanner.nextLine();

                    System.out.println("Por favor, insira o tempo de carga (no formato HH:MM): ");
                    String tempoCargaInput = scanner.nextLine();
                    LocalTime tempoCarga = LocalTime.parse(tempoCargaInput);
                    System.out.println("Por favor, insira o tempo de descarga (no formato HH:MM): ");
                    String tempoDescargaInput = scanner.nextLine();
                    LocalTime tempoDescarga = LocalTime.parse(tempoDescargaInput);
                    System.out.println("Por favor, insira a hora de início (no formato HH:MM): ");
                    String horaInicioInput = scanner.nextLine();
                    LocalTime horaInicio = LocalTime.parse(horaInicioInput);
                    System.out.println("Por favor, insira a velocidade (em km/h): ");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Por favor, insira a autonomia (em m): ");
                    int autonomia = scanner.nextInt();
                    scanner.nextLine();

                    for (Hub hub : mapGraph.vertices()) {
                        if (hub.getLocalId().equals(hubIdProcurado)) {
                            startVertex = hub;
                            break;
                        }
                    }
                    if (startVertex != null) {
                        US07 us07 = new US07();
                        us07.run(startVertex, horaInicio, tempoCarga, tempoDescarga, velocidade, autonomia, mapGraph);
                    }else {
                        System.out.println("Hub não encontrado para o ID fornecido.");
                    }
                }else if (option ==10){
                    System.out.println("A sair do menu ESINF...");
                    break;
                }
            }
        } while (option != -1);
    }

    public static void main(String[] args) {
        MainMenuUI menu = new MainMenuUI();
        menu.run();
    }
}
