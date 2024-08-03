package practice0725;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainHome extends JFrame {
    public  MainHome(){
        setTitle("delete");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("PWを入力");
        JPasswordField ps1 = new JPasswordField(10);
        p1.add(l1);
        p1.add(ps1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("back");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "back");
                dispose();
                new ManuLogin();
            }
        });

        JButton b2 = new JButton("delete");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass1 = new String(ps1.getPassword());
                deleteMethod(pass1);
            }
        });
        p2.add(b1);
        p2.add(b2);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);




        setVisible(true);
    }
    private void deleteMethod(String pass1){
        String sql ="delete from userinfo where id = ? and password = ?";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, ManuLogin.logIn);
            ps.setString(2, pass1);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "削除成功");
            }else {
                JOptionPane.showMessageDialog(null, "削除失敗");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "削除失敗");
            e.printStackTrace();
        }



    }
}
