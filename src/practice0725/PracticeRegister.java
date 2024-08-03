package practice0725;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PracticeRegister extends JFrame {
    public PracticeRegister(){
        Practice.getConnection();
        setTitle("登録画面");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(5,1));

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
        JLabel l3 = new JLabel("PWを確認");
        JPasswordField ps2 = new JPasswordField(10);
        p3.add(l3);
        p3.add(ps2);

        JPanel p4 = new JPanel();
        JLabel l4 = new JLabel("名前を入力");
        JTextField t2 = new JTextField(10);
        p4.add(l4);
        p4.add(t2);

        JPanel p5 = new JPanel();
        JButton b1 = new JButton("戻る");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ログイン画面に戻ります");
                dispose();
                new PracticeLogin();

            }
        });

        JButton b2 = new JButton("登録");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String pass1 = new String(ps1.getPassword());
                String pass2 = new String(ps2.getPassword());
                String name = t2.getText();
                boolean isError = false;

                if (id.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || name.isEmpty()){
                    JOptionPane.showMessageDialog(null, "すべてのテキストを入力");
                    isError = true;
                }
                if (!pass1.equals(pass2)){
                    JOptionPane.showMessageDialog(null, "パスワードが一致しません");
                    isError = true;
                }
                if (!isError){
                    registerMethod(id, pass1, name);
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
    private void registerMethod(String id, String pass1, String name){
        String sql = "insert into userinfo (id, password, name) values (?, ?, ?)";

        try {
            PreparedStatement ps = Practice.connnew.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pass1);
            ps.setString(3, name);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "登録が完了しました");
            }else{
                JOptionPane.showMessageDialog(null, "登録に失敗しました");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new PracticeRegister();
    }
}
