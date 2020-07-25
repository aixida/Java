package controller;

import server.HttpServlet;
import server.HttpServletRequest;
import server.HttpServletResponse;

public class IndexController extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response){
        //1.获取请求发送过来携带的参数
        //2.找到某一个业务层的方法做事
        //3.将最终业务层执行完毕的结果，交还给服务器，让服务器写回给浏览器

        response.sendRedirect("index.view");

    }

}
