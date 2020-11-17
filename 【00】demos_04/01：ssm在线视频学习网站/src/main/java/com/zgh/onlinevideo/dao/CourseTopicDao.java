package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.CourseTopic;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTopicDao {

    int insertCourseTopic(CourseTopic courseTopic);

}
