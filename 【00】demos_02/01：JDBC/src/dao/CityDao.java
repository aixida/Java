package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CityDao {

    String className = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/study?serverTimezone=CST";
    String user = "root";
    String password = "root";

    // 6.查询美国有哪些城市，列出城市名
    // select cityname
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // where cname = '美国';
    // - 参数：国家名
    // - 返回值：若干个城市名
    public ArrayList<String> select6(String countryName){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select cityname \n" +
                "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                "where cname = ?;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, countryName);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("cityname");
                list.add(value);
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 12.查询人口数大于杭州的城市都有哪些
    // select cityname
    // from city
    // where citysize > (select citysize from city where cityname = '杭州');
    // - 参数：城市名
    // - 返回值：若干个城市名
    public ArrayList<String> select12(String cityName){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select cityname \n" +
                "from city \n" +
                "where citysize > (select citysize from city where cityname = ?);";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, cityName);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("cityname");
                list.add(value);
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
