package LAPR.domain;

import java.util.Date;

public class Operacao {
    private int operacaoId;
    private Date data;
    private String operacao;
    private int quantidade;
    private String unidade;
    private String designacao;
    private int idCultura;
    private int id;

    public Operacao(int operacaoId, Date data, String operacao, int quantidade, String unidade, String designacao, int idCultura, int id){
        this.operacaoId=operacaoId;
        this.data=data;
        this.operacao=operacao;
        this.quantidade=quantidade;
        this.unidade=unidade;
        this.designacao=designacao;
        this.idCultura=idCultura;
        this.id=id;
    }

    public int getOperacaoId(){
        return operacaoId;
    }

    public Date getData() {
        return data;
    }


    public String getOperacao() {
        return operacao;
    }


    public int getQuantidade() {
        return quantidade;
    }


    public String getUnidade() {
        return unidade;
    }



    public String getDesignacao() {
        return designacao;
    }



    public int getIdCultura() {
        return idCultura;
    }


    public int getId() {
        return id;
    }
    public boolean isValid(){
        return true;
    }
}
