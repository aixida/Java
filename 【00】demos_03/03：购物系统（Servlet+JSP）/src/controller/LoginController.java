package controller;

import service.UserService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private UserService service = MySpring.getBean("service.UserService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.处理字符集
        request.setCharacterEncoding("UTF-8");
        //2.接受请求发送过来的参数
        String uname = request.getParameter("uname");
        String upassword = request.getParameter("upassword");
        //3.调用业务层的方法执行操作
        String result = service.login(uname, upassword);
        //4.根据结果转发
        if(result.equals("登录成功")){
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }else{
            request.setAttribute("result", result);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

}
