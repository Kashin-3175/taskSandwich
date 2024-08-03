package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn2 {
    public static Connection conn2;

    public static void getConnection(){
        String url ="jdbc:mysql://localhost:3306/mydb";
        String id ="root";
        String pw = "";


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn2 = DriverManager.getConnection(url, id, pw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
