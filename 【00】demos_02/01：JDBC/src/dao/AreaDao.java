package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class AreaDao {

    String className = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/study?serverTimezone=CST";
    String user = "root";
    String password = "root";

    // 1.查询人口数在1000到2000之间的城市所属在哪个地区
    // select distinct aname from area a inner join city ci on a.aid=ci.aid where ci.citysize between 1000 and 2000;
    // - 参数：人口范围 1000 ~ 2000
    // - 返回值：几个不同的地区名
    public ArrayList<String> select1(int begin, int end){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select distinct aname \n" +
                     "from city inner join area \n" +
                     "on city.aid = area.aid  \n" +
                     "where city.citysize between ? and ?;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, begin);
            pstat.setInt(2, end);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                String value = rs.getString("aname");
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

    // 3.查询各地区城市人口平均数，按照人口平均数降序排列
    // select aname, avg(citysize) as avg
    // from area inner join city on area.aid = city.aid
    // group by aname
    // order by avg desc;
    // - 参数：无
    // - 返回值：若干个（城市-人口平均数）
    public ArrayList<HashMap<String,String>> select3(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "select aname, avg(citysize) as avg \n" +
                     "from area inner join city on area.aid = city.aid \n" +
                     "group by aname \n" +
                     "order by avg desc;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("aname", rs.getString("aname"));
                map.put("avg", rs.getString("avg"));
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

    // 5.查询各地区名字和人口总数
    // select aname,sum(citysize) as sum
    // from area inner join city on area.aid = city.aid
    // group by aname;
    // - 参数：无
    // - 返回值：若干个（地区名-地区人口总数）
    public ArrayList<HashMap<String,String>> select5(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "select aname,sum(citysize) as sum \n" +
                     "from area inner join city on area.aid = city.aid  \n" +
                     "group by aname;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("aname", rs.getString("aname"));
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

    // 10.查询各地区总人数，按照人口总数降序排列
    // select aname,sum(citysize) as sum
    // from area inner join city on area.aid = city.aid
    // group by aname
    // order by sum desc;
    // - 参数：无
    // - 返回值：若干个（地区-人口总数）
    public ArrayList<HashMap<String,String>> select10(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "select aname,sum(citysize) as sum \n" +
                "from area inner join city on area.aid = city.aid \n" +
                "group by aname \n" +
                "order by sum desc;";
        try {
            Class.forName(className);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                HashMap<String, String> map = new HashMap<>();
                map.put("aname", rs.getString("aname"));
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


}
