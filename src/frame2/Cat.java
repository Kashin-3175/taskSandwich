package frame2;

import javax.swing.*;
import java.awt.*;

public class Cat extends JFrame {

    public Cat(){
        setTitle("猫");
        setSize(600,300);
        setLocationRelativeTo(null);

        JPanel mainP = new JPanel(new GridLayout(3,3));

        JButton b1 = new JButton("猫");
        JButton b2 = new JButton("猫");
        JButton b3 = new JButton("猫");
        JButton b4 = new JButton("猫");
        JButton b5 = new JButton("猫");
        JButton b6 = new JButton("猫");
        JButton b7 = new JButton("猫");
        JButton b8 = new JButton("猫");
        JButton b9 = new JButton("猫");

        mainP.add(b1);
        mainP.add(b2);
        mainP.add(b3);
        mainP.add(b4);
        mainP.add(b5);
        mainP.add(b6);
        mainP.add(b7);
        mainP.add(b8);
        mainP.add(b9);

        add(mainP);

        setVisible(true);
    }

}
