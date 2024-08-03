package practice0725;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracticeForget extends JFrame {
    public PracticeForget(){
        Practice.getConnection();
        setTitle("パスワードを検索する");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("戻る");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ログイン画面に戻ります");
                dispose();
                new PracticeLogin();
            }
        });

        JButton b2 = new JButton("検索");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                forgetMethod(id);
            }
        });

        p2.add(b1);
        p2.add(b2);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);




        setVisible(true);
    }
    private void forgetMethod(String id1){
        String sql ="select password from userinfo where id = ?";

        try {
            PreparedStatement ps = Practice.connnew.prepareStatement(sql);
            ps.setString(1, id1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String password = rs.getString("password");
                String masking = masking(password);
                JOptionPane.showMessageDialog(null, "あなたのパスワードは"+masking+"です");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String masking(String password){
        if (password.length() <= 2){
            return password;
        }
        StringBuilder mask = new StringBuilder();
        mask.append(password.charAt(0));
        for (int i = 1; i < password.length(); i++){
            if (i < password.length() -1){
                mask.append("★");
            }else{
                mask.append(password.charAt(i));
            }
        }
        return mask.toString();
    }
}
