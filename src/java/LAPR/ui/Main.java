package LAPR.ui;

import LAPR.dataAcess.DatabaseConnection;
import LAPR.ui.menu.MainMenuUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    static Scanner in  = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {

        try {
            loadProperties();

            String ipAddress = System.getProperty("database.inet");
            InetAddress inet = InetAddress.getByName(ipAddress);

            MainMenuUI menu = new MainMenuUI();
            menu.run();
            DatabaseConnection.getInstance().closeConnection();
        } catch (UnknownHostException e) {
            System.out.println("\nDatabase Server not reachable!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("App properties not loaded!");
        }
    }

    private static void loadProperties() throws IOException {
        Properties properties = new Properties(System.getProperties());

        InputStream inputStream = Main.class.getClassLoader().getResource("application.properties").openStream();
        properties.load(inputStream);
        inputStream.close();

        System.setProperties(properties);
    }
}