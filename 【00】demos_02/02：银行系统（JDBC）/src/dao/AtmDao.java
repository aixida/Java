package dao;

import domain.Atm;

import java.sql.*;

public class AtmDao {

    //DAO持久层

    //负责的是纯粹的JDBC读写数据库操作  没有任何的逻辑
    //对于atm表格的新增 修改 删除 查询单条     MyBatis Hibernate持久层 读写数据库
    //新增 修改 删除---->几乎一致---->优化 SQL 参数
    //查询方法-->与别的类-->每一个类都有查询-->几乎一致--->SQL 参数 处理结果

    String className = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=CST";
    String user = "root";
    String password = "root";

    //设计一个方法 负责将某一条记录删除
    public void delete(String aname){
        Connection conn = null;
        Statement stat = null;
        try {
            String sql = "DELETE FROM ATM WHERE ANAME = '"+aname+"'";
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
            //2.执行一条update语句
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(stat!=null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //设计一个方法 负责将一行新的记录写入数据库
    public void insert(Atm atm){
        Connection conn = null;
        Statement stat = null;
        try {
            String sql = "INSERT INTO ATM VALUES('"+atm.getAname()+"','"+atm.getApassword()+"',"+atm.getAbalance()+")";
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
            //2.执行一条update语句
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(stat!=null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //发现存款和取款方法内部几乎一致  出现大量代码冗余
    //转账  修改密码
    //设计一个方法  做哪些重复的事情  jdbc流程  连接数据库 做数据库的写操作 数据更新
    //  参数?---->Atm  返回值---->void
    public void update(Atm atm){
        Connection conn = null;
        Statement stat = null;
        try {
            String sql = "UPDATE ATM SET APASSWORD = '"+atm.getApassword()+"',ABALANCE = "+atm.getAbalance()+" WHERE ANAME = '"+atm.getAname()+"'";
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
            //2.执行一条update语句
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(stat!=null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //发现登录方法和查询余额的方法内部好多程序都一致  出现代码的冗余
    //设计一个方法  做那些重复的事情   查询一行记录
    //  参数?---->用户名 返回值?---->???
    public Atm selectOne(String aname){
        Atm atm = null;
        String sql = "SELECT ANAME,APASSWORD,ABALANCE FROM ATM WHERE ANAME = '"+aname+"'";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if(rs.next()){
                //证明用户名存在 查询到了一行记录  返回值?
                //将结果集内的数据取出来 数据存在一个容器里  对象domain
                atm = new Atm();
                atm.setAname(rs.getString("aname"));
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(stat!=null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return atm;
    }
}
