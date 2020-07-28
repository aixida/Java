package orm;

import java.util.ArrayList;
import java.util.List;

//方案二[模拟MyBatis]
public class SQLAndKey {

    //存储解析"特殊SQL"后的信息
    private List<String> keyList = new ArrayList<>();
    private StringBuilder sql = new StringBuilder();

    public SQLAndKey(ArrayList<String> keyList, StringBuilder sql) {
        this.keyList = keyList;
        this.sql = sql;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public String getSql() {
        return sql.toString();
    }

}
