package LAPR.ui;

import LAPR.Controller.OperacaoFertirregaController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OperacaoFertirregaUI implements Runnable{
    private OperacaoFertirregaController controller;
    public OperacaoFertirregaUI(){
        controller = new OperacaoFertirregaController();
    }
    public void run(){
        try{
            System.out.println("Register a recipe:");

            Scanner scanner = new Scanner (System.in);

            System.out.println("data:(dd-mm-yyyy):");
            String data= scanner.next();
            SimpleDateFormat formatData = new SimpleDateFormat("dd-MM-yyyy");
            Date dataOperacao = formatData.parse(data);

            System.out.println("quantidade:");
            int quantidade=scanner.nextInt();

            System.out.println("unidade:");
            scanner.nextLine();
            String unidade=scanner.nextLine();

            System.out.println("designação:");
            String designacao= scanner.nextLine();

            System.out.println("idCultura:");
            int idCultura=scanner.nextInt();

            System.out.println("idParcela:");
            int id=scanner.nextInt();

            System.out.println("idSetor:");
            int idSetor=scanner.nextInt();

            System.out.println("idReceita:");
            int idReceita=scanner.nextInt();

            System.out.println("horas:");
            double horas = scanner.nextDouble();

            System.out.println("duração:");
            int duracao=scanner.nextInt();

            controller.fertirregaRegister(dataOperacao, quantidade, unidade, designacao, idCultura, id, idSetor, idReceita, horas, duracao);
            System.out.println("\n Operação Fertirrega registada.");

        }catch (ParseException e){
            System.out.println("\n Operação Fertirrega não registada!\n" + e.getMessage());
        }
    }

}
