package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryDao {

    String className = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/study?serverTimezone=CST";
    String user = "root";
    String password = "root";

    // 2.查询每个国家的城市个数 按照城市个数升序排列
    // select cname, count(city.cityid) citycount
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // group by cname
    // order by citycount asc;
    // - 参数：无
    // - 返回值：若干个（国家-城市个数）
    public ArrayList<HashMap<String,String>> select2(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "select cname, count(city.cityid) as citycount \n" +
                     "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                     "group by cname \n" +
                     "order by citycount asc;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("cname", rs.getString("cname"));
                map.put("citycount", rs.getString("citycount"));
                list.add(map);
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 4.查询哈尔滨所在的国家的名字
    // select cname
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // where cityname = '哈尔滨';
    // - 参数：城市名字
    // - 返回值：国家名字
    public ArrayList<String> select4(String cityName){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select cname  \n" +
                     "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                     "where cityname = ?;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, cityName);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("cname");
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

    // 7.查询人口最多的城市在哪个国家
    // select cname
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // where citysize = (select max(citysize) from city);
    // - 参数：无
    // - 返回值：国家名字
    public String select7(){
        String value = null;
        String sql = "select cname \n" +
                "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                "where citysize = (select max(citysize) from city);";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()){
                value = rs.getString("cname");
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    // 8. 查询每个国家的人口总数
    // select cname,sum(citysize) as sum
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // group by cname;
    // - 参数：无
    // - 返回值：若干个（国家-人口总数）
    public ArrayList<HashMap<String,String>> select8(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "select cname,sum(citysize) as sum \n" +
                "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                "group by cname;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("cname", rs.getString("cname"));
                map.put("sum", rs.getString("sum"));
                list.add(map);
            }
            rs.close();
            pstat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 9. 查询城市人口总数为1500万的国家名字
    // select cname
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // where citysize = 1500;
    // - 参数：人口总数
    // - 返回值：国家名字
    public ArrayList<String> select9(int size){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select cname \n" +
                "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                "where citysize = ?;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, size);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("cname");
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

    // 11. 查询人口总数超过5000的国家名称
    // select cname
    // from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid
    // group by cname
    // having sum(citysize) > 5000;
    // - 参数：人口总数
    // - 返回值：国家名字
    public ArrayList<String> select11(int size){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select cname \n" +
                "from country inner join area on country.cid = area.cid inner join city on area.aid = city.aid \n" +
                "group by cname \n" +
                "having sum(citysize) > ?;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, size);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("cname");
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

