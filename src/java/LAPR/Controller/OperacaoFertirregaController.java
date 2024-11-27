package LAPR.Controller;

import LAPR.dataAcess.OperacaoFertirregaRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoFertirregaController {
    private OperacaoFertirregaRepository operacaoFertirregaRepository;
    public OperacaoFertirregaController(){
        getFertirregaRepository();
    }
    private OperacaoFertirregaRepository getFertirregaRepository(){
        if(Objects.isNull(operacaoFertirregaRepository)){
            Repositories repositories = Repositories.getInstance();
            operacaoFertirregaRepository = repositories.getReceitaRepository();
        }
        return operacaoFertirregaRepository;
    }
    public void fertirregaRegister(Date data, int quantidade, String unidade, String designacao, int idCultura, int id,
                                   int idSetor, int idReceita, double horas, int duracao){
        try{
            operacaoFertirregaRepository.fertirregaRegister(data, quantidade, unidade, designacao, idCultura, id, idSetor, idReceita, horas, duracao);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    }
