package school;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class StudyRegistration extends JFrame {
    public StudyRegistration(){
        setTitle("生徒情報登録");
        setSize(700,500);
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
        JButton b1 = new JButton("戻る");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "ログイン画面に戻ります");
                dispose();
                new StudyLogin();
            }
        });

        JButton b2 = new JButton("登録");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                registrationMethod(id1, pass1);

            }
        });

        p3.add(b1);
        p3.add(b2);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);

        setVisible(true);
    }
    private void registrationMethod(String id1, String pass1){
        String fileName = "src/school/students.csv";
        boolean isSwitch = false;

        try{
            if (id1.isEmpty() || pass1.isEmpty()){
                JOptionPane.showMessageDialog(null, "入力してください");
            }else{
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(id1 + "," + pass1 + "\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "入力に成功しました\nログイン画面に戻ります");
                dispose();
                new StudyLogin();
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "入力に失敗しました");
            e.printStackTrace();
        }
    }
}
