package top.pdcasystem.pdcasystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import top.pdcasystem.pdcasystem.Dao.PlanLogMapper;
import top.pdcasystem.pdcasystem.Dao.PublishMapper;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Entity.Publish;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Date;

@Service
public class TestAlphaService {
    @Autowired
    PlanLogMapper planLogMapper;



    @PostConstruct
    public void init(){
        // 刚开始初始化
    }

    @PreDestroy
    public void destory(){
        //销毁之前
    }

    // 注解型事务，一句报错直接回滚，带上数据库
    // REQUIRED: 支持当前事务（外部事务），如果不存在就创建新事物
    // REQUIRES_NEW：创建一个新的事务，并且暂停外部事务
    // NESTED：如果当前存在外部事务，则嵌套在事务中执行（独立的提交和回滚），否则就会像第一个这样
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1(){
        // 新增计划
        PlanLog planLog = new PlanLog();
        planLog.setObject("测试");
        planLog.setLevel(1);
        planLog.setContent("hello");
        planLog.setWeight(3);
        Date date=new Date(new java.util.Date().getTime());
        planLog.setInitdate(date);
        planLog.setUpdatedate(date);
        planLog.setComment("");
        // 新增发布内容
        planLogMapper.insertPlanLog(planLog);
        /**
        Publish publish = new Publish();
        publish.setSourceid(planLog.getId());
        publish.setSource("plan");
        publish.setSourceObject("hello");
        publish.setTitle("事务测试");
        publish.setLocate("test");
        publishMapper.insertPublish(publish);
         **/
        Integer.valueOf("asdfasdf");// 转为试错时是否能回滚所做
        return "ok";
    }

    @Autowired
    private TransactionTemplate transactionTemplate;
    //
    public Object save2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                // 新增计划
                PlanLog planLog = new PlanLog();
                planLog.setObject("测试程序式事务");
                planLog.setLevel(1);
                planLog.setContent("hellotransaction");
                planLog.setWeight(2);
                Date date=new Date(new java.util.Date().getTime());
                planLog.setInitdate(date);
                planLog.setUpdatedate(date);
                planLog.setComment("transaction内容");
                // 新增发布内容
                planLogMapper.insertPlanLog(planLog);
                /**
                 Publish publish = new Publish();
                 publish.setSourceid(planLog.getId());
                 publish.setSource("plan");
                 publish.setSourceObject("hello");
                 publish.setTitle("事务测试");
                 publish.setLocate("test");
                 publishMapper.insertPublish(publish);
                 **/
                //Integer.valueOf("asdfasdf");// 转为试错时是否能回滚所做
                return "ok";

            }
        });
    }
}
