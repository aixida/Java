package service;

import dao.AtmDao;
import domain.User;

public class AtmService {

    private AtmDao dao = new AtmDao();

    //以下的方法全都是处理业务逻辑  比较 判断 计算 没有任何一个方法可以看到读写数据库操作
    //登录
    public String login(String aname, String apassword){
        User atm = dao.selectOne(aname);
        if(atm != null && atm.getApassword().equals(apassword)){
            return "登录成功";
        }
        return "用户名或密码错误";
    }

    //查询余额
    public float cha(String aname){
        return dao.selectOne(aname).getAbalance();
    }

    //存款
    public void cun(String aname, float cunMoney){
        //找寻原始的数据  atm = selectOne
        User atm = dao.selectOne(aname);
        //只需要做修改的事情  逻辑  计算
        atm.setAbalance(atm.getAbalance() + cunMoney);
        //最终的数据交给 update
        dao.update(atm);
    }

    //取款
    public void qu(String aname, float quMoney){
        User atm = dao.selectOne(aname);
        if(atm.getAbalance() >= quMoney) {
            atm.setAbalance(atm.getAbalance() - quMoney);
            dao.update(atm);
        }else{
            System.out.println("对不起,余额不足");
        }
    }

    //转账
    public void zhuan(String outName, String inName, float zhuanMoney){
//        this.qu(outName,zhuanMoney);
//        this.cun(inName,zhuanMoney);
        User outAtm = dao.selectOne(outName);
        User inAtm = dao.selectOne(inName);
        if(outAtm.getAbalance() >= zhuanMoney){
            if (inAtm != null){
                outAtm.setAbalance(outAtm.getAbalance() - zhuanMoney);
                inAtm.setAbalance(inAtm.getAbalance() + zhuanMoney);
                dao.update(outAtm);
                dao.update(inAtm);
            }else{
                System.out.println("对不起,您输入的转入账户不存在");
            }
        }else{
            System.out.println("对不起,余额不足");
        }
    }

    //开户
    public void kai(String aname, String apassword, float abalance){
        dao.insert(new User(aname,apassword,abalance));
    }

    //用户名是否已存在
    public int isExit(String name){
        if(dao.selectOne(name) == null){
            return 1;
        }else{
            return 0;//用户名已存在
        }
    }

    //销户
    public void xiao(String aname){
        User atm = dao.selectOne(aname);
        if(atm != null){
            dao.delete(aname);
        }
    }

}
