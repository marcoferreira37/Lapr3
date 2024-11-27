package LAPR.Controller;

import LAPR.dataAcess.OperacaoSemeaduraRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoSemeaduraController {
    private OperacaoSemeaduraRepository operacaoSemeaduraRepository;
    public OperacaoSemeaduraController(){
        getOperacaoSemeaduraRepository();
    }
    private OperacaoSemeaduraRepository getOperacaoSemeaduraRepository(){
        if(Objects.isNull(operacaoSemeaduraRepository)){
            Repositories repositories= Repositories.getInstance();
            operacaoSemeaduraRepository=repositories.getOperacaoSemeaduraRepository();
        }
        return operacaoSemeaduraRepository;
    }
    public void operacaoSemeaduraRegister(Date data, String operacao, int quantidade, String unidade, String designacao, int idCultura, int id){
        try{
            operacaoSemeaduraRepository.semeaduraRegister(data,operacao,quantidade,unidade,designacao,idCultura,id);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
