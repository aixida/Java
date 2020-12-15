package com.zgh.onlinevideo.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.dao.CourseTopicDao;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.CourseTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class CourseTopicServiceImpl implements CourseTopicService {

    @Autowired
    CourseTopicDao courseTopicDao;

    @Override
    public PageInfo<CourseTopic> getIndexCourseTopic(int typeId) {

        PageHelper.startPage(1, 4);
        HashMap<String, Object> map = new HashMap<>();
        // 类型ID
        map.put("courseTypeId", typeId);
        // flag=1
        map.put("flag", 1);

        List<CourseTopic> list = courseTopicDao.findCourseTopicByCondition(map);

        PageInfo<CourseTopic> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public PageInfo<CourseTopic> getIndexNewestTopic(int limit) {
        // TODO 1
        PageHelper.startPage(1, limit);

        HashMap<String, Object> map = new HashMap<>();
        // 排序 ==1 倒序 desc
        map.put("order", 1);
        // flag=1
        map.put("flag", 1);
        List<CourseTopic> list = courseTopicDao.findCourseTopicByCondition(map);

        // TODO 2
        // TODO 3 MYBATIS 配置文件拦截器
        PageInfo<CourseTopic> pageInfo = new PageInfo<>(list);

        return pageInfo;

    }
}
