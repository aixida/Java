package controller;

import domain.Commodity;
import service.CommodityService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SelectCommodityController extends HttpServlet {

    private CommodityService service = MySpring.getBean("service.CommodityService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.处理字符集 kid
        //2.接收请求传递过来的参数 kid
        String kid = request.getParameter("kid");
        //3.调用业务层的方法负责查询
        ArrayList<Commodity> commodityList = service.selectCommodityById(Integer.parseInt(kid));
        //4.将查询完毕的商品集合带走
        request.setAttribute("commodityList", commodityList);
        //5.转发给一个JSP做最终响应拼接
        request.getRequestDispatcher("showCommodity.jsp").forward(request, response);
    }

}
