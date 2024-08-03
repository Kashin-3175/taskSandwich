package test29;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame29 extends JFrame {
    public  static String login;
    public LoginFrame29(){
        DbConn.getConnection();
        setTitle("ログイン");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("passwordを入力");
        JPasswordField ps1 = new JPasswordField(10);
        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("ログイン");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                loginMethod(id1, pass1);
            }
        });
        p3.add(b1);

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
            ps.setString(1, id1);;
            ps.setString(2, pass1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                JOptionPane.showMessageDialog(null, "ログイン成功");
                login = id1;
                dispose();
                new MainFrame29();
            }else {
                JOptionPane.showMessageDialog(null, "ログイン失敗");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ログイン失敗");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LoginFrame29();
    }
}
