package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


<<<<<<< HEAD
    private static final String URL = "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD"; // Adjust if using a different service name or port
=======
    private static final String URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD"; // Adjust if using a different service name or port
>>>>>>> 80ac051d22e45e3ed97cbde9026d5a73fdaf6d0a
    private static final String USER = "COMP228_W25_sy_12"; // Replace with your Oracle DB username
    private static final String PASS= "password"; // Replace with your Oracle DB password

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}