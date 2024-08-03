package data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListSelectionToTextField extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private JTextField selectedItemField;

    public ListSelectionToTextField() {
        setTitle("List to TextField");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // リストのモデルとリストの作成
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(itemList);

        // JTextFieldの作成
        selectedItemField = new JTextField();
        selectedItemField.setEditable(false);

        // レイアウトの設定
        setLayout(new BorderLayout());
        add(listScrollPane, BorderLayout.CENTER);
        add(selectedItemField, BorderLayout.SOUTH);

        // リストにサンプル項目を追加
        addSampleItems();

        // リストの選択イベントのリスナーを追加
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = itemList.getSelectedValue();
                selectedItemField.setText(selectedValue);
            }
        });
    }

    private void addSampleItems() {
        String[] items = {"Bread", "Lettuce", "Tomato", "Cheese", "Ham", "Turkey", "Bacon"};
        for (String item : items) {
            listModel.addElement(item);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListSelectionToTextField().setVisible(true));
    }
}
