package ESINF.Domain;

import ESINF.Graph.Algorithms;
import ESINF.Graph.Edge;
import ESINF.Graph.Map.MapGraph;

import java.util.*;


/**
 * The type Find circuit us 08.
 */
public class FindCircuitUS08 {
    private MapGraph<Hub, Integer> graphLocations;
    private int autonomy = 0, colaboradores = 0, nHubs = 0;
    private double totalDist = 0;
    private Hub origin = null, originStore = null, destination = null;
    /**
     * The Scanner.
     */
    static Scanner scanner = new Scanner(System.in);


    /**
     * Instantiates a new Find circuit us 08.
     *
     * @param graphLocations the graph locations
     */
//construtor da us, recebe ografo criado a partir do ficheiro
    public FindCircuitUS08(MapGraph<Hub, Integer> graphLocations) {
        this.graphLocations = graphLocations;
    }


    /**
     * Print circuit.
     */
// método de print das informações da us
    public void printCircuit() {
        System.out.println("Insira o número de Hubs que deseja passar:");
        nHubs = scanner.nextInt();
        scanner.nextLine();

        origin = obterHugOrigin(); // Obter o hub de origem

        if (graphLocations.validVertex(origin)) { // verificar se é um hub válido
            System.out.println("Hub de origem :");
            printHub(origin);
            System.out.println();

            System.out.println("Circuito: ");
            System.out.println("   -----> Ida : ");
            origin = caminhoIda(origin);
            System.out.println();

            System.out.println("   -----> Volta : ");
            caminhoVolta(origin);
            System.out.println();
        } else {
            System.out.println("Id inválido");
        }

        totalDist = Math.round(totalDist * 100.0) / 100.0;
        System.out.println("Distância total : " + totalDist + "km");
        System.out.println("Tempo total do circuito : " + calcTempoCircuito(totalDist) + "h");
        System.out.println("Número de carregamentos : " + calcNumeroCarregadores(autonomy));
        System.out.println();
    }

    private Hub obterHugOrigin() {

        System.out.println("Locais: "); // Apresenta os hubs possíveis
        for (Hub hub : graphLocations.vertices()) {
            printHub(hub);
        }

        System.out.println("Escolha o local de origem: (introduza o id) ");
        String idHubO = scanner.nextLine();

        // Encontrar o hub de origem
        for (Hub hub : graphLocations.vertices()) {
            if (idHubO.equalsIgnoreCase(hub.getLocalId())) {
                origin = hub;
                originStore = hub;
                colaboradores = hub.getNumeroColaboradores();
                break;  //sai do loop assim que encontra a origem
            }
        }

        System.out.println("Introduza a autonomia do veículo: (Km) ");
        autonomy = scanner.nextInt();
        return origin;
    }


    /**
     * Caminho ida hub.
     *
     * @param origin the origin
     * @return the hub
     */
    public Hub caminhoIda(Hub origin) {

        Map<Hub, Boolean> isHub = new HashMap<>();

        Hub previousLocation = origin;
        checkHub(origin);
        for (int i = 0; i < nHubs; i++) {
                origin = edgeMaisColabs(origin, isHub); //encontrar o vertice com mais colaboradores
                isHub.putIfAbsent(origin, true);

                double distance = calcDistancia(origin, previousLocation) / 1000;
                totalDist += distance;

                System.out.println("    " + previousLocation.getLocalId() + " -> " + distance + "km -> " + origin.getLocalId());
                checkHub(origin);
                previousLocation = origin;
            }

        return origin;
    }

    /**
     * Caminho volta.
     *
     * @param previousLocation the previous location
     */
    public void caminhoVolta(Hub previousLocation) {
        destination = originStore;
        LinkedList<Hub> shortPath = new LinkedList<>();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        Algorithms.shortestPath(graphLocations, origin, destination, comparator, Integer::sum, 0, shortPath);
        String notLocation = previousLocation.getLocalId();

        if (!shortPath.isEmpty()) {
            checkHub(previousLocation);

            for (Hub hub : shortPath) {
                if (!hub.getLocalId().equals(notLocation)) {
                    double distance = calcDistancia(hub, previousLocation) / 1000;
                    totalDist += distance;

                    System.out.println("    " + previousLocation.getLocalId() + " -> " + distance + "km -> " + hub.getLocalId());

                    checkHub(hub);
                    previousLocation = hub;
                }
            }
        } else {
            System.out.println("Não existe caminho de volta");
        }
    }


    /**
     * Edge mais colabs hub.
     *
     * @param startLocation the start location
     * @param ishub         the ishub
     * @return the hub
     */
    public Hub edgeMaisColabs(Hub startLocation, Map<Hub, Boolean> ishub) {

        Collection<Edge<Hub, Integer>> edges;
        edges = graphLocations.outgoingEdges(startLocation);

        int maiorColab = -1;
        Hub maiorColabHub = null;

        for (Edge<Hub, Integer> edge : edges) {
            Hub destinationHub = edge.getVDest();

            if (!ishub.containsKey(destinationHub)) {
                int collabs = destinationHub.getNumeroColaboradores();
                if (collabs > maiorColab) {
                    maiorColab = collabs;
                    maiorColabHub = destinationHub;
                }
            }
        }
        return maiorColabHub;
    }

    /**
     * Calc distancia double.
     *
     * @param hub1 the hub 1
     * @param hub2 the hub 2
     * @return the double
     */
    public double calcDistancia(Hub hub1, Hub hub2) {

        double distance = 0.0;
        Collection<Edge<Hub, Integer>> edges = graphLocations.outgoingEdges(hub1);

        for (Edge<Hub, Integer> edge : edges) {
            if (edge.getVDest() == hub2) {
                distance = edge.getWeight();
            }
        }
        return distance;
    }

    /**
     * Calc tempo circuito double.
     *
     * @param distance the distance
     * @return the double
     */
    public double calcTempoCircuito(double distance) {
        return Math.round((distance / 60.0) * 100.0) / 100.0;
    }

    /**
     * Check hub.
     *
     * @param hub the hub
     */
    public void checkHub(Hub hub) {
        boolean flag = false;

        for (Hub hubCheck : graphLocations.vertices()) {
            String id = hubCheck.getLocalId();
            if (id.equals(hub.getLocalId())) {
                colaboradores = hub.getNumeroColaboradores();
                System.out.println(hub.getLocalId() + ", " + colaboradores + " colaboradores");
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println(hub.getLocalId() + ", not an hub");
        }
    }

    /**
     * Calc numero carregadores int.
     *
     * @param autonomy the autonomy
     * @return the int
     */
    public int calcNumeroCarregadores(int autonomy) {
        int nrCharges = (int) (totalDist / autonomy);
        double nrChargesDouble = totalDist / autonomy;

        if (nrChargesDouble > nrCharges) {
            nrCharges++;
        }

        return nrCharges;
    }

    /**
     * Print hub.
     *
     * @param hub the hub
     */
    public void printHub(Hub hub) {
        System.out.println(" ID: " + hub.getLocalId() +
                "\n coordinates: \n   latitude --> " + hub.getCoordinates().getLatitude() +
                "\n   longitude --> " + hub.getCoordinates().getLongitude() +
                "\n Starting time: " + hub.getStartingTime() +
                "\n Ending time: " + hub.getEndingTime() + "\n");
    }

    /**
     * Gets autonomy.
     *
     * @return the autonomy
     */
    public int getAutonomy() {
        return autonomy;
    }

    /**
     * Gets colaboradores.
     *
     * @return the colaboradores
     */
    public int getColaboradores() {
        return colaboradores;
    }

    /**
     * Gets hubs.
     *
     * @return the hubs
     */
    public int getnHubs() {
        return nHubs;
    }

    /**
     * Gets total dist.
     *
     * @return the total dist
     */
    public double getTotalDist() {
        return totalDist;
    }

    /**
     * Gets origin.
     *
     * @return the origin
     */
    public Hub getOrigin() {
        return origin;
    }

    /**
     * Gets origin store.
     *
     * @return the origin store
     */
    public Hub getOriginStore() {
        return originStore;
    }

    /**
     * Sets autonomy.
     *
     * @param autonomy the autonomy
     */
    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    /**
     * Sets colaboradores.
     *
     * @param colaboradores the colaboradores
     */
    public void setColaboradores(int colaboradores) {
        this.colaboradores = colaboradores;
    }

    /**
     * Sets hubs.
     *
     * @param nHubs the n hubs
     */
    public void setnHubs(int nHubs) {
        this.nHubs = nHubs;
    }

    /**
     * Sets total dist.
     *
     * @param totalDist the total dist
     */
    public void setTotalDist(double totalDist) {
        this.totalDist = totalDist;
    }

    /**
     * Sets origin.
     *
     * @param origin the origin
     */
    public void setOrigin(Hub origin) {
        this.origin = origin;
    }

    /**
     * Sets origin store.
     *
     * @param originStore the origin store
     */
    public void setOriginStore(Hub originStore) {
        this.originStore = originStore;
    }

    /**
     * Sets destination.
     *
     * @param destination the destination
     */
    public void setDestination(Hub destination) {
        this.destination = destination;
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public Hub getDestination() {
        return destination;
    }

}

