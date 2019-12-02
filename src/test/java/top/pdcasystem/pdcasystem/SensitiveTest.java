package top.pdcasystem.pdcasystem;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.pdcasystem.pdcasystem.Util.ChooseWordUtil;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class SensitiveTest {
    @Autowired
    ChooseWordUtil chooseWordUtil;

    @Test
    public void  testSensitive(){

        String test = "我不喜欢你，但我输出印象笔记/我今天试了，，，了这个。发布了那个，逃避不是真相。你曾经非常不错";
        Map<String, String> map = chooseWordUtil.proceeing(test);
        System.out.println(map.get("content"));
        System.out.println(map.get("locate"));
        System.out.println(map.get("title"));
    }
}
