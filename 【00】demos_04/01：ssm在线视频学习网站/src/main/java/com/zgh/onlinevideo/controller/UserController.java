package com.zgh.onlinevideo.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.dto.LoginToken;
import com.zgh.onlinevideo.dto.ResponseResult;
import com.zgh.onlinevideo.exception.UserException;
import com.zgh.onlinevideo.service.UserService;
import com.zgh.onlinevideo.util.Constants;
import com.zgh.onlinevideo.util.VideoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

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

        HttpSession session = request.getSession(true);

        if (StrUtil.isEmpty(user.getEmail()) || StrUtil.isEmpty(user.getPassword())) {
            // TODO 跳转到错误界面
            throw new UserException("参数错误");
        }

        User dbUser = userService.login(user);
        if (dbUser != null) {
            session.setAttribute(Constants.LOGIN_USER, dbUser);
        }

        // TODO 上面是账号密码的判断

        // TODO 自动登录
        if ("1".equals(autoLogin)) {
            // 1 生成cookie返回给客户端凭证
            // 2 服务器端保存token对应loginToken数据 application
            LoginToken loginToken = VideoUtil.generateLoginToken(request, dbUser);
            Cookie cookie = new Cookie("autoToken", loginToken.generateToken());

            // 设置COOKIE保存属性
            cookie.setPath("/");
            // 单位秒
            cookie.setMaxAge(60 * 60 * 48); // 48小时 60 * 60 * 48;

            response.addCookie(cookie);

            // TODO 服务器保存对应的LoginToken用户登录数据
            ServletContext application = request.getServletContext();
            HashMap<String, LoginToken> tokenMap = (HashMap<String, LoginToken>) application.getAttribute(Constants.AUTO_LOGIN_TOKEN);
            if (tokenMap == null) {
                // 初始化
                tokenMap = new HashMap<>();
                tokenMap.put(loginToken.generateToken(), loginToken);
                application.setAttribute(Constants.AUTO_LOGIN_TOKEN, tokenMap);
            } else {
                // 已经初始化，直接保存loginToken
                tokenMap.put(loginToken.generateToken(), loginToken);
            }
        }

        return "redirect:/";

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        // 1 清空用户Session
        HttpSession session = request.getSession(true);
        session.removeAttribute(Constants.LOGIN_USER);

        // 2 清空application中的用户登录数据
        String cookieToken = VideoUtil.getCookieTokenValue(request.getCookies());
        if (!StrUtil.isEmpty(cookieToken)) {
            ServletContext application = request.getServletContext();
            HashMap<String, LoginToken> tokenMap = (HashMap<String, LoginToken>) application.getAttribute(Constants.AUTO_LOGIN_TOKEN);
            tokenMap.remove(Constants.AUTO_LOGIN_TOKEN);
        }

        // 3 设置cookie失效
        Cookie cookie = new Cookie("autoToken", "invalid");
        cookie.setPath("/");
        cookie.setMaxAge(1);
        response.addCookie(cookie);

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
