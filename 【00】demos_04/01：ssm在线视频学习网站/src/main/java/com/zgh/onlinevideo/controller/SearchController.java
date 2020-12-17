package com.zgh.onlinevideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @Autowired
    CourseTopicService courseTopicService;

    @RequestMapping("/search")
    public String searchPage(Model model, String searchkey, Integer pageNum) {
        model.addAttribute("focusIndex", 2); //头页面聚焦

        if (pageNum == null || pageNum <= 1) {
            pageNum = 1;
        }

        // 课程主题(全部) 排列显示
        PageHelper.startPage(pageNum, 4);
        PageInfo<CourseTopic> searchTopicList = courseTopicService.searchTopicByTitle(searchkey);
        model.addAttribute("searchTopicList", searchTopicList);

        return "search";
    }

}
