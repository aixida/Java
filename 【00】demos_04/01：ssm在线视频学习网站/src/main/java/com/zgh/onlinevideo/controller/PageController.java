package com.zgh.onlinevideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.Banner;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.BannerService;
import com.zgh.onlinevideo.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    CourseTopicService courseTopicService;

    @Autowired
    BannerService bannerService;

    // 首页
    @RequestMapping("/")
    public String indexPage(Model model) {

        // 获取首页数据

        // banner
        List<Banner> bannerList = bannerService.getIndexBanner();

        // 最新
        // 设置分页 第几页，显示N个
        PageInfo<CourseTopic> newsetTopicList = courseTopicService.getIndexNewestTopic(4);

        // type=3
        // 设置分页 第几页，显示N个
        PageHelper.startPage(1, 4);
        PageInfo<CourseTopic> courseTopicList = courseTopicService.getIndexCourseTopic(3);

        model.addAttribute("newsetTopicList", newsetTopicList);
        model.addAttribute("courseTopicList", courseTopicList);
        model.addAttribute("bannerList", bannerList);

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
    public String courseListPage(Model model) {
        PageHelper.startPage(1, 6);
        PageInfo<CourseTopic> newsetTopicList = courseTopicService.getIndexNewestTopic(6);
        model.addAttribute("topicList", newsetTopicList);
        return "course_list";
    }

    // 课程列表 显示某一分类
    @RequestMapping("/course_list/type/{courseTypeId}")
    public String courseListPage(@PathVariable Integer courseTypeId, Model model) {

        PageHelper.startPage(1, 6);
        PageInfo<CourseTopic> courseTopicList = courseTopicService.getIndexCourseTopic(courseTypeId);

        model.addAttribute("courseTopicList", courseTopicList);

        return "course_list";
    }

    @RequestMapping("/course_video")
    public String courseVideoPage() {
        return "course_video";
    }

}
