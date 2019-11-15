package top.pdcasystem.pdcasystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.pdcasystem.pdcasystem.Learn.Alpha;
import top.pdcasystem.pdcasystem.Learn.TestService;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class PdcaSystemApplicationTests implements ApplicationContextAware {

	//主动获取测试，更方便就是ioc

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public  void testApplicationContext(){
		System.out.println(applicationContext);
		System.out.println("this is a test");
		Alpha alphait =applicationContext.getBean("alphait",Alpha.class);
		System.out.println(alphait.select());
	}

	@Test
	public  void testBeanManagerment(){
		TestService testservice=applicationContext.getBean(TestService.class);
		System.out.println(testservice);
		TestService testservice2=applicationContext.getBean(TestService.class);
		System.out.println(testservice2);
	}
	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("alphait")
	private Alpha alpha;

	@Test
	public void testDirect(){
		System.out.println(alpha.select());
	}
}
