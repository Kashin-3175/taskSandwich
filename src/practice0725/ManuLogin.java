package practice0725;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManuLogin extends JFrame {
    public static String logIn;
    public  ManuLogin(){
        DbConn.getConnection();
        setTitle("login");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("ID");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("PW");
        JPasswordField ps1 = new JPasswordField(10);
        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("login");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                loginMethod(id1, pass1);
            }
        });
        JButton b2 = new JButton("resister");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "resister");
                dispose();
                new MainResister();
            }
        });
        JButton b3 = new JButton("forget");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "forget");
                dispose();
                new MainForget();
            }
        });
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);



        setVisible(true);


    }
    private void loginMethod(String id1, String pass1){
        String sql = "select * from userinfo where id = ? and password = ?";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id1);
            ps.setString(2, pass1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                JOptionPane.showMessageDialog(null, "sucsses");
                logIn = id1;
                dispose();
                new MainHome();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ManuLogin();
    }

}
