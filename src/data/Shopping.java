package data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Shopping extends JFrame {
    public  Shopping(){
        setTitle("ショッピング");
        setSize(700,500);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(2,1));

        JPanel p1 = new JPanel();

        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JButton b1 = new JButton("検索");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                searchMethod(id1);
            }
        });
        p2.add(b1);

        mainP.add(p1);
        mainP.add(p2);

        add(mainP);

        setVisible(true);
    }
    private void searchMethod(String id1){
        String fileName = "src/data/shopping.csv";
        boolean isSwitch = false;

        try{
            if (id1.isEmpty()){
                JOptionPane.showMessageDialog(null, "入力してください");
            }else{
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while((line = reader.readLine()) != null){
                    String[] strArr = line.split(",");
                    String id2 = strArr[0];
                    String pass2 = strArr[1];
                    if (id1.equals(id2)){
                        String masking = maskingMethod(pass2);
                        JOptionPane.showMessageDialog(null, "あなたのパスワードは"+masking+"です");
                        isSwitch = true;
                        break;
                    }
                }
                if (isSwitch == false){
                    JOptionPane.showMessageDialog(null, "入力したIDはありません");
                }

            }
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }
    private String maskingMethod(String pass2){
        if (pass2.length() <= 2){
            return pass2;
        }
        StringBuffer mask = new StringBuffer();
        mask.append(pass2.charAt(0));
        for (int i = 0; i < pass2.length(); i++){
            if (i < pass2.length() -1){
                mask.append("★");
            }else {
                mask.append(pass2.charAt(i));
            }
        }
        return mask.toString();
    }

    public static void main(String[] args) {
        new Shopping();
    }
}
