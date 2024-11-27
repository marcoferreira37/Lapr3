package LAPR.Controller;

import LAPR.dataAcess.OperacaoAplicaçãoFatorProduçãoRepository;
import LAPR.dataAcess.OperacaoColheitaRepository;
import LAPR.dataAcess.Repositories;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class OperacaoAplicaçãoFatorProduçãoController{
        private OperacaoAplicaçãoFatorProduçãoRepository operacaoAplicaçãoFatorProducaoRepository;
        public OperacaoAplicaçãoFatorProduçãoController(){
            getOperacaoColheitaRepository();
        }
        private OperacaoAplicaçãoFatorProduçãoRepository getOperacaoColheitaRepository(){
            if(Objects.isNull(operacaoAplicaçãoFatorProducaoRepository)){
                Repositories repositories= Repositories.getInstance();
                operacaoAplicaçãoFatorProducaoRepository=repositories.getOperacaoAplicaçãoFatorProducaoRepository();
            }
            return  operacaoAplicaçãoFatorProducaoRepository;
        }
        public void operacaoAplicaçãoFatorProduçãoRegister(Date data, String operacao, int quantidade, String unidade, String designacao,
                                             int idCultura, int id,String modo,int area,String unidadeDeArea ){
            try{
                OperacaoAplicaçãoFatorProduçãoRepository.aplicaçãoFatorProduçãoRegister(data,operacao,quantidade,unidade,designacao,idCultura,id,modo,area,unidadeDeArea);
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        }


}
