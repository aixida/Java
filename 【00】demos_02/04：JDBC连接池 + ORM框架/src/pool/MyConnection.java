package pool;

import java.sql.*;

public class MyConnection extends AbstractConnection{

    private Connection connection;//真实的连接
    private int index;//索引

    public MyConnection(int index){
        this.index = index;
    }

    private static String driver = DBConfig.getStringValue("driver");
    private static String url = DBConfig.getStringValue("url");
    private static String user = DBConfig.getStringValue("user");
    private static String password = DBConfig.getStringValue("password");

    //只加载驱动一次
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //每次调用构造方法，块都会执行一遍（每次new出一个连接）
    //DriverManager.getConnection()是有异常的, 无法直接给属性赋值, 所以使用块
    {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        ConnectionPool.getInstance().freeConnection(this);
    }

}
