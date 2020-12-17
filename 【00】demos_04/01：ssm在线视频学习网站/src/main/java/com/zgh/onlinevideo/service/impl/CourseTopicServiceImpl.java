package com.zgh.onlinevideo.service.impl;


import com.github.pagehelper.PageException;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.dao.CourseTopicDao;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class CourseTopicServiceImpl implements CourseTopicService {

    @Autowired
    CourseTopicDao courseTopicDao;

    @Override
    public PageInfo<CourseTopic> getIndexCourseTopic(int typeId) {

        HashMap<String, Object> map = new HashMap<>();
        // 类型ID
        map.put("courseTypeId", typeId);
        // flag=1
        map.put("flag", 1);

        List<CourseTopic> list = courseTopicDao.findCourseTopicByCondition(map);

        PageInfo<CourseTopic> pageInfo = new PageInfo<>(list, 4);

        return pageInfo;
    }

    @Override
    public PageInfo<CourseTopic> getIndexNewestTopic() {
        // TODO 1

        HashMap<String, Object> map = new HashMap<>();
        // 排序 ==1 倒序 desc
        map.put("order", 1);
        // flag=1
        map.put("flag", 1);
        List<CourseTopic> list = courseTopicDao.findCourseTopicByCondition(map);

        // TODO 2
        // TODO 3 MYBATIS 配置文件拦截器(PageInfo)
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(list, 4);

        return pageInfo;

    }

    @Override
    public CourseTopic getCourseTopic(int topicId) {
        List<Integer> ids = new ArrayList<>();
        ids.add(topicId);
        List<CourseTopic> list = courseTopicDao.findCourseTopicByIds(ids);
        return list.get(0);
    }

    @Override
    public void viewsAdd(int courseTopicId, int views) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", courseTopicId);
        map.put("views", views);
        int flag = courseTopicDao.updateCourseTopic(map);
        if (flag != 1) {
            throw new PageException("观看次数增加行为发生错误");
        }
    }

    @Override
    public PageInfo<CourseTopic> searchTopicByTitle(String title) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("title", title);
        map.put("flag", 1);

        List<CourseTopic> list = courseTopicDao.findCourseTopicByCondition(map);
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(list, 4);

        return pageInfo;

    }

}
