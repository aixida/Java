package com.zgh.onlinevideo.service.impl;

import com.zgh.onlinevideo.dao.CourseTypeDao;
import com.zgh.onlinevideo.domain.CourseType;
import com.zgh.onlinevideo.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    CourseTypeDao courseTypeDao;

    public int insertCourseType(CourseType courseType) {
        return courseTypeDao.insertCourseType(courseType);
    }

}
