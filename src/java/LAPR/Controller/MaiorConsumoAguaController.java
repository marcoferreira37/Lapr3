package LAPR.Controller;

import LAPR.dataAcess.MaiorConsumoAguaRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Objects;

public class MaiorConsumoAguaController {
    private MaiorConsumoAguaRepository maiorConsumoAguaRepository;

    public MaiorConsumoAguaController() {
        getMaiorConsumoAguaRepository();
    }

    private MaiorConsumoAguaRepository getMaiorConsumoAguaRepository() {
        if (Objects.isNull(maiorConsumoAguaRepository)) {
            Repositories repositories = Repositories.getInstance();
            maiorConsumoAguaRepository = repositories.getMaiorConsumoAguaRepository();
        }
        return maiorConsumoAguaRepository;
    }

    public void maiorConsumoAguaRegister(int ano) {
        try {
            maiorConsumoAguaRepository.maiorConsumoAguaRegister(ano);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
