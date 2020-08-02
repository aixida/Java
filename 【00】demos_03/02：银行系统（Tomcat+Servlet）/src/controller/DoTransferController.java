package controller;

import service.AtmService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DoTransferController extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = request.getParameter("aname");
        String transferBalance = request.getParameter("transferBalance");
        String transferName = request.getParameter("transferName");
        System.out.println("接收到了转出用户名、转入用户名和取款金额:" + aname + "--" + transferName + "--" + transferBalance);

        //调用业务层的方法负责存钱
        AtmService service = MySpring.getBean("service.AtmService");
        service.transfer(aname, transferName, Float.parseFloat(transferBalance));

        PrintWriter out = response.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("<meta charset=\"UTF-8\">");//告诉浏览器 按照这种字符集解析
        out.write("</head>");
        out.write("<body>");
        out.write("****************************<br>");
        out.write("欢迎" + aname + "进入ATM系统<br>");
        out.write("****************************<br>");
        out.write("请输入操作选项<br>");
        out.write("<a href=\"query?aname=" + aname + "\">查询</a><br>");
        out.write("<a href=\"deposit?aname=" + aname + "\">存款</a><br>");
        out.write("<a href=\"withdraw?aname=" + aname + "\">取款</a><br>");
        out.write("<a href=\"transfer?aname=" + aname + "\">转账</a><br>");
        out.write("<a href=\"delete?aname=" + aname + "\">销户</a><br>");
        out.flush();

    }

}
