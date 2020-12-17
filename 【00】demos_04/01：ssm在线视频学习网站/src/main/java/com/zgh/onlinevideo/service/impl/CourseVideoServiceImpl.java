package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.CourseVideoDao;
import com.zgh.onlinevideo.domain.CourseVideo;
import com.zgh.onlinevideo.service.CourseVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseVideoServiceImpl implements CourseVideoService {

    @Autowired
    CourseVideoDao courseVideoDao;

    @Override
    public List<CourseVideo> getCourseVideoAll(int courseTopicId) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("courseTopicId", courseTopicId);
        map.put("flag", 1);

        return courseVideoDao.findCourseVideoByCondition(map);
    }

    @Override
    public CourseVideo getCourseVideo(int vid) {

        List<Integer> ids = new ArrayList();
        ids.add(vid);
        return courseVideoDao.findCourseVideoByIds(ids);
    }
}
