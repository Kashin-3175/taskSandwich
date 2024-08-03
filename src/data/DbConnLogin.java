package data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DbConnLogin extends JFrame {
    public DbConnLogin() {
        setTitle("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField id = new JTextField(10);
        p1.add(l1);
        p1.add(id);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("Passwordを入力");
        JPasswordField pass = new JPasswordField(10);
        p2.add(l2);
        p2.add(pass);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("Login");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = id.getText();
                String pass1 = new String(pass.getPassword());
                boolean isSuccess = false;

                String url = "jdbc:mysql://localhost:3306/test";
                String id = "root";
                String pw = "";

                Connection conn = null;
                Statement st = null;
                ResultSet rs = null;

                try {
                    if(id1.isEmpty() || pass1.isEmpty()){
                        JOptionPane.showMessageDialog(null, "入力してください");
                    }else {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(url, id, pw);

                        String sql = "select * from dbconnlogin";

                        st = conn.createStatement();
                        rs = st.executeQuery(sql);

                        while (rs.next()) {
                            String id2 = rs.getString("LoginId");
                            String pw2 = rs.getString("LoginPassword");
                            if (id2.equals(id1) && pw2.equals(pass1)) {
                                isSuccess = true;
                                break;

                            }
                        }
                        if(isSuccess){
                            JOptionPane.showMessageDialog(null, "ログイン成功");
                            dispose();
                            new DbConnHome();
                        }else {
                            JOptionPane.showMessageDialog(null, "ログイン失敗");
                        }
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });

        p3.add(b1);

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DbConnLogin();
    }
}