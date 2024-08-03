package school;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JLabelUpdateExample {
    public static void main(String[] args) {
        // JFrameの作成
        JFrame frame = new JFrame("JLabel Update Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // JLabelの作成
        JLabel label = new JLabel("初期テキスト");
        label.setBounds(50, 50, 300, 30); // (x, y, width, height)
        frame.add(label);

        // JButtonの作成
        JButton button = new JButton("更新");
        button.setBounds(50, 100, 100, 30); // (x, y, width, height)
        frame.add(button);

        // ボタンにアクションリスナーを追加
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("更新されたテキスト");
            }
        });

        // フレームの表示
        frame.setVisible(true);
    }
}
