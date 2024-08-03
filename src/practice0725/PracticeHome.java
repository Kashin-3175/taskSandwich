package practice0725;

import practice0725.Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PracticeHome extends JFrame {
    public PracticeHome(){
        setTitle("ホーム画面");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("パスワードを入力");
        JPasswordField ps1 = new JPasswordField(10);
        p1.add(l1);
        p1.add(ps1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("削除");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(ps1.getPassword());
                deleteMethod(pass);
            }
        });
        p2.add(b1);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);


        setVisible(true);
    }
    private void deleteMethod(String pass){
        String sql = "delete from userinfo where id = ? and password = ?";

        try {
            PreparedStatement ps = Practice.connnew.prepareStatement(sql);

            ps.setString(1, PracticeLogin.logIn);
            ps.setString(2, pass);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "削除に成功しました");
                dispose();
                new PracticeLogin();
            }else{
                JOptionPane.showMessageDialog(null, "削除に失敗しました");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
