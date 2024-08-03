package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame3 extends JFrame {

    public static String logIn;
    public LoginFrame3(){
        DbConn.getConnection();
        setTitle("");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JTextField t1 = new JTextField(10);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JPasswordField ps1 = new JPasswordField(10);
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
        JButton b2 = new JButton("登録");

        p3.add(b1);
        p3.add(b2);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);


        setVisible(true);
    }
    private void loginMethod(String id1, String pass1){
        String sql ="select * from userinfo where id = ? and password = ?";


        try{
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id1);
            ps.setString(2, pass1);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "成功");
                logIn = id1;
                dispose();
                new MainFrame3();
            }else{
                JOptionPane.showMessageDialog(null, "失敗");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "失敗");
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new LoginFrame3();
    }
}
