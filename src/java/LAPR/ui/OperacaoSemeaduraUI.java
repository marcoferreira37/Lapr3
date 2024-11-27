package LAPR.ui;

import LAPR.Controller.OperacaoSemeaduraController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OperacaoSemeaduraUI implements Runnable{
    private OperacaoSemeaduraController controller;
    public OperacaoSemeaduraUI(){
        controller=new OperacaoSemeaduraController();
    }
    public void run(){
        try{
            System.out.println("Register a new Semeadura Operation:");

            Scanner scanner = new Scanner (System.in);

            System.out.println("data:(dd-mm-yyyy):");
            String data= scanner.next();
            SimpleDateFormat formatData=new SimpleDateFormat("dd-MM-yyyy");

            Date dataOperacao = formatData.parse(data);

            String operacao="Semeadura";

            System.out.println("quantidade:");
            int quantidade=scanner.nextInt();

            System.out.println("unidade:");
            String unidade=scanner.next();

            String designacao=null;

            System.out.println("idCultura:");
            int idCultura=scanner.nextInt();

            System.out.println("id:");
            int id=scanner.nextInt();

            controller.operacaoSemeaduraRegister(dataOperacao,operacao,quantidade,unidade,designacao,idCultura,id);
            System.out.println("\n Semeadura Operation registered.");
        }catch (ParseException e){
            System.out.println("\nSemeadura Operation not registered!\n" + e.getMessage());
        }
    }

}
