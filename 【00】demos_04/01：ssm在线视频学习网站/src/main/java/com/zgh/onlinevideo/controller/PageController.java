package com.zgh.onlinevideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.*;
import com.zgh.onlinevideo.service.*;
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

    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    ToolItemService toolItemService;

    @Autowired
    ToolTypeService toolTypeService;

    // 首页
    @RequestMapping("/")
    public String indexPage(Model model) {

        // 设置头页面的导航条当前焦点位置
        model.addAttribute("focusIndex", 1);

        // 获取首页数据

        // banner
        List<Banner> bannerList = bannerService.getIndexBanner();

        // 最新
        // 设置分页 第几页，显示N个
        PageHelper.startPage(1, 4);
        PageInfo<CourseTopic> newsetTopicList = courseTopicService.getIndexNewestTopic();

        // type=3
        // 设置分页 第几页，显示N个
        PageHelper.startPage(1, 4);
        PageInfo<CourseTopic> courseTopicList = courseTopicService.getIndexCourseTopic(3);

        model.addAttribute("newsetTopicList", newsetTopicList);
        model.addAttribute("courseTopicList", courseTopicList);
        model.addAttribute("bannerList", bannerList);

        return "index";
    }

    // 课程列表
    @RequestMapping("/course_list")
    public String courseListPage(Model model) {
        model.addAttribute("focusIndex", 2); //头页面聚焦
        model.addAttribute("courseTypeId", 0);//全部课程 无论类型

        // 课程主题(全部) 排列显示
        PageHelper.startPage(1, 4);
        PageInfo<CourseTopic> newsetTopicList = courseTopicService.getIndexNewestTopic();
        model.addAttribute("topicList", newsetTopicList);

        // 课程分类 导航栏
        List<CourseType> courseTypeList = courseTypeService.getCourseTypeAll();
        model.addAttribute("courseTypeList", courseTypeList);

        return "course_list";
    }

    // 课程列表 显示某一分类
    @RequestMapping("/course_list/type/{courseTypeId}")
    public String courseListPage(@PathVariable Integer courseTypeId, Integer pageNum, Model model) {

        model.addAttribute("focusIndex", 2);
        model.addAttribute("courseTypeId", courseTypeId);//课程某一分类

        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }

        // 课程分类 导航栏
        List<CourseType> courseTypeList = courseTypeService.getCourseTypeAll();
        model.addAttribute("courseTypeList", courseTypeList);

        // 课程主题(按照分类) 排列显示
        PageHelper.startPage(pageNum, 4);
        PageInfo<CourseTopic> courseTopicList = null;
        if (courseTypeId == 0) {
            // 最新专题
            courseTopicList = courseTopicService.getIndexNewestTopic();
        } else {
            // 对应类型专题
            courseTopicList = courseTopicService.getIndexCourseTopic(courseTypeId);
        }

        model.addAttribute("topicList", courseTopicList);

        return "course_list";
    }

    // vip
    @RequestMapping("/vip")
    public String vipPage(Model model) {
        model.addAttribute("focusIndex", 3);
        return "vip";
    }

    // 直播
    @RequestMapping("/live")
    public String livePage(Model model) {
        model.addAttribute("focusIndex", 4);
        return "redirect:https://www.bilibili.com/";
    }

    // 工具
    @RequestMapping("/tool")
    public String toolPage(Model model) {
        model.addAttribute("focusIndex", 5);
        model.addAttribute("toolTypeId", 0);
        // 最新
        // 设置分页 第几页，显示N个
        PageHelper.startPage(1, 8);
        PageInfo<ToolItem> toolList = toolItemService.getToolItemAll();
        model.addAttribute("toolList", toolList);

        List<ToolType> toolTypeList = toolTypeService.getToolTypeAll();
        model.addAttribute("toolTypeList", toolTypeList);

        return "tool";
    }

    //工具 显示所有分类
    @RequestMapping(value = "/tool/type/{toolTypeId}")
    public String toolList(@PathVariable Integer toolTypeId, Model model, Integer pageNum) {
        model.addAttribute("focusIndex", 5);
        model.addAttribute("toolTypeId", toolTypeId);

        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }

        List<ToolType> toolTypeList = toolTypeService.getToolTypeAll();
        model.addAttribute("toolTypeList", toolTypeList);

        PageHelper.startPage(pageNum, 8);

        PageInfo<ToolItem> toolList = null;
        if (toolTypeId == 0) {
            // 最新
            toolList = toolItemService.getToolItemAll();
        } else {
            // 对应类型
            toolList = toolItemService.getToolItem(toolTypeId);
        }

        model.addAttribute("toolList", toolList);

        return "tool";
    }

    @RequestMapping("/course_video")
    public String courseVideoPage() {
        return "course_video";
    }

}
