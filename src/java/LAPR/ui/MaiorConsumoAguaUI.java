package LAPR.ui;

import LAPR.Controller.MaiorConsumoAguaController;

import java.util.Scanner;

public class MaiorConsumoAguaUI  implements Runnable  {

    private MaiorConsumoAguaController controller;
    public MaiorConsumoAguaUI(){
        controller = new MaiorConsumoAguaController();
    }
    public void run(){
        try{
            System.out.println("Verificar o maior consumo de água para um certo ano:");
            Scanner scanner = new Scanner (System.in);

            System.out.println("Ano desejado para verificação:");
            int ano = scanner.nextInt();

            controller.maiorConsumoAguaRegister(ano);
            System.out.println("\nConsumo de água verificado!\n" );

        }catch (Exception e){
            System.out.println("\nConsumo de água não verificado!\n" + e.getMessage());
        }
    }
}
