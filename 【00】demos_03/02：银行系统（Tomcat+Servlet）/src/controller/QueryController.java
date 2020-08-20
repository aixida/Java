package controller;

import service.AtmService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryController extends HttpServlet {

    private AtmService service = MySpring.getBean("service.AtmService");

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = (String) request.getSession().getAttribute("aname");
        Float abalance = service.inquire(aname);
        //System.out.println(aname + "账号的余额为:" + abalance);

        request.setAttribute("abalance", abalance);//需要将 abalance 传给JSP文件
        request.getRequestDispatcher("showBalance.jsp").forward(request, response);
    }

}
