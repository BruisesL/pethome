package eth.bruises.basic.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件的工具类
 *
 * @author bruises
 */
public class SendEmailUtil {

    /**
     * 发送邮件
     *
     * @param email 邮箱号
     * @param subject 邮件标题
     * @param emailMsg 邮件内容
     */
    public static void sendMail(String email, String subject, String emailMsg) {
        // 创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");

        //如果是其他邮箱就把qq改为相应的邮箱后缀
        props.setProperty("mail.host", "smtp.qq.com");

        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");

        // 创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //发邮件的账号和密码，此处需要到QQ邮箱官网开启SMTP,P0P3,IMAP服务，然后获取授权码作为第三方登录密码
                return new PasswordAuthentication("bruisesluo@qq.com", "akuxxapufbcnfhag");
            }
        };
        //不同与request的session
        Session session = Session.getInstance(props, auth);
        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        try {
            // 设置发送者
            message.setFrom(new InternetAddress("bruisesluo@qq.com"));
            // 设置发送方式与接收者
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setContent(emailMsg, "text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}