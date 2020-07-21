package service;

import dao.UserDao;
import domain.User;
import util.MySpring;

//Service业务层
//负责处理读到的数据, 负责处理业务逻辑产生的新数据
public class UserService {

    private UserDao dao = MySpring.getBean("dao.UserDao");

    //登录
    public String login(String account, String password){
        User user = dao.selectOne(account);
        String result = "登录成功";
        if(user != null && user.getPassword().equals(password)){

        }else{
            result = "用户名或密码错误";
        }
        return result;
    }

}
