package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {

    public static Connection conn;

    public static void getConnection(){


        //String url ="jdbc:mysql://localhost:3306/mydb";
        String url ="jdbc:h2:~/test";
        String id = "root";
        String pw = "";

        try {
            //Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, id, pw);


            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(255))";


        //} catch (ClassNotFoundException e) {
         //   e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
   

}
