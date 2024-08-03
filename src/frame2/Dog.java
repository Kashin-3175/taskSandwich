package frame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dog extends JFrame {

    public Dog(){
        setTitle("動物");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel();

        JLabel l1 = new JLabel("IDを入力");
        JTextField t1 = new JTextField(10);

        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();

        JLabel l2 = new JLabel("PWを入力");
        JPasswordField pass1 = new JPasswordField(10);

        p2.add(l2);
        p2.add(pass1);

        JPanel p3 = new JPanel();

        JButton b1 = new JButton("認証");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str1 = t1.getText();
                String password = new String(pass1.getPassword());

                if(str1.equals("犬") && password.equals("1234")){
                    JOptionPane.showMessageDialog(null, "ログイン成功");
                    dispose();
                    new Cat();
                }else{
                    JOptionPane.showMessageDialog(null, "もう一度お試しください");
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
        new Dog();
    }
}
