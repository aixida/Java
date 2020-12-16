package com.zgh.onlinevideo.service;

import com.zgh.onlinevideo.domain.CourseType;

import java.util.List;

public interface CourseTypeService {

    int insertCourseType(CourseType courseType);

    List<CourseType> getCourseTypeAll();

}
