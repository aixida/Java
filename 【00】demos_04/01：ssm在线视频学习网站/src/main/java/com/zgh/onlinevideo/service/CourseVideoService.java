package com.zgh.onlinevideo.service;

import com.zgh.onlinevideo.domain.CourseVideo;

import java.util.List;

public interface CourseVideoService {

    List<CourseVideo> getCourseVideoAll(int topicId);

    CourseVideo getCourseVideo(int vid);

}
