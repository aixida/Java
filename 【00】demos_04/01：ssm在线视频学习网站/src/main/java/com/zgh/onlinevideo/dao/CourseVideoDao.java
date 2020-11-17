package com.zgh.onlinevideo.dao;

import com.zgh.onlinevideo.domain.CourseVideo;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseVideoDao {

    int insertCourseVideo(CourseVideo courseVideo);

}
