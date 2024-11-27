package LAPR.domain;

public class OperacaoSemeadura {
    private int operacaoId;

    public OperacaoSemeadura(int operacaoId){
        this.operacaoId=operacaoId;
    }
    public int getOperacaoId(){
        return operacaoId;
    }
    public boolean isValid(){
        return true;
    }
}
