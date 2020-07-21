package dao;

import domain.User;
import util.FileLoaderAndCommit;

import java.util.HashMap;

public class AtmDao {

    private FileLoaderAndCommit flac = new FileLoaderAndCommit("src\\User.txt");

    //获取缓存
    private HashMap<String, User> userBox = flac.loadFile();

    //查询一行记录
    public User selectOne(String name){
        return userBox.get(name);
    }

    //更新一行记录
    public void update(User user){
        userBox.put(user.getAname(),user);
        flac.commit(userBox);
    }

    public void insert(User user){
        userBox.put(user.getAname(),user);
        flac.commit(userBox);
    }

    //删除一行记录
    public void delete(String name){
        userBox.remove(name);
        flac.commit(userBox);
    }

}
