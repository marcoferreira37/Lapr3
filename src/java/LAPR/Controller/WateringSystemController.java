package LAPR.Controller;

import LAPR.US002.ExportFile;
import LAPR.US002.Parcel;
import LAPR.US002.WateringPlan;
import LAPR.USLP11.CampNotebookController;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class WateringSystemController {
    private String FILE = "src/resources/Lapr/exemplo.txt";
    private ArrayList<Parcel> parcels = new ArrayList<>();
    private ArrayList<WateringPlan> wateringPlans = new ArrayList<>();
    private LocalDateTime firstWatering;
    private LocalDateTime OgfirstWatering;
    private LocalDateTime lastWatering;
    private LocalDateTime OglastWatering;
    private LocalDateTime dateTime;

    public void execute() {
        try {
            dateTime = collectDate(false);
            readFileAndCollectInfo();
            createWateringPlan(dateTime);
            new ExportFile(wateringPlans).exportFile();
            isItWatering();
            CampNotebookController controller = new CampNotebookController();
            controller.importPlanoDeRega();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void readFileAndCollectInfo() throws FileNotFoundException {
        Scanner read = new Scanner(new File(FILE));

        String line = read.nextLine();
        line = line.replaceAll(",", ":");
        String[] times = line.split(":");
        String[] hours = new String[4];

        for (int i = 0; i < times.length; i++) {
            hours[i] = times[i];
            hours[i] = hours[i].trim();
        }
        boolean alsoGetHourandMinute = false;

        firstWatering = dateTime.withHour(Integer.parseInt(hours[0])).withMinute(Integer.parseInt(hours[1])).withSecond(0);
        OgfirstWatering = firstWatering;
        lastWatering = dateTime.withHour(Integer.parseInt(hours[2])).withMinute(Integer.parseInt(hours[3])).withSecond(0);
        OglastWatering = lastWatering;

        while (read.hasNextLine()) {

            line = read.nextLine();
            String[] info = line.split(",");
            String parcelID = info[0];
            String duration = info[1];
            String regularity = info[2];
            String mix = info.length > 3 ? info[3] : null;
            int recurrence = info.length > 4 ? Integer.parseInt(info[4]) : 0;


            Parcel parcel = new Parcel(parcelID, Integer.parseInt(duration), regularity, mix , recurrence);
            parcels.add(parcel);
        }
    }

    private LocalDateTime collectDate(boolean alsoGetHourandMinute) {

        Scanner scanner = new Scanner(System.in);

        String data = inputDay(scanner);



        String[] info = data.split("-");


        if (alsoGetHourandMinute) {

            int hora = -1;
            boolean flag = true;
            while (flag) {
                System.out.printf("Input your desired hour (0-23):");
                hora = scanner.nextInt();

                if (hora < 24 && hora >= 0) {
                    flag = false;
                } else {
                    System.out.println("Invalid hour!");
                }
            }

            flag = true;
            int minuto = -1;
            while (flag) {
                System.out.printf("Input your desired minute (0-59):");
                minuto = scanner.nextInt();

                if (minuto < 60 && minuto >= 0) {
                    flag = false;
                } else {
                    System.out.println("Invalid minute!");
                }
            }
            return LocalDateTime.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), hora, minuto);
        }
        return LocalDateTime.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), 0, 0);

    }

    private LocalDateTime collectDate2(boolean alsoGetHourandMinute) {

        Scanner scanner = new Scanner(System.in);


        String data2 = inputDay2(scanner);


        String[] info = data2.split("-");


        if (alsoGetHourandMinute) {

            int hora = -1;
            boolean flag = true;
            while (flag) {
                System.out.printf("Input your desired hour (0-23):");
                hora = scanner.nextInt();

                if (hora < 24 && hora >= 0) {
                    flag = false;
                } else {
                    System.out.println("Invalid hour!");
                }
            }

            flag = true;
            int minuto = -1;
            while (flag) {
                System.out.printf("Input your desired minute (0-59):");
                minuto = scanner.nextInt();

                if (minuto < 60 && minuto >= 0) {
                    flag = false;
                } else {
                    System.out.println("Invalid minute!");
                }
            }
            return LocalDateTime.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), hora, minuto);
        }
        return LocalDateTime.of(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), 0, 0);

    }

    public void createWateringPlan(LocalDateTime dateTime) {

        for (int i = 0; i < 30; i++) {
            int wateringTotalDuration = 0;
            int wateringLastDuration = 0;

            if ( i != 0) dateTime = dateTime.plusDays(1);

            for (Parcel parcel : parcels) {

                if (checkIfRegularityValid(i, parcel.getRegularity())) {
                    WateringPlan wateringPlan = new WateringPlan(dateTime,parcel,parcel.getDuration() ,firstWatering.plusMinutes(wateringTotalDuration), firstWatering.plusMinutes(wateringTotalDuration + parcel.getDuration()));
                    wateringPlans.add(wateringPlan);
                    wateringTotalDuration += parcel.getDuration();
                }
            }

            for (Parcel parcel : parcels) {

                if (checkIfRegularityValid(i, parcel.getRegularity())) {
                    WateringPlan wateringPlan = new WateringPlan(dateTime ,parcel,parcel.getDuration() ,lastWatering.plusMinutes(wateringLastDuration), lastWatering.plusMinutes(wateringLastDuration + parcel.getDuration()));
                    wateringPlans.add(wateringPlan);
                    wateringLastDuration += parcel.getDuration();
                }
            }


        }
    }

    private boolean checkIfRegularityValid(int i, String regularity) {
        switch (regularity) {
            case "T":
                return true;
            case "P":
                if (LocalDate.now().plusDays(i).getDayOfMonth() % 2 == 0) {
                    return true;
                }
            case "I":
                if (LocalDate.now().plusDays(i).getDayOfMonth() % 2 != 0) {
                    return true;
                }
            case "3":
                if (i % 3 == 0) {
                    return true;
                }
            default:
                return false;
        }
    }

    private void isItWatering() {
        boolean validDate = true;
        LocalDateTime time = null;

        while (validDate) {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("----Date you want to verify if it is watering (like this example -> (yyyy-mm-dd))----");

            boolean alsoGetHourandMinute = true;
            time = collectDate2(alsoGetHourandMinute);

            if (time.isBefore(OgfirstWatering) || time.isAfter(OglastWatering.plusDays(30))) {
                System.out.println("Invalid date!");
            } else {
                validDate = false;
            }
        }

        boolean isItWatering = false;
        for (int i = 0; i < 30; i++) {

            for (WateringPlan wateringPlan : wateringPlans) {
                LocalDateTime firstCycle = wateringPlan.getFirstCycle().plusDays(i);
                LocalDateTime lastCycle = wateringPlan.getLastCycle().plusDays(i);

                if ((time.isEqual(firstCycle) || time.isEqual(lastCycle))
                        || (time.isBefore(lastCycle) && time.isAfter(firstCycle))) {

                    long minutesLeft = lastCycle.getMinute() - time.getMinute();

                    if (minutesLeft == 0) {
                        System.out.println("It's stopping at this moment now");
                    } else {
                        System.out.println("It is watering and will stop in " + minutesLeft + " minutes");
                    }

                    isItWatering = true;
                    break;
                }
            }

        }
        if (!isItWatering) {
            System.out.println("It is not watering");
        }
    }

    public String inputDay(Scanner scanner){
        System.out.println("-----------------------------------------------------------------------------------------");
         System.out.println("Input the date you want to start the watering plan ( like this example -> (yyyy-mm-dd)):");
        String data = scanner.nextLine();
        return data;
    }

    public String inputDay2(Scanner scanner){
        String data = scanner.nextLine();
        return data;
    }

    public static void main(String[] args) {
        WateringSystemController controller = new WateringSystemController();
        controller.execute();
    }
}


