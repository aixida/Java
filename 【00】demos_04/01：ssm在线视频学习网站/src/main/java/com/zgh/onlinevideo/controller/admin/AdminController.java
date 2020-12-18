package com.zgh.onlinevideo.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    // 后台页面
    @RequestMapping("/admin")
    public String adminPage(Model model) {

        PageHelper.startPage(1, 8);
        PageInfo<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "admin_main";

    }

    //用户列表 显示所有用户
    @RequestMapping(value = "/admin/user")
    public String userPage(Model model, Integer pageNum) {

        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, 8);

        PageInfo<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "admin_main";
    }

    // 删除用户
    @RequestMapping(value = "/admin/userDel/{uid}")
    public String userDel(@PathVariable Integer uid, Integer pageNum) {

        int code = userService.delete(uid);
        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }
        return "redirect:/admin/user?pageNum=" + pageNum;
    }

    // 显示更新用户页面
    @RequestMapping(value = "/admin/userUpdatePage/{uid}")
    public String userUpdate(@PathVariable Integer uid, Model model, Integer pageNum) {

        User user = userService.getUser(uid);

        model.addAttribute("user", user);
        model.addAttribute("pageNum", pageNum);

        return "admin_user_update";
    }

    // 处理用户更新请求
    @RequestMapping(value = "/admin/userUpdate", method = RequestMethod.POST)
    public String userUpdate(User user, Integer pageNum) {

        int code = userService.updateUser(user);

        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }

        return "redirect:/admin/user?pageNum=" + pageNum;
    }


    // 新增用户
    // ...

}
