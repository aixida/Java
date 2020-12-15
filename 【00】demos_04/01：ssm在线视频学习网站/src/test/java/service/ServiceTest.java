package service;

import com.github.pagehelper.PageInfo;
import com.zgh.onlinevideo.domain.CourseTopic;
import com.zgh.onlinevideo.service.CourseTopicService;
import com.zgh.onlinevideo.service.UserService;
import com.zgh.onlinevideo.service.impl.MailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void t1() {

        System.out.println(userService.existEmail("847818218@qq.com"));

        System.out.println(userService.existEmail("111"));

    }

    @Autowired
    MailSenderService mailSenderService;

    @Test
    public void t2() throws MessagingException, IOException {

        mailSenderService.send();
        System.out.println("send mail succeed");

    }

    @Autowired
    CourseTopicService courseTopicService;


    @Test
    public void t3() {

        PageInfo<CourseTopic> list = courseTopicService.getIndexCourseTopic(3);

        System.out.println(list.getPageSize());
        System.out.println(list.getSize());

        System.out.println(list.getList().size());
    }


}
