package dao;

import domain.User;

import java.sql.*;

public class UserDao {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/shopping?serverTimezone=CST";
    private String user = "root";
    private String password = "root";

    //查询单条记录
    public User selectOne(String uname){
        User result = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT UNAME,UPASSWORD,UBALANCE FROM USER WHERE UNAME = ?";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,uname);
            rs = pstat.executeQuery();
            if(rs.next()){
                result = new User();
                result.setUname(rs.getString("uname"));
                result.setUpassword(rs.getString("upassword"));
                result.setUbalance(rs.getFloat("ubalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (pstat != null){
                    pstat.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }

    //新增一条记录
    public int insert(User auser){
        int count = 0;//数据库更改的行数==1
        Connection conn = null;
        PreparedStatement pstat = null;
        String sql = "INSERT INTO USER VALUES(?,?,?)";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, auser.getUname());
            pstat.setString(2, auser.getUpassword());
            pstat.setFloat(3, auser.getUbalance());
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

}
