package data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Curry1 extends JFrame {

    public Curry1(){
        setTitle("カレーを作ろう");
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
        JPasswordField pw1 = new JPasswordField(10);

        p2.add(l2);
        p2.add(pw1);

        JPanel p3 = new JPanel();

        JButton b1 = new JButton("認証");

        String fileName = "src/data/test.csv";


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = t1.getText();
                String pass = new String(pw1.getPassword());

                if(str1.equals("MAKECURRY") && pass.equals("1234")){

                    String[] data = {str1, pass};

                    try{
                        FileWriter writer = new FileWriter(fileName);

                        for(String line : data){
                            writer.write(line + "\n");
                        }

                        writer.close();
                        System.out.println("CSVファイルにデータを書き込みました" + fileName);
                    }catch (Exception e2){
                        e2.printStackTrace();
                        System.out.println("CSVファイルの作成が失敗しました");
                    }


                    JOptionPane.showMessageDialog(null, "ログイン成功");
                    dispose();
                    new Curry();
                }else {
                    JOptionPane.showMessageDialog(null, "ログイン失敗");
                }
            }
        });

        p3.add(b1);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Curry1();
    }

}

