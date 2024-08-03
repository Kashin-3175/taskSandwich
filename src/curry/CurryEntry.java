package curry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class CurryEntry extends JFrame {
    public CurryEntry(){
        setTitle("会員登録");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));
        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);

        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("PASSを入力");
        JPasswordField ps1 = new JPasswordField(10);

        p2.add(l2);
        p2.add(ps1);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("登録");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = t1.getText();
                String pass1 = new String(ps1.getPassword());
                String fileName = "src/curry/curry.csv";

                try{
                    if (id1.isEmpty() && pass1.isEmpty()){
                        JOptionPane.showMessageDialog(null,"入力してください");
                    }else{
                        FileWriter writer = new FileWriter(fileName, true);
                        writer.write(id1 + "," + pass1 + "\n");
                        writer.close();
                        JOptionPane.showMessageDialog(null, "登録しました");
                        dispose();
                        new CurryLogin();
                    }

                }catch (Exception e2){
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "登録に失敗しました");
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
}
