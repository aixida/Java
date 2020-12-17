package com.zgh.onlinevideo.controller;

import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.domain.CourseVideo;
import com.zgh.onlinevideo.domain.User;
import com.zgh.onlinevideo.service.CourseTopicService;
import com.zgh.onlinevideo.service.CourseVideoService;
import com.zgh.onlinevideo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    CourseTopicService courseTopicService;

    @Autowired
    CourseVideoService courseVideoService;

    // 课程专题页面
    @RequestMapping("/topic/{courseTopicId}")
    public String topicPage(@PathVariable Integer courseTopicId, Model model) {
        model.addAttribute("focusIndex", 2);
        model.addAttribute("courseTopicId", courseTopicId);

        CourseTopic courseTopic = courseTopicService.getCourseTopic(courseTopicId);

        // 浏览数+1
        courseTopicService.viewsAdd(courseTopicId, courseTopic.getViews()+1);

        courseTopic = courseTopicService.getCourseTopic(courseTopicId);
        model.addAttribute("courseTopic", courseTopic);

        List<CourseVideo> courseVideoList = courseVideoService.getCourseVideoAll(courseTopicId);
        model.addAttribute("courseVideoList", courseVideoList);

        model.addAttribute("video", courseVideoList.get(0));



        return "course_video";
    }

    // 课程专题页面 播放某一集视频
    @RequestMapping(value = "/topic/{courseTopicId}/{videoId}")
    public String topicPage(@PathVariable Integer courseTopicId, @PathVariable Integer videoId, Model model, HttpSession session) {
        model.addAttribute("focusIndex", 2);
        model.addAttribute("courseTopicId", courseTopicId);

        CourseTopic courseTopic = courseTopicService.getCourseTopic(courseTopicId);

        // 检查当前登录用户 vip
        User dbUser = (User) session.getAttribute(Constants.LOGIN_USER);
        if (dbUser != null) {
            int userVipFlag = dbUser.getVipFlag();
            System.out.println(dbUser);
            model.addAttribute("userVipFlag", userVipFlag);
        }

        // 浏览数+1
        courseTopicService.viewsAdd(courseTopicId, courseTopic.getViews()+1);

        courseTopic = courseTopicService.getCourseTopic(courseTopicId);
        model.addAttribute("courseTopic", courseTopic);

        List<CourseVideo> courseVideoList = courseVideoService.getCourseVideoAll(courseTopicId);
        model.addAttribute("courseVideoList", courseVideoList);

        CourseVideo video = courseVideoService.getCourseVideo(videoId);
        model.addAttribute("video", video);

        return "course_video";
    }

}
