package top.pdcasystem.pdcasystem.Learn;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class TestConfig {
@Bean//方法返回的对象想被装盘配到容器中
public SimpleDateFormat simpleDateFormat(){
    return  new SimpleDateFormat("HH:mm:ss");
}



}
