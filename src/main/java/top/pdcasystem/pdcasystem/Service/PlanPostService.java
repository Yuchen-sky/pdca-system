package top.pdcasystem.pdcasystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.pdcasystem.pdcasystem.Dao.PlanLogMapper;
import top.pdcasystem.pdcasystem.Entity.PlanLog;

import java.util.Date;
import java.util.List;

@Service
public class PlanPostService {
    @Autowired
    private PlanLogMapper planLogMapper;

    public PlanLog selectById(int id){
        return planLogMapper.selectById(id);
    }

    public List<PlanLog> selectByLevel(int thinglevel,int offset,int limit){
        return planLogMapper.selectByLevel(thinglevel,offset,limit);
    }

    public List<PlanLog> selectByContent(String content,int offset,int limit){
        return planLogMapper.selectByContent(content,offset,limit);
    }

    public List<PlanLog> selectByUpdateDate(Date date, int offset, int limit){
        return planLogMapper.selectByUpdateDate(date,offset,limit);
    }

    public List<PlanLog> selectByUpdateDateNotFinish(Date date,int offset,int limit){
        return planLogMapper.selectByUpdateDateNotFinish(date,offset,limit);
    }

    public List<PlanLog> selectByNotFinish(int offset,int limit){
        return planLogMapper.selectByNotFinish(offset,limit);
    }

    public List<PlanLog> selectByInitDate(Date date,int offset,int limit){
        return planLogMapper.selectByInitDate(date,offset,limit);
    }

    public List<PlanLog> selectByObject(String object,int offset,int limit){
        return planLogMapper.selectByObject(object,offset,limit);
    }

    public List<PlanLog> selectOrderByGenerateTime(int offset,int limit){
        return planLogMapper.selectOrderByGenerateTime(offset, limit);
    }

    public List<PlanLog> selectOrderByUpdateTime(int offset,int limit){
        return planLogMapper.selectOrderByUpdateTime(offset, limit);
    }

    public int insertPlanLog(PlanLog planLog){
        return planLogMapper.insertPlanLog(planLog);
    }

    public int updateFinish(int id,int finish){
        return planLogMapper.updateFinish(id,finish);
    }

    public int updateComment(int id,String comment){
        return planLogMapper.updateComment(id,comment);
    }

    public int updateDelay(int id){
        return planLogMapper.updateDelay(id,1);
    }

    public int updateUpdateDate(int id,Date date){
        return planLogMapper.updateUpdateDate(id,date);
    }



}
