package top.pdcasystem.pdcasystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.pdcasystem.pdcasystem.Service.TestAlphaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class TransactionTest {
    @Autowired
    private TestAlphaService testService;

    @Test
    public void  testTransaction(){
        //testService.save1();

    }
    @Test
    public void  testTransaction2(){
        //testService.save2();

    }
}
