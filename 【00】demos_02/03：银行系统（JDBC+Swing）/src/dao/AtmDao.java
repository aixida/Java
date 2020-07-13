package dao;

import domain.Atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AtmDao {

    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=CST";
    private String user = "root";
    private String password = "root";

    //读取一行信息
    public Atm selectOne(String aname){
        Atm atm = null;
        String sql = "select apassword,abalance from atm where aname = ?";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, aname);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()){
                atm = new Atm();
                atm.setAname(aname);
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atm;
    }

    //新增一条记录
    public int insert(Atm atm){
        int count = 0;//数据库更改的行数==1
        String sql = "INSERT INTO ATM VALUES(?,?,?)";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, atm.getAname());
            pstat.setString(2, atm.getApassword());
            pstat.setFloat(3, atm.getAbalance());
            count = pstat.executeUpdate();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


}
