package dao;

import domain.Atm;

import java.sql.*;

public class AtmDao {

    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=CST";
    private String user = "root";
    private String password = "root";

    //读取一行信息
    public Atm selectOne(String aname){
        Atm atm = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT APASSWORD,ABALANCE FROM ATM WHERE ANAME = ?";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, aname);
            rs = pstat.executeQuery();
            if (rs.next()){
                atm = new Atm();
                atm.setAname(aname);
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(pstat != null) {
                    pstat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return atm;
    }

    //新增一条记录
    public int insert(Atm atm){
        int count = 0;//数据库更改的行数==1
        Connection conn = null;
        PreparedStatement pstat = null;
        String sql = "INSERT INTO ATM VALUES(?,?,?)";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, atm.getAname());
            pstat.setString(2, atm.getApassword());
            pstat.setFloat(3, atm.getAbalance());
            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstat != null) {
                    pstat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    //修改一条记录
    public int update(Atm atm){
        int count = 0;//数据库更改的行数 == 1
        Connection conn = null;
        PreparedStatement pstat = null;
        String sql = "UPDATE ATM SET APASSWORD = ?,ABALANCE = ? WHERE ANAME =?";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, atm.getApassword());
            pstat.setFloat(2, atm.getAbalance());
            pstat.setString(3, atm.getAname());
            count = pstat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(pstat != null) {
                    pstat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    //删除

}
