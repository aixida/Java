package orm;

import domain.Student;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlSessionFactory {

    private SqlSessionFactory(){}
    private static SqlSessionFactory ssf;
    public static SqlSessionFactory getInstance(){
        if (ssf == null){
            ssf = new SqlSessionFactory();
        }
        return ssf;
    }

    //============================ 方案二[模拟MyBatis] - begin ============================

    private Handler handler = new Handler();

    //修改单条记录
    public int update(String sql, Object obj){
        int count = -1;
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            //解析特殊结构的SQL    insert into  student values(#{id},#{name},#{sex},#{birth},#{ctime});
            //1.将SQL中的5个key获取出来
            //2.将SQL中的5个key替换成5个？
            SQLAndKey sqlAndKey = handler.parseSQL(sql);
            conn = ConnectionPool.getInstance().getConnection();
            pstat = conn.prepareStatement(sqlAndKey.getSql());
            if (obj != null){
                handler.handleParameter(pstat,obj,sqlAndKey.getKeyList());//负责将SQL与 ? 拼接完整
            }
            count = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
        return count;
    }
    public int update(String sql) {
        return this.update(sql, null);
    }

    //删除单条记录
    public int delete(String sql, Object obj) {
        return this.update(sql,obj);
    }
    public int delete(String sql) {
        return this.update(sql, null);
    }

    //增加单条记录
    public int insert(String sql, Object obj) {
        return this.update(sql,obj);
    }
    public int insert(String sql) {
        return this.update(sql, null);
    }

    //============================ 方案一[模拟MyBatis] - end ============================

    //以下为方案一[模拟SpringJDBC]
    //  1.传递values数组里面的值有顺序
    //  2.values参数可读性不强

    //增删改
    public int update1(String sql, Object ...values) {
        int count = -1;
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            pstat = conn.prepareStatement(sql);
            for(int i = 0; i < values.length; i++){
                pstat.setObject(i+1, values[i]);
            }
            count = pstat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
        return count;
    }
    public int update1(String sql){
        return this.update1(sql, null);
    }

    public int insert1(String sql, Object ...values) {
        return this.update1(sql, values);
    }
    public int insert1(String sql){
        return this.update1(sql, null);
    }

    public int delete1(String sql, Object ...values) {
        return this.update1(sql, values);
    }
    public int delete1(String sql){
        return this.update1(sql, null);
    }

    //单条查询
    public <T>T selectOne1(String sql, RowMapper mapper, Object ...values){
        T obj = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            pstat = conn.prepareStatement(sql);
            for(int i = 0; i < values.length; i++){
                pstat.setObject(i+1, values[i]);
            }
            rs = pstat.executeQuery();

            //处理结果集 rs再返回之前需要关闭, 所以需要把rs中的数据取出来
            //解决方案1: domain类型, 采用反射
            //解决方案2: map集合
            //解决方案2: domain类型, 读取SQL语句中涉及的数据库
            //解决方案4: 策略模式, 流程相同而执行策略不同  （采取该方案）
            if (rs.next()){
                obj = (T)mapper.mapperRow(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
        return obj;
    }

}
