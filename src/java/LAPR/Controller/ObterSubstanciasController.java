package LAPR.Controller;

import LAPR.dataAcess.Repositories;
import LAPR.dataAcess.ObterSubstanciasRepository;

import java.sql.SQLException;
import java.util.Objects;

public class ObterSubstanciasController {
    private ObterSubstanciasRepository obterSubstanciasRepository;

    public ObterSubstanciasController() {
        getObterSubstanciasRepository();
    }

    private ObterSubstanciasRepository getObterSubstanciasRepository() {
        if (Objects.isNull(obterSubstanciasRepository)) {
            Repositories repositories = Repositories.getInstance();
            obterSubstanciasRepository = repositories.getObterSubstanciasRepository();
        }
        return obterSubstanciasRepository;
    }

    public void obterSubstanciasRegister(int ano) {
        try {
            obterSubstanciasRepository.obterSubstanciasRegister(ano);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
