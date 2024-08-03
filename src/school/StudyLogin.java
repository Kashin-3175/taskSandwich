package school;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class StudyLogin extends JFrame {
    public static String id3 ;

    public StudyLogin(){
        setTitle("WEB勉強システムログイン");
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
        JButton b1 = new JButton("ログイン");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                loginMethod(id1, pass1);

            }
        });

        JButton b2 = new JButton("登録");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "登録画面に移動します");
                dispose();
                new StudyRegistration();

            }
        });

        JButton b3 = new JButton("パスワードをお忘れですか？");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "パスワードを検索画面に移動します");
                dispose();
                new StudentSearch();
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
    private void loginMethod(String id1, String pass1){
        String fileName = "src/school/students.csv";
        boolean isSwitch = false;

        try{
            if(id1.isEmpty() || pass1.isEmpty() ){
                JOptionPane.showMessageDialog(null, "入力してください");

            }else {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while((line = reader.readLine()) != null){
                    String[] strArr = line.split(",");
                    String id2 = strArr[0];
                    String pass2 = strArr[1];

                    if (id1.equals(id2) && pass1.equals(pass2)){
                        isSwitch = true;
                        break;
                    }

                }
                if (isSwitch){
                    id3 = id1;
                    JOptionPane.showMessageDialog(null, "ログイン成功");
                    dispose();
                    new StudyHome();
                }else {
                    JOptionPane.showMessageDialog(null, "IDかパスワードが間違っています");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        new StudyLogin();
    }
}
