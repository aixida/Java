package com.zgh.onlinevideo.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.dto.ResponseResult;
import com.zgh.onlinevideo.exception.UserException;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult checkLogin(User user) {

        ResponseResult responseResult = new ResponseResult(1, "ok");

        User dbUser = userService.login(user);

        if (StrUtil.isEmpty(user.getEmail()) || StrUtil.isEmpty(user.getPassword()) || dbUser == null) {
            responseResult.setRcode(-1);
            responseResult.setMessage("login fail");
        }

        return responseResult;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, String autoLogin, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (StrUtil.isEmpty(user.getEmail()) || StrUtil.isEmpty(user.getPassword())) {
            // TODO 跳转到错误界面
            throw new UserException("参数错误");
        }

        User dbUser = userService.login(user);
        if (dbUser != null) {
            session.setAttribute("login_user", user);
        }


        return "redirect:/";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("login_user");

        return "redirect:/";

    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(User user, String vcode, HttpSession session) {

        // TODO 后台表单数据验证
        String session_vcode = (String) session.getAttribute("session_vcode");

        if (StrUtil.isEmpty(session_vcode)) {
            // TODO 跳转到错误界面
            throw new UserException("验证码错误");
        }

        boolean emailMatch = ReUtil.isMatch("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", user.getEmail());
        boolean numberMatch = ReUtil.isMatch("1[3456789]\\d{9}$", user.getNumber());
        boolean passwordMatch = ReUtil.isMatch("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$", user.getPassword());

        if (!emailMatch || !numberMatch || !passwordMatch) {
            // TODO 跳转到错误界面
            throw new UserException("注册信息错误");
        }

        // 参数验证通过
        user.setCreateTime(new Date());
        int code = userService.regist(user);
        if (code != 1) {
            // TODO 数据库插入数据失败 跳转到错误界面
            throw new UserException("注册失败");
        }

        // 注册成功 直接登录
        session.setAttribute("login_user", user);

        return "redirect:/";
    }

    @RequestMapping("checkEmail")
    @ResponseBody
    public ResponseResult checkEmail(String email) {

        ResponseResult result = new ResponseResult(-1, "email exist");

        User user = userService.existEmail(email);

        if (user == null) {
            result.setRcode(1);
            result.setMessage("ok");
        }

        return result;
    }


}
