package ESINF.Domain;

public class HubOptimizationCriteria {
    private Hub hub;
    private double centrality;
    private double influence;
    private double proximity;

    public HubOptimizationCriteria(Hub hub) {
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    public double getCentrality() {
        return centrality;
    }

    public double getInfluence() {
        return influence;
    }

    public double getProximity() {
        return proximity;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public void setCentrality(double centrality) {
        this.centrality = centrality;
    }

    public void setInfluence(double influence) {
        this.influence = influence;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }

    @Override
    public String toString() {
        return "HubOptimizationCriteria{" +
                "hub=" + hub +
                ", centrality=" + centrality +
                ", influence=" + influence +
                ", proximity=" + proximity +
                '}';
    }
}
