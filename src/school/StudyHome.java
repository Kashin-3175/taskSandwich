package school;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import static school.StudyLogin.id3;

public class StudyHome extends JFrame {
    public StudyHome(){
        setTitle("WEB勉強システムホーム"+""+"ID：”"+id3+"”");
        setSize(700,500);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel(new GridLayout(1,2));
        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("");
        p1.add(l1);
        p1.add(l2);

        JPanel p2 = new JPanel();


        JPanel p3 = new JPanel();
        JButton b1 = new JButton("更新");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "src/school/students.csv";
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line;
                    line = reader.readLine();
                    String[] strArr = line.split(",");
                    String str1 = strArr[0];
                    String str2 = strArr[1];

                    l1.setText("更新しました");
                    l2.setText(str2);

                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        JButton b2 = new JButton("戻る");
        p3.add(b1);
        p3.add(b2);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);

        setVisible(true);
    }
}
