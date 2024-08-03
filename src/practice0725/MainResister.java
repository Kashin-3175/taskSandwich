package practice0725;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainResister extends JFrame {
    public MainResister(){
        setTitle("resister");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(5,1));

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
        JLabel l3 = new JLabel("PW2");
        JPasswordField ps2 = new JPasswordField(10);
        p2.add(l3);
        p2.add(ps2);

        JPanel p4 = new JPanel();
        JLabel name1 = new JLabel("name");
        JTextField t2 = new JTextField(10);
        p4.add(name1);
        p4.add(t2);

        JPanel p5 = new JPanel();
        JButton b1 = new JButton("back");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "back");
                dispose();
                new ManuLogin();
            }
        });

        JButton b2 = new JButton("resister");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                String pass2 = new String(ps2.getPassword());
                String name2 = name1.getText();
                boolean isError = false;

                if (id1.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || name2.isEmpty()){
                    JOptionPane.showMessageDialog(null, "empty");
                    isError = true;
                }
                if (!pass1.equals(pass2)){
                    JOptionPane.showMessageDialog(null, "一致しません");
                    isError = true;
                }
                if (!isError){
                    resisterMethod(id1, pass1, name2);
                }
            }
        });
        p5.add(b1);
        p5.add(b2);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);
        mainP.add(p4);
        mainP.add(p5);

        add(mainP);



        setVisible(true);
    }
    private void resisterMethod(String id1, String pass1, String name2){
        String sql = "insert into userinfo (id, password, name) values (?, ?, ?)";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id1);
            ps.setString(2, pass1);
            ps.setString(3, name2);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "登録成功");
            }else {
                JOptionPane.showMessageDialog(null, "登録失敗");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "登録失敗");
            e.printStackTrace();
        }
    }
}
