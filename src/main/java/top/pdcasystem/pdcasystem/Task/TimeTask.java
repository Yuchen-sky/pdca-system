package top.pdcasystem.pdcasystem.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.pdcasystem.pdcasystem.Service.HabitService;

import java.util.Date;

@Component
public class TimeTask {
    public static final Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Autowired
    HabitService habitService;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 0 0 * * *")
    public void autoGennaratePlan(){
        logger.debug("执行定时任务" + new Date().toString());
        long time = System.currentTimeMillis();
        int timenow = (int) time / 1000;
        timenow = (int) timenow / ( 60 * 60 * 24);
        habitService.generateHabitLog(timenow);
    }


}
