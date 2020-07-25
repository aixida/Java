package controller;

import server.HttpServlet;
import server.HttpServletRequest;
import server.HttpServletResponse;

public class LoginController extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println(name + "---" + password);
    }

}