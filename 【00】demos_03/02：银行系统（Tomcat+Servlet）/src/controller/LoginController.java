package controller;

import service.AtmService;
import util.MySpring;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //1.获取请求发送过来的账号和密码
        String aname = request.getParameter("aname");
        String apassword = request.getParameter("apassword");
        //System.out.println("接受到了浏览器发送过来的请求信息" + aname + "--" + apassword);

        //2.负责处理一个业务判断---调用业务层的登录方法
        AtmService service = MySpring.getBean("service.AtmService");
        String result = service.login(aname,apassword);

        //3.根据业务方法的执行结果  给予响应
        if (result.equals("登录成功")) {
            //Session
            HttpSession session = request.getSession();
            session.setAttribute("aname", aname);
            //转发
            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");//告知转发路径
            rd.forward(request, response);//转发给服务器
        } else {
            //重新登录
            request.getRequestDispatcher("login.html").forward(request, response);
        }

    }
}
