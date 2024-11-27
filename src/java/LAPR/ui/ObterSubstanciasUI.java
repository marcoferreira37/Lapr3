package LAPR.ui;

import LAPR.Controller.MaiorConsumoAguaController;
import LAPR.Controller.ObterSubstanciasController;
import LAPR.dataAcess.ObterSubstanciasRepository;

import java.util.List;
import java.util.Scanner;

public class ObterSubstanciasUI  implements Runnable{

    private ObterSubstanciasController controller;
    private ObterSubstanciasRepository repository = new ObterSubstanciasRepository();

    public ObterSubstanciasUI(){
        controller = new ObterSubstanciasController();
    }
    public void run(){
        try{
            System.out.println("Verificar as substancias de FP nao usadas num ano:");
            Scanner scanner = new Scanner (System.in);

            System.out.println("Ano desejado para verificação:");
            int ano = scanner.nextInt();

            controller.obterSubstanciasRegister(ano);
            System.out.println(repository.obterSubstanciasRegister(ano));
            System.out.println("\nsubstancias de FP nao usadas verificadas!\n" );

        }catch (Exception e){
            System.out.println("\nsubstancias de FP nao usadas não verificadas!\n" + e.getMessage());
        }
    }

}
