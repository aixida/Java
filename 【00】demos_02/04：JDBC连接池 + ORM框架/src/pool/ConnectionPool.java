package pool;

import java.sql.Connection;

/**
 * 1.mysql、数据库名字、账号、密码等不能修改 （使用配置文件与IO）
 *
 * 2.连接池只需要一个   （单例模式或者静态）
 *
 * 3.连接池并发执行, 出现线程安全问题  （线程安全锁）
 *
 * 4.连接池容量不够，未达到上限  （动态扩容）
 *
 * 5.连接池无法扩容, 并且连接用完了   （等待机制）
 *
 * 6.连接池对于用户的使用方式不一样了   （静态代理模式）
 */
public class ConnectionPool {

    //单例模式（双重检测模型）
    private ConnectionPool(){}
    private static volatile ConnectionPool connectionPool;//修饰符volatile, 防止指令重排
    public static ConnectionPool getInstance(){
        if (connectionPool == null){
            synchronized (ConnectionPool.class){
                if (connectionPool == null){
                    connectionPool = new ConnectionPool();//创建对象内存中有3个指令（分配空间、摆放东西、空间创建好后, 传给地址引用）
                }
            }

        }
        return connectionPool;
    }

    private static final int BUSY_VALUE = 1;
    private static final int FREE_VALUE = 0;
    private static final int NULL_VALUE = -1;

    //连接池
    private Connection[] connectionList = new Connection[DBConfig.getIntegerValue("minPoolSize","5")];
    //连接池对应状态   占用1/释放0/空置-1
    private byte[] connectionBitMap = new byte[DBConfig.getIntegerValue("minPoolSize","5")];
    //连接池存量
    private int total = 0;

    //连接池初始化 每一个connection的状态空置
    {
        //注意: 增强for循环只适合查询，无法修改引用值！！！
        for(int i = 0; i < connectionBitMap.length; i++){
            connectionBitMap[i] = NULL_VALUE;
        }
    }

    //连接池中获取connection
    public synchronized Connection getConnection(){
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
    private int getFreeIndex(){
        for (int i = 0; i < connectionBitMap.length; i++){
            if(connectionBitMap[i] == FREE_VALUE){
                return i;
            }
        }
        return -1;//无已释放的connection
    }

    //负责在连接池中寻找空置的connection
    private int getNullIndex(){
        for (int i = 0; i < connectionBitMap.length; i++){
            if(connectionBitMap[i] == NULL_VALUE){
                return i;
            }
        }
        return -1;//无空置的connection
    }

    //分配connection
    private Connection distrute(int index){
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
    private int grow(){
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
    public synchronized void freeConnection(MyConnection myConnection){
        connectionBitMap[myConnection.getIndex()] = FREE_VALUE;
    }

}
