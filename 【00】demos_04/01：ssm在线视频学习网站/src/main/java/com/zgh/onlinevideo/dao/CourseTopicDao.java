package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.CourseTopic;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CourseTopicDao {

    int insertCourseTopic(CourseTopic courseTopic);

    List<CourseTopic> findCourseTopicByCondition(HashMap<String, Object> map);

}
