package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AtmController {

    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginController执行啦");
    }

    public void query(HttpServletRequest request,HttpServletResponse response){
        System.out.println("QueryController执行啦");
    }

}
