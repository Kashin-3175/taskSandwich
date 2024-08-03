package practice0725;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Practice {
    public static Connection connnew;

    public static void getConnection(){

        String url = "jdbc:mysql://localhost:3306/mydb";
        String id = "root";
        String pw = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connnew = DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
