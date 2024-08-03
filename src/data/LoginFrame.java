package data;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    public static String logIn;


    public LoginFrame() {
        DbConn.getConnection();
        // タイトル、サイズ、閉じるボタンの設定
        setTitle("WEAVUS社員管理システム");

        // 大きさを指定
        setSize(500, 300);

        //GridLayoutを設定


        // FRAMEを画面中央に表示
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p = new JPanel();
        JLabel l1 = new JLabel("ID");
        p.add(l1);

        JTextField t1 = new JTextField(10);
        p.add(t1);

        mainP.add(p);

        JPanel p1 = new JPanel();
        JLabel l2 = new JLabel("Password");
        JPasswordField t2 = new JPasswordField(10);
        p1.add(l2);
        p1.add(t2);

        mainP.add(p1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("Login");
        JButton b2 = new JButton("ShginUp");
        JButton b3 = new JButton("forgot password");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String password = new String(t2.getPassword());

                //ログイン成功時、新しい画面の表示
                cheackLogin(id, password);

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignInFrame();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "パスワードをお忘れですか？");
                dispose();
                new ForgotPassword();
            }
        });
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);



        mainP.add(p2);

        add(mainP);

        // 画面を表示
        setVisible(true);
    }

    private void cheackLogin(String id, String password){
      String sql = "select * from userinfo where id = ? and password = ?";


        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();



            if (rs.next()){
                JOptionPane.showMessageDialog(null, "ログイン成功");
                logIn = id;
                dispose();
                new MainFrame();
            }else {
                JOptionPane.showMessageDialog(null, "ログイン失敗");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new LoginFrame();
    }
}