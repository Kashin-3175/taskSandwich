package test29;

import util.DbConn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeFrame29 extends JFrame {
    public final static String MALE = "0";
    public final static String FEMALE = "1";

    JTextField t1 = new JTextField(10);
    JPasswordField ps1 = new JPasswordField(10);
    JPasswordField ps2 = new JPasswordField(10);
    JTextField t4 = new JTextField(10);
    JRadioButton rb1 = new JRadioButton("male");
    JRadioButton rb2 = new JRadioButton("female");
    JTextField t6 = new JTextField(10);
    JTextField t7 = new JTextField(10);

    public ChangeFrame29(){
        DbConn.getConnection();
        setTitle("変更画面");
        setSize(600, 400);
        setLocationRelativeTo(null);

        UserInfoVo29 userInfoVo29 = findUserinfo();

        JPanel mainP = new JPanel(new GridLayout(8,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");

        t1.setText(userInfoVo29.getId());
        p1.add(l1);
        p1.add(t1);

        t1.setEditable(false);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("passwordを入力");
        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JLabel l3 = new JLabel("password(確認)を入力");
        p3.add(l3);
        p3.add(ps2);

        JPanel p4 = new JPanel();
        JLabel l4 = new JLabel("名前を入力");
        t4.setText(userInfoVo29.getName());
        p4.add(l4);
        p4.add(t4);

        JPanel p5 = new JPanel();
        JLabel l5 = new JLabel("性別を入力");

        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        p5.add(l5);
        p5.add(rb1);
        p5.add(rb2);

        if (userInfoVo29.getGender().equals(MALE)){
            rb1.setSelected(true);
        }else{
            rb2.setSelected(true);
        }

        JPanel p6 = new JPanel();
        JLabel l6 = new JLabel("年齢を入力");
        String changeAge = Integer.toString(userInfoVo29.getAge());
        t6.setText(changeAge);
        p6.add(l6);
        p6.add(t6);

        JPanel p7 = new JPanel();
        JLabel l7 = new JLabel("生年月日を入力");
        t7.setText(userInfoVo29.getBirthday());
        p7.add(l7);
        p7.add(t7);

        JPanel p8 = new JPanel();
        JButton b1 = new JButton("リセット");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                ps1.setText("");
                ps2.setText("");
                t4.setText("");
                bg.clearSelection();
                t6.setText("");
                t7.setText("");
            }
        });

        JButton b2 = new JButton("更新");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fieldCheack()){
                    updateUserinfo();
                }
            }
        });
        JButton b3 = new JButton("戻る");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "メイン画面に戻ります");
                dispose();
                new MainFrame29();
            }
        });
        p8.add(b1);
        p8.add(b2);
        p8.add(b3);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);
        mainP.add(p4);
        mainP.add(p5);
        mainP.add(p6);
        mainP.add(p7);
        mainP.add(p8);

        add(mainP);

        setVisible(true);
    }
    private UserInfoVo29 findUserinfo(){
        String sql = "select * from userinfo where id = ?";
        UserInfoVo29 userInfoVo29 = new UserInfoVo29();

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, LoginFrame29.login);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                userInfoVo29.setId(rs.getString("id"));
                userInfoVo29.setPassword(rs.getString("password"));
                userInfoVo29.setName(rs.getString("name"));
                userInfoVo29.setGender(rs.getString("gender"));
                userInfoVo29.setAge(rs.getInt("age"));
                userInfoVo29.setBirthday(rs.getString("birthday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userInfoVo29;

    }
    private boolean fieldCheack(){
        boolean isError = false;

        if (t1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "IDを入力してください");
            isError = true;
        }else if (new String(ps1.getPassword()).isEmpty()){
            JOptionPane.showMessageDialog(null, "passwordを入力してください");

            isError = true;
        }else if (new String(ps2.getPassword()).isEmpty()){
            JOptionPane.showMessageDialog(null, "password(確認)を入力してください");
            isError = true;
        }else if (!new String(ps1.getPassword()).equals(new String(ps2.getPassword()))){
            JOptionPane.showMessageDialog(null, "一致しません");
            isError = true;
        }else if (t4.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "名前を入力してください");
            isError = true;
        }else if (!rb1.isSelected() && !rb2.isSelected()){
            JOptionPane.showMessageDialog(null, "性別を選択してください");
            isError = true;
        } else if (t6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "年齢を入力してください");
            isError = true;
        } else if (t7.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "生年月日を入力してください");
            isError = true;
        }
        return isError;


    }
    private void updateUserinfo(){
        String sql = "update userinfo set password = ?, name = ?, gender = ?, age = ?, birthday = ? where id = ?";

        try {
            PreparedStatement ps = DbConn.conn.prepareStatement(sql);
            ps.setString(1, new String(ps1.getPassword()));
            ps.setString(2, t4.getText());
            ps.setString(3, rb1.isSelected() ? MALE : FEMALE);
            ps.setInt(4, Integer.parseInt(t6.getText()));
            ps.setString(5, t7.getText());
            ps.setString(6, LoginFrame29.login);

            int result = ps.executeUpdate();

            if (result == 1){
                JOptionPane.showMessageDialog(null, "更新成功");
            }else {
                JOptionPane.showMessageDialog(null, "更新失敗");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ChangeFrame29();
    }
}
