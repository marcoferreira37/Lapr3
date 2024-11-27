package LAPR.US003;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteNotebookPlan {

    List<NotebookEntrie> entries = new ArrayList<>();
    CampNotebookController controller = new CampNotebookController();
    public void ImportNotebookPlan() throws FileNotFoundException {
        File file = new File("src/resources/Lapr/Plano_de_rega.csv");
        Scanner read = new Scanner(file);
        String header = read.nextLine();
        while (read.hasNextLine()){
            String line = read.nextLine();
            String[] info = line.split(",");

            String date = info[0];
            String parcelID = info[1];
            String duration = info[2];
            String firstCycle = info[3];
            String lastCycle = info[4];

            if(controller.checkIfItIrregated(new NotebookEntrie(date, parcelID, duration, firstCycle, lastCycle))){
                NotebookEntrie entrie = new NotebookEntrie(date, parcelID, duration, firstCycle, lastCycle);
                entries.add(entrie);
            }
        }

    }
    public void ExportNotebookPlan() throws FileNotFoundException {
        PrintWriter write = new PrintWriter("src/resources/Lapr/Caderno_de_campo.txt");
        for (NotebookEntrie entrie : entries) {
                write.println("Rega - {" + "Data: " +entrie.getDate() + ", Parcela: " + entrie.getParcelID() + ", Rega efetuada das " + entrie.getFirstCycle()+ " às " + entrie.getLastCycle() + "}\n");
        }
        System.out.println("Exported to Caderno_de_campo.txt");
        write.close();
    }
}
