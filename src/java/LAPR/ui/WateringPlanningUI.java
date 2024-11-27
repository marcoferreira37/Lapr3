package LAPR.ui;

import LAPR.Controller.WateringSystemController;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WateringPlanningUI implements Runnable {
    private WateringSystemController controller;

    public WateringPlanningUI() {
        controller = new WateringSystemController();
    }

    public void run() {
        controller.execute();
    }
}