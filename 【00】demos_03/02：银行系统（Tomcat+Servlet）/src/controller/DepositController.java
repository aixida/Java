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

public class DepositController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = request.getParameter("aname");
        String depositBalance = request.getParameter("depositBalance");
        //System.out.println("接收到了名字和存款金额:" + aname + "--" + depositBalance);

        //调用业务层的方法负责存钱
        AtmService service = MySpring.getBean("service.AtmService");
        service.deposit(aname, Float.parseFloat(depositBalance));

        //欢迎界面
        RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");//告知转发路径
        rd.forward(request, response);//转发给服务器
    }

}
