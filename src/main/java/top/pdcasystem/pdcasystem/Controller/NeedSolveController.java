package top.pdcasystem.pdcasystem.Controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.pdcasystem.pdcasystem.Entity.Habit;
import top.pdcasystem.pdcasystem.Entity.NeedSolve;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Service.NeedSolveService;
import top.pdcasystem.pdcasystem.Util.CommonUtil;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/needsolve")
public class NeedSolveController {

    @Autowired
    private NeedSolveService needSolveService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String addNeedSolve(String object, String content ){
        NeedSolve needSolve = new NeedSolve();
        needSolve.setObject(object);
        needSolve.setContent(content);
        needSolveService.addNeedSolve(needSolve);
        return CommonUtil.getJSONString(0,"发送成功");
    }

    @LoginRequired
    @RequestMapping(path="/setneedsolve",method = RequestMethod.POST)
    public String getplan(
            Model model, int id, String object, String content, String comment, int weight, int afraid ,int tempdeadline ,int deadline
    ){
        NeedSolve old = null;
        if (id != 0) {
            old = needSolveService.selectNeedSolveByid(id);
        }
        if(tempdeadline < 1 ) {
            tempdeadline = 1;
        }
        if(deadline < 1 ) {
            deadline = 1;
        }
        if (old == null || id == 0) {
            NeedSolve needSolve = new NeedSolve();

            needSolve.setAfraid(afraid);
            needSolve.setObject(object);
            needSolve.setContent(content);
            needSolve.setComment(comment);
            needSolve.setWeight(weight);
            needSolve.setTempdeadline(tempdeadline);
            needSolve.setDeadline(deadline);

            needSolveService.insertNeedSolve(needSolve);
            return "needSolveResponse";
        }

        if (!StringUtils.isBlank(object)) {
            old.setObject(object);
        }
        if (!StringUtils.isBlank(content)) {
            old.setContent(content);
        }
        if (!StringUtils.isBlank(comment)) {
            String newComment = old.getComment() + " + " + comment;
            old.setComment(newComment);
        }
        if (weight != 0) {
                old.setWeight(weight);
        }
        if (afraid != 0 ){
            old.setAfraid(afraid);
        }
        if (tempdeadline != 0 ){
            old.setTempdeadline(tempdeadline);
        }
        if (deadline != 0 ){
            old.setDeadline(deadline);
        }
        needSolveService.updateAll(old);

        return "needSolveResponse";
    }

    @LoginRequired
    @RequestMapping(path="/setneedsolvefinishstate",method = RequestMethod.POST)
    public String setfinish(
            Model model, int id, String finish, String golevel, String comment, String tempdeadline
    ){
        NeedSolve  needSolve=needSolveService.selectNeedSolveByid(id);
        String result = new String();
        int deadline = needSolve.getTempdeadline();
        int golevelit = needSolve.getGolevel();
        int finishreal = needSolve.getFinish();
        if (deadline < 0) {
            deadline = 1;
        }
        if(comment.length()>0){

            result=needSolve.getComment()+" + "+comment;

        }else {
            result = needSolve.getComment();
        }
        if (!StringUtils.isBlank(tempdeadline)) {
            if (StringUtils.isNumeric(tempdeadline)) {
                deadline = Integer.parseInt(tempdeadline);
            }
        }
        if (!StringUtils.isBlank(finish)) {
            if (StringUtils.isNumeric(finish)) {
                finishreal = Integer.parseInt(finish);
            }
        }
        if (!StringUtils.isBlank(golevel)) {
            if (StringUtils.isNumeric(golevel)) {
                golevelit = Integer.parseInt(golevel);
            }
        }
        needSolveService.updateFinish(id, finishreal, golevelit, result, deadline);

        return "needSolveResponse";
    }
/**
    @LoginRequired
    @RequestMapping(path="/setneedsolvedelaystate",method = RequestMethod.POST)
    public String setdelay(
            Model model, int id, int delay, int golevel, String comment, String tempdeadline
    ){
        NeedSolve  needSolve=needSolveService.selectNeedSolveByid(id);
        String result = new String();
        int deadline = needSolve.getTempdeadline();
        if(comment.length()>0){

            result=needSolve.getComment()+" + "+comment;

        }else {
            result = needSolve.getComment();
        }
        if (!StringUtils.isBlank(tempdeadline)) {
            if (StringUtils.isNumeric(tempdeadline)) {
                deadline = Integer.parseInt(tempdeadline);
            }
        }
        needSolveService.updateFinish(id, delay, golevel, result, deadline);

        return "needSolveResponse";
    }

**/

    @LoginRequired
    @RequestMapping(path="/setneedsolvefinishbyid/{id}",method = RequestMethod.GET)
    public String setdelaybyid(
            Model model, @PathVariable("id") int id
    ){

        needSolveService.updateFinishIt(id, 1);

        return "needSolveResponse";
    }

    @RequestMapping(path={"/getneedsolve"},method = RequestMethod.GET)
    public  String getPlan(Model model){

        List<NeedSolve> planLogs=needSolveService.selectNeedSolve(0,50);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(NeedSolve a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                Timestamp gt = new Timestamp((long)a.getGeneratetime()*1000);
                Timestamp ut = new Timestamp((long)a.getUpdatetime()*1000);
                Date gd = new Date(gt.getTime());
                Date ud = new Date(ut.getTime());
                map.put("gendate", gd);
                map.put("update", ud);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "needsolve";
    }

    @RequestMapping(path="/getnotfinishneedsolve",method = RequestMethod.GET)
    public  String getNotFinishPlan(Model model){

        List<NeedSolve> planLogs=needSolveService.selectNeedSolveNotFinish(0,50,0,5);
        List<Map<String,Object>> post=new ArrayList<>();
        if(planLogs!=null){
            for(NeedSolve a:planLogs){
                Map<String,Object> map=new HashMap<>();
                map.put("planlog",a);
                Timestamp gt = new Timestamp((long)a.getGeneratetime()*1000);
                Timestamp ut = new Timestamp((long)a.getUpdatetime()*1000);
                Date gd = new Date(gt.getTime());
                Date ud = new Date(ut.getTime());
                map.put("gendate", gd);
                map.put("update", ud);
                post.add(map);
            }

        }
        model.addAttribute("planPost",post);
        return "needsolve";
    }

    @RequestMapping(path="/getsetneedsolve",method = RequestMethod.GET)
    public  String getsetPlan(){
        return "needsolveWrite";
    }


}
