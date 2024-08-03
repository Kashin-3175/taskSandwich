package data;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SignInFrame extends JFrame {
    public SignInFrame() {
        setTitle("WEAVUS");
        setSize(300,200);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(5,2));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("ID");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("password");
        JPasswordField t2 = new JPasswordField(10);
        p2.add(l2);
        p2.add(t2);

        JPanel p3 = new JPanel();
        JLabel l3 = new JLabel("password2");
        JPasswordField t3 = new JPasswordField(10);
        p3.add(l3);
        p3.add(t3);

        JPanel p4 = new JPanel();
        JLabel l4 = new JLabel("name");
        JTextField t4 = new JTextField(10);
        p4.add(l4);
        p4.add(t4);


        JPanel p5 = new JPanel();
        JButton b1 = new JButton("戻り");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ログイン画面に戻ります");
                dispose();
                new LoginFrame();
            }
        });

        JButton b2 = new JButton("登録");
        p5.add(b1);
        p5.add(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isError = false;
                String id = t1.getText();
                String pw1 = new String(t2.getPassword());
                String pw2 = new String(t3.getPassword());
                String name1 = t4.getText();

                if(id.isEmpty() || pw1.isEmpty() || pw2.isEmpty() || name1.isEmpty()){
                    JOptionPane.showMessageDialog(null, "すべてのテキストを入力");
                    isError = true;
                }

                if(!pw1.equals(pw2)){
                    JOptionPane.showMessageDialog(null, "パスワードが一致しません");
                    isError = true;
                }

                if (!isError){
                    saveUserInfo(id, pw1, name1);
                }

            }
        });

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);
        mainP.add(p4);
        mainP.add(p5);

        add(mainP);

        setVisible(true);
    }

    private void saveUserInfo(String id, String pw1, String name1){
        String sql = "insert into userinfo (id, password, name) values (?, ?, ?)";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pw1);
            ps.setString(3, name1);


            int result = ps.executeUpdate();

            if(result == 1){
                JOptionPane.showMessageDialog(null, "登録完了");
            }else {
                JOptionPane.showMessageDialog(null, "登録失敗");
            }

        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "登録失敗");

        }catch (SQLException e2){
            e2.printStackTrace();
        }



    }

    public static void main(String[] args) {
        new SignInFrame();
    }
}
