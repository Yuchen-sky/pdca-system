package top.pdcasystem.pdcasystem.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Service.PlanPostService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PlanGetController {
    @Autowired
    private PlanPostService planPostService;


    @RequestMapping(path={"/getplan","/"},method = RequestMethod.GET)
    public  String getPlan(Model model){
        Date date=new Date(new java.util.Date().getTime());
        List<PlanLog> planLogs=planPostService.selectByUpdateDate(date,0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(PlanLog a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "planlog";
    }

    @RequestMapping(path="/getnotfinishplan",method = RequestMethod.GET)
    public  String getNotFinishPlan(Model model){

        List<PlanLog> planLogs=planPostService.selectByNotFinish(0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(PlanLog a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "planlog";
    }

    @RequestMapping(path="/gethistorybygenerate",method = RequestMethod.GET)
    public  String getHistoryPlan(Model model){

        List<PlanLog> planLogs=planPostService.selectOrderByGenerateTime(0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(PlanLog a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "planlog";
    }

    @RequestMapping(path="/gethistorybyupdate",method = RequestMethod.GET)
    public  String getHistoryPlan2(Model model){

        List<PlanLog> planLogs=planPostService.selectOrderByUpdateTime(0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(PlanLog a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "planlog";
    }

    @RequestMapping(path="/gettodaynotfinishplan",method = RequestMethod.GET)
    public  String getTodayNotFinishPlan(Model model){
        Date date=new Date(new java.util.Date().getTime());
        List<PlanLog> planLogs=planPostService.selectByUpdateDateNotFinish(date,0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(PlanLog a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "planlogTodayNotFinish";
    }


}
