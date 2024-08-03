package curry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class CurryLogin extends JFrame {
    public  CurryLogin(){
        setTitle("カレーショッピングログイン");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("ID");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("PW");
        JPasswordField ps1 = new JPasswordField(10);
        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("認証");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                String fileName = "src/curry/curry.csv";
                boolean isSwitch = false;

                try{
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
                        JOptionPane.showMessageDialog(null, "認証成功");
                        dispose();
                        new CurryHome();
                    }else {
                        JOptionPane.showMessageDialog(null, "認証失敗");

                    }


                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        });

        JButton b2 = new JButton("登録");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "登録画面へ移動します");
                dispose();
                new CurryEntry();
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

    public static void main(String[] args) {
        new CurryLogin();
    }

}
