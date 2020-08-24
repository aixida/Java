package dao;

import domain.Commodity;
import domain.Kind;
import domain.User;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("all")
public class CommodityDao {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/shopping?serverTimezone=CST";
    private String user = "root";
    private String password = "root";

    //根据商品的cid 查询单条记录
    public Commodity selectOne(int cid){
        Commodity commodity = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT CID,CNAME,CPRICE,CCOUNT,KID FROM COMMODITY WHERE CID = ?";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1,cid);
            rs = pstat.executeQuery();
            if(rs.next()){
                commodity = new Commodity();
                commodity.setCid(rs.getInt("cid"));
                commodity.setCname(rs.getString("cname"));
                commodity.setCprice(rs.getFloat("cprice"));
                commodity.setCcount(rs.getInt("ccount"));
                Kind kind = new Kind();
                kind.setKid(rs.getInt("kid"));
                commodity.setKind(kind);
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
        return commodity;
    }

    //根据种类编号kid 查询所有的商品
    public ArrayList<Commodity> selectCommodityByKid(int kid){
        ArrayList<Commodity> commodityList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT CID,CNAME,CPRICE,CCOUNT,KID FROM COMMODITY WHERE KID = ?";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1,kid);
            rs = pstat.executeQuery();
            while(rs.next()){
                Commodity commodity = new Commodity();
                commodity.setCid(rs.getInt("cid"));
                commodity.setCname(rs.getString("cname"));
                commodity.setCprice(rs.getFloat("cprice"));
                commodity.setCcount(rs.getInt("ccount"));
                Kind kind = new Kind();
                kind.setKid(rs.getInt("kid"));
                commodity.setKind(kind);
                commodityList.add(commodity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
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
        return commodityList;
    }

    //修改一条记录
    public int update(Commodity commodity){
        int count = 0;//数据库更改的行数==1
        Connection conn = null;
        PreparedStatement pstat = null;
        String sql = "UPDATE COMMODITY SET CNAME = ?,CPRICE = ?,CCOUNT = ? WHERE CID = ?";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, commodity.getCname());
            pstat.setFloat(2, commodity.getCprice());
            pstat.setInt(3, commodity.getCcount());
            pstat.setInt(4, commodity.getCid());
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
