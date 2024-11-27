package LAPR.Controller;

import LAPR.dataAcess.OperacaoMondaRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoMondaController {
    private OperacaoMondaRepository operacaoMondaRepository;
    public OperacaoMondaController(){
        getOperacaoMondaRepository();
    }
    private OperacaoMondaRepository getOperacaoMondaRepository(){
        if(Objects.isNull(operacaoMondaRepository)){
            Repositories repositories= Repositories.getInstance();
            operacaoMondaRepository=repositories.getOperacaoMondaRepository();
        }
        return operacaoMondaRepository;
    }
    public void operacaoMondaRegister(Date data, String operacao, int quantidade, String unidade, String designacao, int idCultura, int id){
        try{
            operacaoMondaRepository.mondaRegister(data,operacao,quantidade,unidade,designacao,idCultura,id);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
