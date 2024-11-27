package LAPR.USLP11;

import LAPR.US002.Parcel;
import LAPR.US002.WateringPlan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CampNotebookController {
    public void execute() throws FileNotFoundException {
      importPlanoDeRega();
    }
    public boolean checkIfItIrregated(WateringPlan entrie){
        LocalDateTime now = LocalDateTime.now();
        if (entrie.getLastCycle().isBefore(now)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CampNotebookController controller = new CampNotebookController();
        try {
            controller.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void importPlanoDeRega() throws FileNotFoundException {
        String file = "src/resources/Lapr/Plano_de_rega.csv";
        Scanner read = new Scanner(new File(file));
        String filepw = "src/resources/Lapr/CadernoDeCampo.txt";
        PrintWriter pw = new PrintWriter(filepw);
        String info = read.nextLine();
        while (read.hasNextLine()){
            String[] line = read.nextLine().split(",");
            String[] date = line[0].split("-");
            String setor = line[1];
            String duracao = line[2];
            String[] timeInicio = line[3].split(":");
            String[] timeFim = line[4].split(":");
            String regularity = line[5];
            String mix = line[6];
            String recurence = line[7];
            LocalDateTime data = LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(timeFim[0]),Integer.parseInt(timeFim[1]));
            Parcel parcel = new Parcel(setor,Integer.parseInt(duracao),regularity,mix,Integer.parseInt(recurence));
            int activationTime = Integer.parseInt(duracao);
            LocalDateTime firstCycle = LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(timeInicio[0]),Integer.parseInt(timeInicio[1]));
            LocalDateTime lastCycle = LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(timeFim[0]),Integer.parseInt(timeFim[1]));
            WateringPlan plan = new WateringPlan(data,parcel,activationTime,firstCycle,lastCycle);
            if (checkIfItIrregated(plan)) {
                exportCadernoDeCampo(date, setor, duracao, timeInicio, timeFim, regularity, mix, recurence, filepw, pw);
            }
        }
        read.close();
        pw.close();
    }
    public void exportCadernoDeCampo(String[] date,String setor,String duracao,String[] timeInicio,String[] timeFim,String regularity,String mix,String recurence, String file, PrintWriter pw) throws FileNotFoundException {
        pw.println("Rega Efetuada no dia "+date[2]+"/"+date[1]+"/"+date[0]+" no setor "+setor+" com duração de "+duracao+" minutos das "+timeInicio[0]+":"+timeInicio[1]+" até às "+timeFim[0]+":"+timeFim[1]);
    }
}
