package orm;

import java.sql.ResultSet;

//方案一[模拟SpringJDBC]
public interface RowMapper {

    //策略模式
    //为单条查询定义规则, 将查询完毕的结果集转变为domain对象

    public Object mapperRow(ResultSet rs);

}
