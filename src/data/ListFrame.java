package data;

import util.DbConn;
import vo.UserInfoLoinUserDetails;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListFrame extends JFrame {
    private List<UserInfoLoinUserDetails> voList;

    public ListFrame(){
        DbConn.getConnection();
        setTitle("WEAVUS社員管理システム");
        setSize(380,500);
        setLocationRelativeTo(null);

        voList = findUserInfoJoinUserDetails();

        JPanel mainP = new JPanel(new GridLayout(3,1));

        JPanel p1 = new JPanel(new BorderLayout());
        JLabel l1 = new JLabel("件数: " + voList.size() + "件");
        p1.add(l1, BorderLayout.WEST);

        JPanel p2 = new JPanel();
        JTextArea ta = new JTextArea(10, 30);
        ta.setText("ID\t名前\t性別\t年齢\n");
        for (UserInfoLoinUserDetails vo : voList) {
            ta.append(vo.getId() + "\t" + vo.getName() + "\t" + vo.getGender() + "\t" + vo.getAge() + "\n");
        }
        ta.setEditable(false);
        p2.add(new JScrollPane(ta));

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("戻り");
        b1.addActionListener(e -> System.exit(0));  // ボタンを押すとプログラムを終了するように設定
        p3.add(b1);

        mainP.add(p1);
        mainP.add(p2);
        mainP.add(p3);

        add(mainP);

        setVisible(true);
    }

    private List<UserInfoLoinUserDetails> findUserInfoJoinUserDetails(){
        String sql = "SELECT i.id id, i.name as name1, i.gender gender, i.age age, d.salary salary, d.job_status job_status FROM userinfo i INNER JOIN userdetail d ON i.id = d.userid";
        List<UserInfoLoinUserDetails> voList = new ArrayList<>();

        try {
            Statement st = DbConn.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                UserInfoLoinUserDetails vo = new UserInfoLoinUserDetails();
                vo.setId(rs.getString("id"));
                vo.setName(rs.getString("name1"));
                vo.setGender(rs.getString("gender"));
                vo.setAge(rs.getInt("age"));
                vo.setSalary(rs.getInt("salary"));
                vo.setJobstatus(rs.getString("job_status"));
                voList.add(vo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voList;
    }

    public static void main(String[] args) {
        new ListFrame();
    }
}
