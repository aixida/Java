package controller;

import service.AtmService;
import util.MySpring;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = request.getParameter("aname");

        AtmService service = MySpring.getBean("service.AtmService");
        service.deleteAccount(aname);
        //System.out.println("删除账号: " + aname);

        //回到首页
        RequestDispatcher rd = request.getRequestDispatcher("showGoodbye.jsp");//告知转发路径
        rd.forward(request, response);//转发给服务器
    }

}
