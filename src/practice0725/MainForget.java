package practice0725;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainForget extends JFrame {
    public MainForget(){
        setTitle("forget");
        setSize(500,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("ID");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);
        
        JPanel p2 = new JPanel();
        JButton b1 = new JButton("search");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                forgetMethod(id1);
            }
        });

        p2.add(b1);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);

        setVisible(true);

    }
    private void forgetMethod(String id1){
        String sql = "select password from userinfo where id = ?";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, id1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String password = rs.getString("password");
                String masking = masking(password);
                JOptionPane.showMessageDialog(null, "あなたのPWは"+masking+"です");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private String masking(String password){
        if (password.length() >= 2){
            return password;
        }
        StringBuilder mask = new StringBuilder();
        mask.append(password.charAt(0));
        return mask.toString();


    }
}
