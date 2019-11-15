package top.pdcasystem.pdcasystem.Learn;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//@Scope("singletop")//默认单例
@Service
@Scope("prototype")//每次使用心得实例
public class TestService {
    @PostConstruct
    public  void init(){
    System.out.println("初始化");
    }

    public TestService(){
        System.out.println("实例化");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁化");
    }
}
