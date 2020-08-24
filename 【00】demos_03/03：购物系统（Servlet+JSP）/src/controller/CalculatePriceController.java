package controller;

import domain.Commodity;
import service.UserService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CalculatePriceController extends HttpServlet {

    private UserService service = MySpring.getBean("service.UserService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        HttpSession session = request.getSession();
        HashMap<Commodity, Integer> shoppingCar = (HashMap<Commodity, Integer>) session.getAttribute("shoppingCar");
        //计算价格
        float sumPrice = 0;
        Set<Commodity> keys =  shoppingCar.keySet();
        Iterator<Commodity> it =  keys.iterator();
        while(it.hasNext()){
            Commodity key = it.next();
            int count = shoppingCar.get(key);
            Float price = key.getCprice();
            sumPrice += count * price;
        }
        //将计算好的价钱存起来
        request.setAttribute("sumPrice", sumPrice);
        //判断用户余额是否充足
        float balance = service.selectBalance((String) request.getSession().getAttribute("uname"));
        String result = "";
        if (balance >= sumPrice) {
            result = "您的余额充足";
        } else {
            result = "您的余额不足";
        }
        request.setAttribute("result", result);
        request.getRequestDispatcher("showEndList.jsp").forward(request,response);
    }

}
