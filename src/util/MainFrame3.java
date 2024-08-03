package util;

import data.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainFrame3 extends JFrame {
    public MainFrame3(){
        setTitle("");
        setSize(500,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));
        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("pass");
        JPasswordField ps1 = new JPasswordField(10);
        p1.add(l1);
        p1.add(ps1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("削除");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass1 = new String(ps1.getPassword());
                deleteMethod(pass1);
            }
        });

        p2.add(b1);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);

        setVisible(true);
    }

    private void deleteMethod(String pass1){
        String sql ="delete from userinfo where id = ? and password = ?";

        try{
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, LoginFrame.logIn);
            ps.setString(2, pass1);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "削除");
            }else{
                JOptionPane.showMessageDialog(null, "失敗");
                dispose();
                new LoginFrame();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "失敗");
            e.printStackTrace();
        }
    }
}
