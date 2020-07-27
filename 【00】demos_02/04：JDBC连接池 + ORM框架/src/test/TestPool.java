package test;

import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("all")
public class TestPool {
    public static void main(String[] args) {

        try {
            //============ 以下是第一次使用连接池的效率 ============

            long t1 = System.currentTimeMillis();

            //1.获取连接
            Connection conn = ConnectionPool.getConnection();

            long t2 = System.currentTimeMillis();

            //2.创建状态参数
            PreparedStatement pstat = conn.prepareStatement("select * from student");
            //3.执行就可以啦
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name") + " - " + rs.getString("sex"));
            }

            long t3 = System.currentTimeMillis();

            System.out.println(t2 - t1);//566
            System.out.println(t3 - t2);//45

            rs.close();
            pstat.close();
            conn.close();

            //============ 以下是第二次使用连接池的效率 ============
            System.out.println("===============");

            long tt1 = System.currentTimeMillis();

            //1.获取连接
            Connection conn2 = ConnectionPool.getConnection();

            long tt2 = System.currentTimeMillis();

            //2.创建状态参数
            PreparedStatement pstat2 = conn2.prepareStatement("select * from student");
            //3.执行就可以啦
            ResultSet rs2 = pstat2.executeQuery();
            while(rs2.next()){
                System.out.println(rs2.getString("name") + " - " + rs2.getString("sex"));
            }

            long tt3 = System.currentTimeMillis();

            System.out.println(tt2 - tt1);//0
            System.out.println(tt3 - tt2);//2

            rs2.close();
            pstat2.close();
            conn2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
