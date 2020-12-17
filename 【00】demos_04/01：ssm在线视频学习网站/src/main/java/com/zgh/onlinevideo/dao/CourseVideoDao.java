package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.CourseVideo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CourseVideoDao {

    int insertCourseVideo(CourseVideo courseVideo);

    List<CourseVideo> findCourseVideoByCondition(HashMap<String, Object> map);

    CourseVideo findCourseVideoByIds(List<Integer> ids);

}
