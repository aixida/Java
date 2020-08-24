package controller;

import domain.Kind;
import service.KindService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SelectAllKindController extends HttpServlet {

    private KindService service = MySpring.getBean("service.KindService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理字符集
        //接受请求参数
        //调用一个业务层的方法去查询
        ArrayList<Kind> kindList = service.selectAllKind();
        //将查询到的结果带走
        request.setAttribute("kindList", kindList);
        //转发给JSP做拼接响应
        request.getRequestDispatcher("showAllKind.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
