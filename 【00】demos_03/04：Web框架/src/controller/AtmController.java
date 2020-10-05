package controller;

import domain.Person;
import mymvc.Param;

import java.util.HashMap;

/**
 *  1.没有继承关系 降低类和类之间的耦合关系
 *  2.底层还保留了原有Controller的管理机制 (单例 懒加载)
 *  3.没有方法重写 方法名字随意 少了很多约束 使用更加灵活
 *  4.有了最终的转发或重定向 只使用一个简单的String作为返回值
 *  5.减少了方法的参数使用 request response
 *  6.方法的异常没有啦
 */
public class AtmController {

    //参数前注解值 == 请求携带的参数
//    public String login(@Param("name") String name, @Param("pass") String password) {
//    public String login(Person person) {
    public String login(HashMap<String, String> person) {
        System.out.println("LoginController执行啦");
//        System.out.println("接收到参数：" + name + " - " + password);
        System.out.println(person);

        //处理转发与重定向
        //1.直接给予响应 response.getWriter().write("")
        //  返回值类型为void，或者返回“”、“null”
        //2.请求转发 => 交给框架
        //  直接返回 “资源名”
        //3.请求重定向 => 交给框架
        //  返回 “redirect:资源名”
        return "redirect:welcome.jsp";
    }

    public void query(){
        System.out.println("QueryController执行啦");
    }

}
