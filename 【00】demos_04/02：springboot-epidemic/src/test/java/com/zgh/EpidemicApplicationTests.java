package com.zgh;

import com.zgh.mail.MailComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpidemicApplicationTests {

    @Autowired
    MailComponent mail;

    @Test
    void contextLoads() {

        mail.send();

    }

}
