package service;

import dao.AtmDao;
import domain.Atm;
import util.MySpring;

public class AtmService {

    private AtmDao dao = MySpring.getBean("dao.AtmDao");

    //登录
    public String login(String aname,String apassword){
        String result = "登录名或密码错误";
        Atm atm = dao.selectOne(aname);
        if(atm != null && apassword.equals(atm.getApassword())){
            result = "登录成功";
        }
        return result;
    }

    //查询余额


    //注册新用户


    //判断账号是否存在


}
