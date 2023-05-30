package eth.bruises.basic.abandoned;


import org.springframework.mail.javamail.JavaMailSender;

/**
 * 发送邮件工具
 *
 * @author bruises
 */
public class SendEmailUtil1 {
    static {

    }

    public static void sendEmail(Long shopId) {
//        JavaMailSender javaMailSender = SpringBeanUtil.getBean(JavaMailSender.class);
        JavaMailSender javaMailSender = SpringBeanUtil.getContext().getBean(JavaMailSender.class);
        System.out.println(javaMailSender);
    }

    public static void main(String[] args) {
        sendEmail(1L);
    }
}
