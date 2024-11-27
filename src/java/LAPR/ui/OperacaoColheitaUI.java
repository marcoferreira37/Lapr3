package LAPR.ui;

import LAPR.Controller.OperacaoColheitaController;
import LAPR.Controller.OperacaoMondaController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OperacaoColheitaUI implements Runnable{
    private OperacaoColheitaController controller;
    public OperacaoColheitaUI(){
        controller = new OperacaoColheitaController();
    }
    public void run(){
        try{
            System.out.println("Register a new Colheita Operation:");

            Scanner scanner = new Scanner (System.in);

            System.out.println("data:(dd-mm-yyyy):");
            String data= scanner.next();
            SimpleDateFormat formatData=new SimpleDateFormat("dd-MM-yyyy");

            Date dataOperacao = formatData.parse(data);
            String operacao = "Colheita";

            System.out.println("quantidade:");
            int quantidade=scanner.nextInt();

            System.out.println("unidade:");
            String unidade=scanner.next();

            String designacao= null;

            System.out.println("idCultura:");
            int idCultura=scanner.nextInt();

            System.out.println("idParcela:");
            int id=scanner.nextInt();

            controller.operacaoColheitaRegister(dataOperacao,operacao,quantidade,unidade,designacao,idCultura,id);
            System.out.println("\n Colheita Operation registered.");

        }catch (ParseException e){
            System.out.println("\nColheita Operation not registered!\n" + e.getMessage());
        }
    }

}
