package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection(){
        final String url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ersproject";
        final String username = System.getenv("RDS_USERNAME");
        final String password = System.getenv("RDS_PASSWORD");

        Connection conn = null;

        try{
           conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return conn;
    }
}
