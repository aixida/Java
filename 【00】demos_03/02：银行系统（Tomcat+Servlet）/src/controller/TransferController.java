package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TransferController extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aname = request.getParameter("aname");

        PrintWriter out = response.getWriter();
        out.write("<html>");
        out.write(" <head>");
        out.write("     <meta charset=\"UTF-8\">");
        out.write(" </head>");
        out.write(" <body>");
        out.write("     <form action=\"doTransfer\" method=\"post\">");
        out.write("         请输入转账金额:<input type=\"text\" name=\"transferBalance\" value=\"\"><br>");
        out.write("         请输入转账用户名:<input type=\"text\" name=\"transferName\" value=\"\"><br>");
        out.write("         <input type=\"submit\" value=\"确定\">");
        out.write("         <input type=\"hidden\" name=\"aname\" value=\"" + aname + "\">");
        out.write("     </form>");
        out.write(" </body>");
        out.write("</html>");
        out.flush();
    }

}
