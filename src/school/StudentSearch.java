package school;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class StudentSearch extends JFrame {
    public StudentSearch() {
        setTitle("パスワード検索");
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3, 1));

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

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

        JButton b2 = new JButton("検索");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                searchMethod(id1);
            }
        });

        p3.add(b1);
        p3.add(b2);

        mainP.add(p1);
        mainP.add(p3);

        add(mainP);

        setVisible(true);
    }

    private void searchMethod(String id1) {
        String fileName = "src/school/students.csv";
        boolean isSwitch = false;

        try {
            if (id1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "入力してください");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] strArr = line.split(",");
                    String id2 = strArr[0];
                    String pass2 = strArr[1];

                    if (id1.equals(id2)) {
                        String masking = maskingMethod(pass2);
                        JOptionPane.showMessageDialog(null, "あなたのパスワードは"+masking+"です");
                        isSwitch = true;
                        break;
                    }

                }
                if (!isSwitch) {
                    JOptionPane.showMessageDialog(null, "入力されたIDは存在しません");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String maskingMethod(String pass2) {
        if (pass2.length() <= 2) {
            return pass2;
        }
        StringBuffer mask = new StringBuffer();
        mask.append(pass2.charAt(0));
        for (int i = 1; i < pass2.length(); i++) {
            if (i < pass2.length() - 1) {
                mask.append("★");
            } else {

                mask.append(pass2.charAt(i));
            }
        }
        return mask.toString();
    }
}
