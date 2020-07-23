package test;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMain {
    public static void main(String[] args) {
        try {
            //1.获取连接
            Connection conn = ConnectionPool.getConnection();
            System.out.println(conn);
            //2.创建状态参数
            PreparedStatement pstat = conn.prepareStatement("select * from student");
            //3.执行就可以啦
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
