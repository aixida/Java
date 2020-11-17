package com.zgh.onlinevideo.controller;

import com.zgh.onlinevideo.domain.CourseType;
import com.zgh.onlinevideo.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    CourseTypeService courseTypeService;

    @RequestMapping("/test")
    public String test() {

        CourseType courseType = new CourseType();
        courseType.setFlag(3);
        courseType.setName("MySQL");

        courseTypeService.insertCourseType(courseType);

        return "test";
    }

}
