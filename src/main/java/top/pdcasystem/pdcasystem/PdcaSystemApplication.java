package top.pdcasystem.pdcasystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdcaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdcaSystemApplication.class, args);
		//IOC依赖反转
		//创建spring容器
		//扫描bean，主项目下的包并由注解,配上配置内容
		//默认实例化单个
	}

}
