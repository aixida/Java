package test;

import service.AtmService;
import java.util.Scanner;

public class TestMain {

    public Scanner input = new Scanner(System.in);
    public AtmService as = new AtmService();

    //开户
    public void kai(){
        while (true){
            System.out.println("请输入用户名");
            String name = input.nextLine();
            int result = as.isExit(name);
            if(result == 0){
                System.out.println("用户名已存在!!!\n");
                continue;
            }
            System.out.println("请输入密码");
            String password = input.nextLine();
            System.out.println("请输入预存金额");
            String balance = input.nextLine();
            as.kai(name, password, Float.parseFloat(balance));
            System.out.println("开户成功, 进行登录\n");
            login();
        }
    }

    //登录
    public void login(){
        System.out.println("请输入用户名");
        String aname = input.nextLine();
        System.out.println("请输入密码");
        String apassword = input.nextLine();

        String result = as.login(aname,apassword);
        if(result.equals("登录成功")){
            System.out.println("======== 欢迎" + aname + "进入艾希达小笨笨银行系统 ========");
            while(true){
                System.out.println("\n请输入操作选项：查询请输入1, 存款请输入2, 取款请输入3, 转账请输入4, 销户请选择5, 退出请输入0");
                String option = input.nextLine();
                if(option.equals("0")){
                    break;
                }
                switch(option){
                    case "1":
                        float abalance = as.cha(aname);
                        System.out.println("尊敬的" + aname + "用户,您的可用余额为:" + abalance);
                        break;
                    case "2":
                        System.out.println("请您输入存款金额:");
                        String cunMoney = input.nextLine();
                        as.cun(aname,Float.parseFloat(cunMoney));
                        break;
                    case "3":
                        System.out.println("请您输入取款金额:");
                        String quMoney = input.nextLine();
                        as.qu(aname,Float.parseFloat(quMoney));
                        break;
                    case "4":
                        System.out.println("请输入转账用户");
                        String inName = input.nextLine();
                        System.out.println("请输入转账金额");
                        String zhuanMoney = input.nextLine();
                        as.zhuan(aname,inName,Float.parseFloat(zhuanMoney));
                        break;
                    case "5":
                        System.out.println("确认销户（是/否）");
                        String value = input.nextLine();
                        if (value.equals("是")){
                            as.xiao(aname);
                            System.exit(0);
                        }
                        break;
                }
            }
        }else{
            System.out.println(result);
        }
    }

    public static void main(String[] args){
        TestMain m = new TestMain();

        System.out.println("已有账户请输入1, 开户请输入其它");
        String val = m.input.nextLine();
        if(val.equals("1")){
            m.login();
        }else{
            m.kai();
        }

    }
}
