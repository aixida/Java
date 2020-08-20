package controller;

import service.AtmService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = request.getParameter("aname");
        String apassword = request.getParameter("apassword");
        String abalance = request.getParameter("abalance");

        AtmService service = MySpring.getBean("service.AtmService");
        service.regist(aname,apassword,Float.parseFloat(abalance));
        //System.out.println("新注册账号: " + aname + "---" + apassword + "---" + abalance);

        PrintWriter out = response.getWriter();
        out.write("<html>");
        out.write(" <head>");
        out.write("     <meta charset=\"UTF-8\">");
        out.write(" </head>");
        out.write(" <body>");
        out.write("     <a href=\"login.html\">请进入登录界面</a>");
        out.write(" </body>");
        out.write("</html>");
        out.flush();
    }

}
