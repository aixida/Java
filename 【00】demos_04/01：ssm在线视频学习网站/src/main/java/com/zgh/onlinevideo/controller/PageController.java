package com.zgh.onlinevideo.controller;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    CourseTopicService courseTopicService;


    // 首页
    @RequestMapping("/")
    public String indexPage(Model model) {

        // 获取首页数据

        // 最新
        // 设置分页 第几页，显示N个
        PageInfo<CourseTopic> newsetTopicList = courseTopicService.getIndexNewestTopic(4);
        // type=3
        // 设置分页 第几页，显示N个

        PageInfo<CourseTopic> courseTopicList = courseTopicService.getIndexCourseTopic(3);

        model.addAttribute("newsetTopicList", newsetTopicList);
        model.addAttribute("courseTopicList", courseTopicList);

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
