package top.pdcasystem.pdcasystem.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.pdcasystem.pdcasystem.Entity.Habit;
import top.pdcasystem.pdcasystem.Entity.HabitLog;
import top.pdcasystem.pdcasystem.Service.HabitService;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

import java.sql.Date;

@Controller
public class HabitPostController {
    @Autowired
    private HabitService habitService;

    @RequestMapping(path="/getsethabit",method = RequestMethod.GET)
    public  String getsetHabit(){
        return "habitWrite";
    }

    @LoginRequired
    @RequestMapping(path="/sethabit",method = RequestMethod.POST)
    public String sethabit(
            Model model, String typed, String object, String content, String comment, String timeset, int weight, int status
    ){
        Habit oldhabit = habitService.selectByObject(object);
        if (oldhabit == null) {
            Habit habit=new Habit();
            habit.setTyped(typed);
            habit.setObject(object);
            habit.setContent(content);
            habit.setComment(comment);
            habit.setWeight(weight);
            if (StringUtils.isNumeric(timeset)) {
                habit.setTimeset(timeset);
            } else {
                habit.setTimeset("1");
            }
            habit.setStatus(status);
            habitService.insertHabit(habit);
            return "habitResponse";
        }
        if (!StringUtils.isBlank(typed)) {
            oldhabit.setTyped(typed);
        }
        if (!StringUtils.isBlank(content)) {
            oldhabit.setContent(content);
        }
        if (!StringUtils.isBlank(comment)) {
            String newComment = oldhabit.getComment() + " + " + comment;
            oldhabit.setComment(newComment);
        }
        if (!StringUtils.isBlank(timeset)) {
            if (StringUtils.isNumeric(timeset)) {
                oldhabit.setTimeset(timeset);
            }
        }
        if (weight != 0 ){
            oldhabit.setWeight(weight);
        }
        oldhabit.setStatus(status);
        oldhabit.setNewtotal(0);
        oldhabit.setNewfinish(0);
        habitService.updateHabit(oldhabit);
        return "habitResponse";
    }

    @LoginRequired
    @RequestMapping(path="/sethabitfinish",method = RequestMethod.POST)
    public String setHabitFinish(
            Model model, String id
    ){
        if (StringUtils.isNumeric(id)) {
            habitService.updateFinish(Integer.parseInt(id),1);
            HabitLog habitLog = habitService.selecthabitlogById(Integer.parseInt(id));
            habitService.updateHabitFinishNum(habitLog.getHabitid(), 1, 1);
            return "habitResponse";
        }
        HabitLog habitLog = habitService.selectLogByObject(id);
        if (habitLog != null) {
            habitService.updateFinish(habitLog.getId(),1);
            habitService.updateHabitFinishNum(habitLog.getHabitid(), 1, 1);
            return "habitResponse";
        }
        return "problemResponse";
    }

    @LoginRequired
    @RequestMapping(path="/sethabitfinishstate",method = RequestMethod.POST)
    public String setfinish(
            Model model, String id,int finish,String comment
    ){
        int realid = 0;
        HabitLog habitLog;
        if (StringUtils.isNumeric(id)) {
            realid = Integer.parseInt(id);
            habitService.updateFinish(realid, finish);
            habitLog = habitService.selecthabitlogById(Integer.parseInt(id));
            if(comment.length()>0){
                String result = habitLog.getComment()+"+"+comment;
                habitService.updateComment(realid, result);
            }
            if(finish == 1 || finish == 2) {
                habitService.updateHabitFinishNum(habitLog.getHabitid(), 1, 1);
            }
        }else {
            habitLog = habitService.selectLogByObject(id);
            if (habitLog != null) {
                realid = habitLog.getId();
                habitService.updateFinish(realid, finish);
                if(comment.length()>0){
                    String result = habitLog.getComment()+"+"+comment;
                    habitService.updateComment(realid, result);
                }
                if(finish == 1 || finish == 2) {
                    habitService.updateHabitFinishNum(habitLog.getHabitid(), 1, 1);
                }
            } else {
                return "problemResponse";
            }
        }
        return "habitResponse";
    }



    @LoginRequired
    @RequestMapping(path="/sethabitlogfinishbyid/{habitlogid}",method = RequestMethod.GET)
    public String setHabitFinish(
            Model model,@PathVariable("habitlogid")  int id
    ){
            habitService.updateFinish(id,1);
            HabitLog habitLog = habitService.selecthabitlogById(id);
            habitService.updateHabitFinishNum(habitLog.getHabitid(), 1, 1);
            return "habitResponse";
    }
}
