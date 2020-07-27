package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("all")
public class TestJDBC {

    public static void main(String[] args) {
        //============ 以下纯粹的JDBC操作的效率 ============

        try {
            long t1 = System.currentTimeMillis();

            //1.获取连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/study?serverTimezone=CST","root","root");

            long t2 = System.currentTimeMillis();

            //2.创建状态参数
            PreparedStatement pstat = conn.prepareStatement("select * from student");
            //3.执行就可以啦
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name") + " - " + rs.getString("sex"));
            }

            long t3 = System.currentTimeMillis();

            System.out.println(t2 - t1);//580
            System.out.println(t3 - t2);//48

            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
