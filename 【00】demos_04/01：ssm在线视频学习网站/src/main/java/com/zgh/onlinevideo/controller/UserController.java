package com.zgh.onlinevideo.controller;

import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(User user, String vcode, HttpSession session) {

        String session_vcode = (String) session.getAttribute("session_vcode");

        System.out.println(session_vcode);

        user.setCreateTime(new Date());

        int code = userService.regist(user);

        return "test";
    }

}
