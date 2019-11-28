package top.pdcasystem.pdcasystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Service.PlanPostService;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

import java.sql.Date;
import java.util.*;

@Controller
public class PlanPostController {
    @Autowired
    private PlanPostService planPostService;

    @RequestMapping(path="/getsetplan",method = RequestMethod.GET)
    public  String getsetPlan(){
        return "planlogWrite";
    }

    @LoginRequired
    @RequestMapping(path="/setplan",method = RequestMethod.POST)
    public String getplan(
            Model model, int thinglevel ,String object,String content,String comment,int weight
    ){
        Date date=new Date(new java.util.Date().getTime());
        PlanLog planLog=new PlanLog();
        planLog.setLevel(thinglevel);
        planLog.setObject(object);
        planLog.setContent(content);
        planLog.setComment(comment);
        planLog.setWeight(weight);
        planLog.setInitdate(date);
        planLog.setUpdatedate(date);
        planPostService.insertPlanLog(planLog);

        return "planlogResponse";
    }

    @LoginRequired
    @RequestMapping(path="/setfinish",method = RequestMethod.POST)
    public String setfinish(
            Model model, int planid
    ){
        planPostService.updateFinish(planid,1);

        return "planlogResponse";
    }

    @LoginRequired
    @RequestMapping(path="/setfinishstate",method = RequestMethod.POST)
    public String setfinish(
            Model model, int planid,int finish,String comment
    ){
        planPostService.updateFinish(planid,finish);
        if(comment.length()>0){
            PlanLog  planLog=planPostService.selectById(planid);
            String result=planLog.getComment()+"+"+comment;
            planPostService.updateComment(planid,result);
        }

        return "planlogResponse";
    }

    @LoginRequired
    @RequestMapping(path="/setdelay",method = RequestMethod.POST)
    public String setdelay(
            Model model, int planid
    ){
        planPostService.updateDelay(planid);
        PlanLog  planLog=planPostService.selectById(planid);
        Date date=planLog.getUpdatedate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        cal.add(Calendar.DATE, 1);  //在当前时间基础上加一年
        Date newdate=new Date(cal.getTime().getTime());
        planPostService.updateUpdateDate(planid,newdate);

        return "planlogResponse";
    }

}