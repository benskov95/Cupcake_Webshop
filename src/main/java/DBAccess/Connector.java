package DBAccess;

import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 The purpose of Connector is to...

 @author kasper
 */
public class Connector {

    private static final String URL = "jdbc:mysql://64.225.101.254/cupcakeshop?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() throws LoginSampleException {
        if (singleton == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
                throw new LoginSampleException("Problem med at skabe forbindelse til databasen");
            }
        }
        return singleton;
    }

}
