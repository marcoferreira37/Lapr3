package LAPR.Controller;


import LAPR.dataAcess.DatabaseConnection;

import java.sql.SQLException;

public class ExitController {

    public ExitController(){
    }

    public void exit() throws SQLException {
        DatabaseConnection.getInstance().closeConnection();
    }
}
