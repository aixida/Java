package dao;

import domain.Kind;

import java.sql.*;
import java.util.ArrayList;

public class KindDao {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/shopping?serverTimezone=CST";
    private String user = "root";
    private String password = "root";

    //查询全部种类数据
    public ArrayList<Kind> selectAllKind(){
        ArrayList<Kind> kindList = new ArrayList<Kind>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT KID,KNAME FROM KIND";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while(rs.next()){
                Kind kind = new Kind();
                kind.setKid(rs.getInt("kid"));
                kind.setKname(rs.getString("kname"));
                kindList.add(kind);
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
        return kindList;
    }

}
