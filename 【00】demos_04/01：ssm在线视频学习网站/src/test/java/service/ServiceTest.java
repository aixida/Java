package service;

import com.zgh.onlinevideo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


}
