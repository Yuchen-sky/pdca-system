package top.pdcasystem.pdcasystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.pdcasystem.pdcasystem.Entity.NeedSolve;
import top.pdcasystem.pdcasystem.Service.NeedSolveService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class testneedsolve {
    @Autowired
    NeedSolveService needSolveService;

    @Test
    public void  test(){
        NeedSolve needSolve = new NeedSolve();
        needSolve.setObject("ceshi");
        needSolve.setContent("mengxiang");
        needSolveService.insertNeedSolve(needSolve);

    }
}
