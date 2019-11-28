package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.PlanLog;

import java.util.Date;
import java.util.List;

@Mapper
public interface PlanLogMapper {
    //@param 给注解取别名的如果只有一个参数，在if，则必须用别名
    PlanLog selectById(int id);
    List<PlanLog> selectByLevel(int thinglevel,int offset,int limit);
    List<PlanLog> selectByContent(String content,int offset,int limit);
    List<PlanLog> selectByUpdateDate(Date date,int offset,int limit);
    List<PlanLog> selectByUpdateDateNotFinish(Date updatedate,int offset,int limit);
    List<PlanLog> selectByNotFinish(int offset,int limit);
    List<PlanLog> selectByInitDate(Date date,int offset,int limit);
    List<PlanLog> selectByObject(String object,int offset,int limit);
    List<PlanLog> selectOrderByGenerateTime(int offset,int limit);
    List<PlanLog> selectOrderByUpdateTime(int offset,int limit);


    int insertPlanLog(PlanLog planLog);

    int updateUpdatetime(int id,int updatetime);
    int updateWeight(int id,int weight);
    int updateDelay(int id,int delay);
    int updateFinish(int id,int finish);
    int updateUpdateDate(int id,Date updatedate);
    int updateComment(int id,String comment);
}
