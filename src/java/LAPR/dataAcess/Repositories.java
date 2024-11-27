package LAPR.dataAcess;

public class Repositories {

    private static final Repositories instance = new Repositories();

    private OperacaoSemeaduraRepository operacaoSemeaduraRepository = new OperacaoSemeaduraRepository();

    private OperacaoMondaRepository operacaoMondaRepository = new OperacaoMondaRepository();

    private OperacaoColheitaRepository operacaoColheitaRepository = new OperacaoColheitaRepository();
    private OperacaoAplicaçãoFatorProduçãoRepository operacaoAplicaçãoFatorProducaoRepository = new OperacaoAplicaçãoFatorProduçãoRepository();

    public static Repositories getInstance() {
        return instance;
    }

    public OperacaoSemeaduraRepository getOperacaoSemeaduraRepository() {
     return operacaoSemeaduraRepository;
    }

    public OperacaoMondaRepository getOperacaoMondaRepository() { return  operacaoMondaRepository ;}

    public OperacaoColheitaRepository getOperacaoColheitaRepository() { return  operacaoColheitaRepository ;}
    public OperacaoFertirregaRepository getReceitaRepository() { return  new OperacaoFertirregaRepository() ;}
    public MaiorConsumoAguaRepository getMaiorConsumoAguaRepository() { return  new MaiorConsumoAguaRepository() ;}
    public ObterSubstanciasRepository getObterSubstanciasRepository() { return  new ObterSubstanciasRepository() ;}

    public OperacaoAplicaçãoFatorProduçãoRepository getOperacaoAplicaçãoFatorProducaoRepository() { return  operacaoAplicaçãoFatorProducaoRepository ;}
}
