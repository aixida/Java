package service;

import dao.AtmDao;
import domain.Atm;

public class AtmService {

    //Service业务层需要Dao持久层的支持
    //在Service层中存储一个Dao层的对象作为属性
    //@MyAnnotation("dao.AtmDao")---->IOC
    private AtmDao dao = new AtmDao();

    //以下的方法全都是处理业务逻辑  比较 判断 计算 没有任何一个方法可以看到读写数据库操作
    //登录
    public String login(String aname, String apassword){
        Atm atm = dao.selectOne(aname);
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
        Atm atm = dao.selectOne(aname);
        //只需要做修改的事情  逻辑  计算
        atm.setAbalance(atm.getAbalance() + cunMoney);
        //最终的数据交给 update
        dao.update(atm);
    }
    //取款
    public void qu(String aname, float quMoney){
        Atm atm = dao.selectOne(aname);
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
        Atm outAtm = dao.selectOne(outName);
        Atm inAtm = dao.selectOne(inName);
        if(outAtm.getAbalance() >= zhuanMoney){
            outAtm.setAbalance(outAtm.getAbalance() - zhuanMoney);
            inAtm.setAbalance(inAtm.getAbalance() + zhuanMoney);
            dao.update(outAtm);
            dao.update(inAtm);
        }else{
            System.out.println("对不起,余额不足");
        }
    }
    //开户
    public void kai(String aname, String apassword, float abalance){
        dao.insert(new Atm(aname,apassword,abalance));
    }
    //销户
    public void xiao(String aname){
        Atm atm = dao.selectOne(aname);
        if(atm != null){
            dao.delete(aname);
        }
    }
}
