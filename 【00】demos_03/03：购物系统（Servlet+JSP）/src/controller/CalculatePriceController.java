package controller;

import domain.Commodity;

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
        //转发做最后列表展示
        request.getRequestDispatcher("showEndList.jsp").forward(request,response);
    }

}
