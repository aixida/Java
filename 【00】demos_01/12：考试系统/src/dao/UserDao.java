package dao;

import domain.User;
import util.UserFileReader;

//持久层：数据的持久化
//只负责数据的读、写，不负责处理逻辑
//本代码仅仅包含了I/O（学习了数据库后，这个层次的内部代码都是JDBC）
public class UserDao {

    //缓存---虚拟机(内存中)---存储文件中的所有信息

    //查询一行记录
    public User selectOne(String account){
        return UserFileReader.getUser(account);
    }



}
