package dao;

import com.zgh.onlinevideo.dao.*;
import com.zgh.onlinevideo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.plugin.dom.core.CoreConstants;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})//基于这些配置文件 测试下列代码
//@ContextConfiguration(locations = {"classpath:spring/spring-web.xml", "classpath:spring/spring-dao.xml"})
public class DaoTest {

    @Autowired
    CourseTypeDao courseTypeDao;

    @Test
    public void t1() {

        CourseType courseType = new CourseType();
        courseType.setFlag(4);
        courseType.setName("算法");

        int code = courseTypeDao.insertCourseType(courseType);
        System.out.println(code);
    }

    @Autowired
    BannerDao bannerDao;

    @Test
    public void t2() {

        Banner banner = new Banner();
        banner.setCreateTime(new Date());
        banner.setFlag(1);
        banner.setBannerImgUrl("url");
        banner.setBannerImgUrl("img url");
        banner.setType(1);

        int code = bannerDao.insertBanner(banner);
        System.out.println(code);
    }

    @Autowired
    CourseTopicDao courseTopicDao;

    @Test
    public void t3() {
        CourseTopic courseTopic = new CourseTopic();
        courseTopic.setFlag(1);
        courseTopic.setCoursewareUrl("url");
        courseTopic.setCreateTime(new Date());

        int code = courseTopicDao.insertCourseTopic(courseTopic);
        System.out.println(code);
    }

    @Autowired
    CourseVideoDao courseVideoDao;

    @Test
    public void t4() {
        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setCreateTime(new Date());
        courseVideo.setName("video");
        int code = courseVideoDao.insertCourseVideo(courseVideo);
        System.out.println(code);
    }

    @Autowired
    ToolItemDao toolItemDao;

    @Test
    public void t5() {
        ToolItem toolItem = new ToolItem();
        toolItem.setName("tool item");
        int code = toolItemDao.insertToolItem(toolItem);
        System.out.println(code);
    }

    @Autowired
    ToolTypeDao toolTypeDao;

    @Test
    public void t6() {
        ToolType toolType = new ToolType();
        toolType.setName("tool type");
        int code = toolTypeDao.insertToolType(toolType);
        System.out.println(code);
    }

    @Autowired
    UserDao userDao;

    @Test
    public void t7() {
        User user = new User();
        user.setCreateTime(new Date());
        int code = userDao.insertUser(user);
        System.out.println(code);
    }

}
