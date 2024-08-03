package test;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainTest extends JFrame {
    public MainTest(){
        setTitle("メイン画面");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        JButton b1 = new JButton("変更");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "変更画面に移動します");
                dispose();
                new ChangeTest();
            }
        });
        p1.add(b1);

        JPanel p2 = new JPanel();
        JButton b2 = new JButton("ログアウト");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ログアウトします");
                LoginTest.login = "";
                try {
                    DbConn.conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
                new LoginTest();
            }
        });
        p2.add(b2);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);

        setVisible(true);
    }
}
