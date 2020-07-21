package util;

import domain.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//类的目的是为了增加一个缓存机制
//程序启动的时候将User.txt文件中的所有信息 一次性读出来
//以后做查询直接读取缓存中的数据  提高读取的性能
public class UserFileReader {

    //缓存
    private static HashMap<String, User> userBox = new HashMap<>();

    //从缓存中获取用户信息
    public static User getUser(String account){
        return userBox.get(account);
    }

    //在当前类加载的时候先执行
    static {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src\\dbfile\\User.txt"));
            String value = br.readLine();
            while (value != null){
                String[] userValue = value.split("-");
                User user = new User(userValue[0],userValue[1]);
                userBox.put(user.getAccount(),user);
                value = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
