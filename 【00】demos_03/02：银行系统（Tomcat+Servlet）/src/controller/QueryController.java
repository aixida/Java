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
        //0.处理字符集
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //1.接受请求携带的参数
        String aname = request.getParameter("aname");

        //2.调用业务层的方法
        Float abalance = service.inquire(aname);
        System.out.println(aname + "账号的余额为:" + abalance);

        //3.将余额响应回浏览器
        PrintWriter out = response.getWriter();
        out.write("<html>");
        out.write(" <head>");
        out.write("     <meta charset=\"UTF-8\">");
        out.write("     <script type=\"text/javascript\">");
        out.write("         window.onload=function(){");
        out.write("             var inputElement = document.getElementById(\"back\");");
        out.write("             inputElement.onclick=function(){");
        out.write("                 window.history.back();");
        out.write("             }");
        out.write("         }");
        out.write("     </script>");
        out.write(" </head>");
        out.write(" <body>");
        out.write("     尊敬的" + aname + "您的可用余额为:" + abalance + "<br>");
        out.write("     <input id=\"back\" type=\"button\" value=\"回去\">");
        out.write(" </body>");
        out.write("</html>");
        out.flush();
    }

}
