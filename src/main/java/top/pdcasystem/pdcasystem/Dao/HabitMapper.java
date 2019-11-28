package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.Habit;

import java.util.List;

@Mapper
public interface HabitMapper {
    //@param 给注解取别名的如果只有一个参数，在if，则必须用别名
    Habit selectById(int id);
    Habit selectByObject(String object);
    List<Habit> selectByRealUsing( int offset, int limit);
    List<Habit> selectByStatus(int status,int offset,int limit);
    List<Habit> selectOrderByGenerateTime(int offset,int limit);
    List<Habit> selectOrderByUpdateTime(int offset,int limit);

    int insertHabit(Habit habit);

    int updateUpdatetime(int id);
    int updateTotal(int id,int total, int newtotal);
    int updateFinish(int id,int totalfinish, int newfinish);
    int updateAll(int id,String typed,String object, String content, String comment,int status, String timeset, int weight, int total, int totalfinish, int newtotal, int newfinish);
    int updateStatus(int id, int status);
}
