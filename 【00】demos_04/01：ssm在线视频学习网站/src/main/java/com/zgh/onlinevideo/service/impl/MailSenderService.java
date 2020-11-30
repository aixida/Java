package com.zgh.onlinevideo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailSenderService {

    // 密码找回
    // 指定用户email 根据email找回
    // 生成token的参数
    // email + 时间(时间戳) + 服务器秘钥（123！@#随机生成） md5 = token
    // url?token=xxx&email=xx&t=xxx

    @Autowired
    private JavaMailSender javaMailSender;

    public void send() throws MessagingException, IOException {

        // 发件人
        String from = "847818218@qq.com";
        // 收件人
        String to = "847818218@qq.com";
        // 邮件标题
        String subject = "使用Spring发送邮件";
        // 邮件内容
        String text = "<h1>http://localhost:8080/</h1>";

        //1.跟SpringBeanFactory要一个对象 或者 自动注入

        //2.利用这个sender创建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        //3.发送这个邮件  找小弟帮忙
        MimeMessageHelper helper = new MimeMessageHelper(message);
        //4.需要告知helper 发给谁 发什么
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);// 发送html
        //5.直接发送消息
        javaMailSender.send(message);

    }

}
