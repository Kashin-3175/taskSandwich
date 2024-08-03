package data;


import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public MainFrame(){

        setTitle("メインフレーム");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(4,1));

        JPanel p1 = new JPanel();
        JButton b1 = new JButton("変更");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChangeFrame();
            }
        });


        p1.add(b1);

        JPanel p2 = new JPanel();
        JButton b2 = new JButton("一覧");
        p2.add(b2);

        JPanel p3 = new JPanel();
        JButton b3 = new JButton("ログアウト");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.logIn = "";
                try {
                    DbConn.conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        p3.add(b3);

        JPanel p4 = new JPanel();
        JLabel l4 = new JLabel(LoginFrame.logIn + "さん、こんにちは");
        p4.add(l4);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);
        mainP.add(p4);

        add(mainP);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}