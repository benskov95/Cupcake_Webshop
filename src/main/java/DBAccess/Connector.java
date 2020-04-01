package DBAccess;

import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The purpose of the Connector class is to
 * establish a connection to the SQL database
 * so that the information within it can be
 * accessed and modified.

 @author benjamin
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() throws SQLException, ClassNotFoundException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");

        if (deployed != null) {
            // Prod: hent variabler fra setenv.sh
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");

        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/cupcakeshop?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
            USERNAME = "root";
            PASSWORD = "root";
        }
    }


}
