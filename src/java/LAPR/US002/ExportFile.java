package LAPR.US002;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExportFile {
    private ArrayList<WateringPlan> wateringPlans = new ArrayList<>();

    public ExportFile(ArrayList<WateringPlan> wateringPlans) {
        this.wateringPlans = wateringPlans;
    }

    public void exportFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/resources/Lapr/Plano_de_rega.csv");
        pw.println("Dia,Setor,Duração,Início,Final,Regularity,Mix,Recurrence");

        for (WateringPlan wateringPlan : wateringPlans) {
            String mixInfo = (wateringPlan.getParcel().getMix() != null) ? wateringPlan.getParcel().getMix() : "";
            int recurrenceInfo = (wateringPlan.getParcel().getRecurrence() != -1) ? wateringPlan.getParcel().getRecurrence() : 0;

            pw.println(
                    wateringPlan.getDate().toString().substring(0, 10) + "," +
                            wateringPlan.getParcel().getParcelId() + "," +
                            wateringPlan.getParcel().getDuration() + "," +
                            wateringPlan.getFirstCycle().getHour() + ":" + wateringPlan.getFirstCycle().getMinute() + "," +
                            wateringPlan.getLastCycle().getHour() + ":" + wateringPlan.getLastCycle().getMinute() + "," +
                            wateringPlan.getParcel().getRegularity() + "," +
                            mixInfo + "," +
                            recurrenceInfo
            );
        }
        pw.close();
    }
}