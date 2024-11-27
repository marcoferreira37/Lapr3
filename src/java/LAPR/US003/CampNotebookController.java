package LAPR.US003;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class CampNotebookController {
    public void execute() throws FileNotFoundException {
        WriteNotebookPlan export = new WriteNotebookPlan();
        export.ImportNotebookPlan();
        export.ExportNotebookPlan();
    }
    public boolean checkIfItIrregated(NotebookEntrie entrie){
        LocalDateTime now = LocalDateTime.now();
        String lastCycle = entrie.getLastCycle();
        String[] lastCycleArray = lastCycle.split(":");
        String date = entrie.getDate();
        String[] dateArray = date.split("-");


        LocalDateTime entrieTime = LocalDateTime.of(Integer.parseInt( dateArray[0]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[2]),Integer.parseInt(lastCycleArray[0]),Integer.parseInt(lastCycleArray[1]));
        if (entrieTime.isBefore(now)){
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
}
