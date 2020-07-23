package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private Connection connection;//真实的连接
    private int index;//索引

    public MyConnection(int index){
        this.index = index;
    }

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/study?serverTimezone=CST";
    private static String user = "root";
    private static String password = "root";

    //只加载驱动一次
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //每次调用构造方法，块都会执行一遍（每次new出一个连接）
    {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
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



}
