package com.zgh.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Autowired
    private JavaMailSender sender;

    public void send() {

        System.out.println("spring-boot 发送邮件");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("a email come from spring-boot");
        message.setText("talk nonsense");

        message.setTo("847818218@qq.com");
        message.setFrom("847818218@qq.com");

        sender.send(message);

    }

}
