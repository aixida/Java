package com.zgh.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 1.原生 javax.mail 发送邮件
 */
public class MailUtil {

    public static String myEmailAccount = "847818218@qq.com";
    public static String myEmailPassword = "tbolyqdizctwbaij"; //QQ邮箱授权码

    public static String myEmailSMTPHost = "smtp.qq.com";

    public static void main(String[] args) throws Exception {

        // 1.创建参数
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.host", myEmailSMTPHost);
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        // 2.创建会话
        Session session = Session.getInstance(prop);
        session.setDebug(true); //设置debug模式 可以看到日志

        // 3.创建邮件
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(myEmailAccount, "Studying", "UTF-8")); //发件人
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(myEmailAccount, "zgh", "UTF-8")); //收件人 抄送CC, 密送BCC

        message.setSubject("a email comes from zgh", "UTF-8");
        message.setContent("hello", "text/html;charset=UTF-8");

        message.setSentDate(new Date());
        message.saveChanges(); //以 eml 的格式保存

        // 4.传输
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();

    }

}
