package LAPR.ui;
import LAPR.Controller.OperacaoAplicaçãoFatorProduçãoController;
import LAPR.Controller.OperacaoColheitaController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OperacaoAplicaçãoFatorProduçãoUI implements Runnable{
    private OperacaoAplicaçãoFatorProduçãoController controller;
    public OperacaoAplicaçãoFatorProduçãoUI(){
        controller = new OperacaoAplicaçãoFatorProduçãoController();
    }
    public void run(){
        try{
            System.out.println("Register a new Colheita Operation:");

            Scanner scanner = new Scanner (System.in);

            System.out.println("data:(dd-mm-yyyy):");
            String data= scanner.next();
            SimpleDateFormat formatData=new SimpleDateFormat("dd-MM-yyyy");

            Date dataOperacao = formatData.parse(data);

            String operacao = "Aplicacao_Fator_Producao";

            System.out.println("quantidade:");
            int quantidade=scanner.nextInt();

            System.out.println("unidade:");
            String unidade=scanner.next();
            scanner.nextLine();

            System.out.println("designação:");
            String designacao= scanner.nextLine();

            System.out.println("idCultura:");
            int idCultura=scanner.nextInt();

            System.out.println("idParcela:");
            int id=scanner.nextInt();

            System.out.println("Modo:");
            String modo=scanner.next();

            System.out.println("Area de aplicação:");
            int area=scanner.nextInt();

            System.out.println("Unidades de área:");
            String unidadeArea=scanner.next();

            controller.operacaoAplicaçãoFatorProduçãoRegister(dataOperacao,operacao,quantidade,unidade,designacao,idCultura,id,modo,area,unidadeArea);
            System.out.println("\n Aplicação Fator Produção Operation registered.");

        }catch (ParseException e){
            System.out.println("\nColheita Operation not registered!\n" + e.getMessage());
        }
    }
}
