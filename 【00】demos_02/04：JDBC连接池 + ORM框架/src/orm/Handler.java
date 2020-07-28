package orm;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//方案二[模拟MyBatis]
public class Handler {

    //解析一条特殊结构的SQL
    //  - 参数: 特殊SQL语句
    //  - 返回值: 下面两个玩意包装成一个对象
    //      获取一堆key ArrayList<String>
    //      一条 PreparedStatement 需要的SQL语句
    SQLAndKey parseSQL(String sql) {
        StringBuilder newSQL = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>();
        while(true){
            int left = sql.indexOf("#{");
            int right = sql.indexOf("}");
            if(left != -1 && right != -1 && left < right){
                newSQL.append(sql.substring(0,left));
                newSQL.append("?");
                keyList.add(sql.substring(left+2,right));//key
                sql = sql.substring(right+1);
            }else{//SQL语句中无key了
                newSQL.append(sql);
                break;
            }
        }
        return new SQLAndKey(keyList,newSQL);
    }

    //负责将SQL与 ? 拼接完整
    void handleParameter(PreparedStatement pstat, Object obj, List<String> keyList) throws SQLException {
        Class c = obj.getClass();
        //c（存储 ? 对应的若干个值）一般的类型为：
        //  查询、删除: 基本数据类型或者String
        //  增加、修改: domain或者map
        if(c == int.class || c == Integer.class){
            pstat.setInt(1,(Integer)obj);
        }else if(c == float.class || c == Float.class){
            pstat.setFloat(1,(Float)obj);
        }else if(c == double.class || c == Double.class){
            pstat.setDouble(1,(Double)obj);
        }else if(c == String.class){
            pstat.setString(1,(String)obj);
        }else{
            //1.map
            if (obj instanceof Map){
                for(int i = 0; i < keyList.size(); i++){
                    Map map = (Map)obj;
                    pstat.setObject(i+1,map.get(keyList.get(i)));
                }
            }else{
                //2.domain
                try {
                    for(int i = 0; i < keyList.size(); i++){
                        String key = keyList.get(i);
                        Field field = c.getDeclaredField(key);
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        pstat.setObject(i+1,value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
