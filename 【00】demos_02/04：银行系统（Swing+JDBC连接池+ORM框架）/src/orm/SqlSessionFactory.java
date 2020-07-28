package orm;

import orm.annotation.Delete;
import orm.annotation.Insert;
import orm.annotation.Select;
import orm.annotation.Update;
import pool.ConnectionPool;

import javax.swing.tree.RowMapper;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    //单条查询
    // 返回值: domain层对象
    // 参数: SQL语句  需要返回的对象类型  ？对应的值
    public <T> T selectOne(String sql,  Object obj, Class resultType){
        List<T> results = (List<T>) this.selectList(sql, obj, resultType);
        if (results.size() == 0){
            return null;
        }
        return results.get(0);
    }

    //多条查询
    public <T> List<T> selectList(String sql,  Object obj, Class resultType){
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            SQLAndKey sqlAndKey = handler.parseSQL(sql);
            conn = ConnectionPool.getInstance().getConnection();
            pstat = conn.prepareStatement(sqlAndKey.getSql());
            if (obj != null){
                handler.handleParameter(pstat,obj,sqlAndKey.getKeyList());//负责将SQL与 ? 拼接完整
            }
            rs = pstat.executeQuery();
            while (rs.next()){
                list.add((T)handler.handleResult(rs, resultType));//给对象赋值
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
        return list;
    }

    //============================ ============================ ============================

    //SqlSession为Dao创建动态代理
    public <T> T getMapper(Class c){
        //Java提供了一个现成的类帮我们管理
        //  第一个参数：类加载器（为了生成代理对象，要通过反射去加载）
        //  第二个参数：Class类型的数组，这个数组中传入需要生成代理的类，即告诉我要给谁代理
        //  第三个参数：方法，即告诉我要代理你干什么事
        //      MethodProxy是一个接口，得实现它
        return (T) Proxy.newProxyInstance(c.getClassLoader(), new Class[]{c}, new MethodProxy());
    }

    //内部类
    private class MethodProxy implements InvocationHandler {

        @Override
        //参数：代理对象，被代理方法，被代理方法的参数
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())){
                return method.invoke(this,args);//dao层是正常类，不是接口，不需要代理干活
            }
            Annotation an = method.getAnnotations()[0];
            Class type = an.annotationType();
            Method m = type.getDeclaredMethod("value");
            String sql = (String) m.invoke(an);
            //参数: 1.基本类型 2.map 3.domain 4.无
            Object param = args==null?null:args[0];
            if (type == Insert.class) {
                return SqlSessionFactory.this.insert(sql, param);
            } else if (type == Delete.class) {
                return SqlSessionFactory.this.delete(sql, param);
            }
            else if (type == Update.class) {
                return SqlSessionFactory.this.update(sql, param);
            }
            else if (type == Select.class) {
                //单条查询还是多条查询？？？
                //根据method反射, 通过返回值判断, 单: domaim 多: List<domain>
                Class methodReturnTypeClass = method.getReturnType();
                if (methodReturnTypeClass == List.class){//多条查询
                    //解析集合的泛型
                    //返回值的具体类型, 比如(java.util.List<domain.Student>)
                    //Class是无法操作泛型的, 返回值使用 Type 接口来接收, 需要将 Type 还原成可以操作泛型的类型
                    Type returnType = method.getGenericReturnType();
                    ParameterizedType realReturnType = (ParameterizedType)returnType;
                    Type[] patternTypes = realReturnType.getActualTypeArguments();//获取泛型类
                    Type patternType = patternTypes[0];//当前集合中只有一个泛型
                    return SqlSessionFactory.this.selectList(sql, param, (Class)patternType);
                }else {
                    //单条查询
                    return SqlSessionFactory.this.selectOne(sql, param, methodReturnTypeClass);
                }
            } else {
                System.out.println("无此注解！");
            }
            return null;
        }

    }


}
