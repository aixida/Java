package util;

import java.sql.Connection;

public class ConnectionPool {

    private static final int BUSY_VALUE = 1;
    private static final int FREE_VALUE = 0;
    private static final int NULL_VALUE = -1;

    //连接池
    private static Connection[] connectionList = new Connection[DBConfig.getIntegerValue("minPoolSize","1")];
    //连接池对应状态   占用1/释放0/空置-1
    private static byte[] connectionBitMap = new byte[DBConfig.getIntegerValue("minPoolSize","1")];
    //连接池存量
    private static int total = 0;

    //连接池初始化 每一个connection的状态空置
    static {
        //注意: 增强for循环只适合查询，无法修改引用值！！！
        for(int i = 0; i < connectionBitMap.length; i++){
            connectionBitMap[i] = NULL_VALUE;
        }
    }

    //连接池中获取connection
    public static synchronized Connection getConnection(){
        int freeIndex = getFreeIndex();
        if(freeIndex > -1){
            return distrute(freeIndex);
        }
        if(total < DBConfig.getIntegerValue("maxPoolSize","10")){//允许扩容
            int nullIndex = getNullIndex();
            if(nullIndex == -1){//需要扩容
                nullIndex = grow();
            }
            return distrute(nullIndex);
        }
        return null;//连接池繁忙，没空理你
    }

    //负责在连接池中寻找已经释放的connection
    private static int getFreeIndex(){
        for (int i = 0; i < connectionBitMap.length; i++){
            if(connectionBitMap[i] == FREE_VALUE){
                return i;
            }
        }
        return -1;//无已释放的connection
    }

    //负责在连接池中寻找空置的connection
    private static int getNullIndex(){
        for (int i = 0; i < connectionBitMap.length; i++){
            if(connectionBitMap[i] == NULL_VALUE){
                return i;
            }
        }
        return -1;//无空置的connection
    }

    //分配connection
    private static Connection distrute(int index){
        if (connectionBitMap[index] == BUSY_VALUE){//占用中
            return null;
        }
        Connection connection = null;
        if (connectionBitMap[index] == NULL_VALUE){//空置
            connection = new MyConnection(index);
            connectionList[index] = connection;
            total++;
        } else if(connectionBitMap[index] == FREE_VALUE){//有connection但是处于释放状态
            connection = connectionList[index];
        }
        connectionBitMap[index] = BUSY_VALUE;
        return connection;
    }

    //扩容
    private static int grow(){
        //容量增倍
        Connection[] newConnectionList = new Connection[connectionList.length * 2];
        byte[] newConnectionBitMap = new byte[connectionBitMap.length * 2];
        //复制并初始化
        for (int i = 0; i < connectionList.length; i++){
            newConnectionList[i] = connectionList[i];
            newConnectionBitMap[i] = connectionBitMap[i];
        }
        int firstNullIndex = connectionList.length;
        for (int i = firstNullIndex; i < newConnectionList.length; i++){
            newConnectionBitMap[i] = NULL_VALUE;
        }
        connectionList = newConnectionList;
        connectionBitMap = newConnectionBitMap;
        return firstNullIndex;
    }

    //释放connection
    public static synchronized void freeConnection(MyConnection myConnection){
        connectionBitMap[myConnection.getIndex()] = FREE_VALUE;
    }

}
