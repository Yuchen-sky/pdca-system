package top.pdcasystem.pdcasystem;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.pdcasystem.pdcasystem.Util.MailClient;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class Mailtest {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testMail(){
        mailClient.sendMail("luzhenandj1994@163.com", "你还不知道我是谁么", "这是一个测试邮件!，请勿拒收 ");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("158378340@qq.com", "你还不知道我是谁么ahah", content);
    }
}
