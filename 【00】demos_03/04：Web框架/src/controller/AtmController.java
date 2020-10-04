package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AtmController {

    public String login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginController执行啦");

        //处理转发与重定向
        //1.直接给予响应 response.getWriter().write("")
        //  返回值类型为void，或者返回“”、“null”
        //2.请求转发 => 交给框架
        //  直接返回 “资源名”
        //3.请求重定向 => 交给框架
        //  返回 “redirect:资源名”
        return "redirect:index.jsp";
    }

    public void query(HttpServletRequest request,HttpServletResponse response){
        System.out.println("QueryController执行啦");
    }

}
