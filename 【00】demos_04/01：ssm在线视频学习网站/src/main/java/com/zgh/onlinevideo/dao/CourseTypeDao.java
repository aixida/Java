package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.CourseType;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeDao {

    int insertCourseType(CourseType courseType);

}
