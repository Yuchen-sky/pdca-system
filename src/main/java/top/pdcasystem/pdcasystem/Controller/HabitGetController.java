package top.pdcasystem.pdcasystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.pdcasystem.pdcasystem.Entity.DateUtil;
import top.pdcasystem.pdcasystem.Entity.Habit;
import top.pdcasystem.pdcasystem.Entity.HabitLog;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Service.HabitService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HabitGetController {

    @Autowired
    HabitService habitService;

    @RequestMapping(path={"/gethabit"},method = RequestMethod.GET)
    public  String getHabit(Model model){
        List<Habit> habits=habitService.selectByStatus(0,0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(habits!=null){
            for(Habit a:habits){
                Map<String,Object> map=new HashMap<>();
                map.put("habit",a);
                post.add(map);
            }
        }
        model.addAttribute("Post",post);
        return "habit";
    }

    @RequestMapping(path={"/gethabitlog"},method = RequestMethod.GET)
    public  String getHabitLog(Model model){
        List<HabitLog> habitLogs=habitService.selectOrderByGenerateTime(0,50);
        List<Map<String, Object>> post=new ArrayList<>();
        if(habitLogs != null){
            for(HabitLog a: habitLogs){
                Map<String, Object> map=new HashMap<>();
                Timestamp gt = new Timestamp((long)a.getGeneratetime()*1000);
                Timestamp ut = new Timestamp((long)a.getUpdatetime()*1000);
                Date gd = new Date(gt.getTime());
                Date ud = new Date(ut.getTime());
                map.put("habitlog", a);
                map.put("gendate", gd);
                map.put("update", ud);
                post.add(map);
            }
        }
        model.addAttribute("Post",post);
        return "habitlog";
    }

    @RequestMapping(path="/getnotfinishhabitlog",method = RequestMethod.GET)
    public  String getNotFinishHabitLog(Model model){
        List<HabitLog> habitLogs=habitService.selectByNotFinish(0,0,50);
        List<Map<String, Object>> post=new ArrayList<>();
        if(habitLogs != null){
            for(HabitLog a: habitLogs){
                Map<String, Object> map=new HashMap<>();
                Timestamp gt = new Timestamp((long)a.getGeneratetime()*1000);
                Timestamp ut = new Timestamp((long)a.getUpdatetime()*1000);
                Date gd = new Date(gt.getTime());
                Date ud = new Date(ut.getTime());
                map.put("habitlog", a);
                map.put("gendate", gd);
                map.put("update", ud);
                post.add(map);
            }
        }
        model.addAttribute("Post",post);
        return "habitlog";
    }

}
