package com.zgh.onlinevideo.service;


import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.CourseTopic;

public interface CourseTopicService {

    PageInfo<CourseTopic> getIndexCourseTopic(int typeId);

    // 最新的那些课程主题
    PageInfo<CourseTopic> getIndexNewestTopic();

    CourseTopic getCourseTopic(int topicId);

    // 课程专题浏览数+1
    void viewsAdd(int courseTopicId, int views);
}
