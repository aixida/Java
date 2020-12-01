package com.zgh.onlinevideo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // 首页
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    // 工具
    @RequestMapping("/tools")
    public String toolsPage() {
        return "tools";
    }

    // vip
    @RequestMapping("/vip")
    public String vipPage() {
        return "vip";
    }

    // 课程列表
    @RequestMapping("/course_list")
    public String courseListPage() {
        return "course_list";
    }

    @RequestMapping("/course_video")
    public String courseVideoPage() {
        return "course_video";
    }

}
