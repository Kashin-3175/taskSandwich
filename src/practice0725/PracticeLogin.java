package practice0725;

import practice0725.Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracticeLogin extends JFrame {
    public static String logIn;
    public  PracticeLogin(){
        Practice.getConnection();
        setTitle("ログイン");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("PWを入力");
        JPasswordField ps1 = new JPasswordField(10);
        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("ログイン");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String pass = new String(ps1.getPassword());
                loginMethod(id, pass);
            }
        });

        JButton b2 = new JButton("登録");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "登録画面に移動します");
                dispose();
                new PracticeRegister();
            }
        });

        JButton b3 = new JButton("忘れた");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "パスワードを忘れましたか？");
                dispose();
                new PracticeForget();
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
    private void loginMethod(String id1, String pass){
        String sql = "select * from userinfo where id = ? and password = ?";

        try {
            PreparedStatement ps = Practice.connnew.prepareStatement(sql);
            ps.setString(1, id1);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                JOptionPane.showMessageDialog(null, "ログイン成功");
                logIn = id1;
                dispose();
                new PracticeHome();
            }else{
                JOptionPane.showMessageDialog(null, "ログイン失敗");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ログイン失敗");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new PracticeLogin();
    }
}
