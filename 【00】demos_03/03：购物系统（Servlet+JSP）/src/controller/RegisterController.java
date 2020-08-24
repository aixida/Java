package controller;

import service.UserService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname = request.getParameter("uname");
        String upassword = request.getParameter("upassword");
        String ubalance = request.getParameter("ubalance");

        UserService service = MySpring.getBean("service.UserService");
        int flag = service.regist(uname, upassword, Float.parseFloat(ubalance));
        if (flag == 1) {
            String result = "注册成功，请登录！";
            request.setAttribute("result", result);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            String result = "注册失败，请重新注册！";
            if (flag == 2) {
                result = "用户名已存在！！！";
            }
            request.setAttribute("result", result);
            request.getRequestDispatcher("regist.jsp").forward(request, response);
        }

    }

}
