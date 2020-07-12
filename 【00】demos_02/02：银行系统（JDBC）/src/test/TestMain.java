package test;

import service.AtmService;

import java.util.Scanner;

public class TestMain {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        AtmService ta = new AtmService();
        System.out.println("请输入用户名");
        String aname = input.nextLine();
        System.out.println("请输入密码");
        String apassword = input.nextLine();
        String result = ta.login(aname,apassword);
        if(result.equals("登录成功")){
            System.out.println("\n========\n欢迎"+aname+"进入艾希达小笨笨银行系统\n========");
            while(true){
                System.out.println("\n请输入操作选项：");
                System.out.println("查询请输入1,\n存款请输入2,取款请输入3,转账请输入4,\n退出请输入0");
                String option = input.nextLine();
                if(option.equals("0")){
                    break;
                }
                switch(option){
                    case "1":
                        float abalance = ta.cha(aname);
                        System.out.println("\n尊敬的"+aname+"用户,您的可用余额为:"+abalance);
                        break;
                    case "2":
                        System.out.println("请您输入存款金额:");
                        String cunMoney = input.nextLine();
                        ta.cun(aname,Float.parseFloat(cunMoney));
                        break;
                    case "3":
                        System.out.println("请您输入取款金额:");
                        String quMoney = input.nextLine();
                        ta.qu(aname,Float.parseFloat(quMoney));
                        break;
                    case "4":
                        System.out.println("请输入转账用户");
                        String inName = input.nextLine();
                        System.out.println("请输入转账金额");
                        String zhuanMoney = input.nextLine();
                        ta.zhuan(aname,inName,Float.parseFloat(zhuanMoney));
                        break;
                }
            }
        }else{
            System.out.println(result);
        }
    }
}

