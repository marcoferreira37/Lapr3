package LAPR.Controller;

import LAPR.dataAcess.OperacaoColheitaRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoColheitaController {
    private OperacaoColheitaRepository operacaoColheitaRepository;
    public OperacaoColheitaController(){
        getOperacaoColheitaRepository();
    }
    private OperacaoColheitaRepository getOperacaoColheitaRepository(){
        if(Objects.isNull(operacaoColheitaRepository)){
            Repositories repositories= Repositories.getInstance();
            operacaoColheitaRepository=repositories.getOperacaoColheitaRepository();
        }
        return operacaoColheitaRepository;
    }
    public void operacaoColheitaRegister(Date data, String operacao, int quantidade, String unidade, String designacao,
                                         int idCultura, int id){
        try{
            operacaoColheitaRepository.colheitaRegister(data,operacao,quantidade,unidade,designacao,idCultura,id);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
