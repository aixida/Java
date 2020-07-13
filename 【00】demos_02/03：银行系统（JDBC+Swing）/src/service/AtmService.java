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
    public Float inquire(String aname){
        return dao.selectOne(aname).getAbalance();
    }

    //注册新用户
    public int regist(String aname, String apassword, Float abalance){
        return dao.insert(new Atm(aname,apassword,abalance));
    }

    //判断账号是否存在
    public boolean isExit(String aname){
        if(dao.selectOne(aname) != null){
            return true;//账号存在
        }else {
            return false;
        }
    }

    //存款
    public int deposit(String aname, Float depositMoney){
        Atm atm = dao.selectOne(aname);
        atm.setAbalance(atm.getAbalance() + depositMoney);
        return dao.update(atm);
    }

    //取款
    public int withdraw(String aname, Float withdrawMoney){
        Atm atm = dao.selectOne(aname);
        if (atm.getAbalance() >= withdrawMoney){
            atm.setAbalance(atm.getAbalance() - withdrawMoney);
            return dao.update(atm);//1成功 0失败
        } else{
            return -1;//余额不足
        }
    }

    //转账


    //销户


}
