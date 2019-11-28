package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.HabitLog;

import java.util.Date;
import java.util.List;

@Mapper
public interface HabitLogMapper {
    //@param 给注解取别名的如果只有一个参数，在if，则必须用别名
    HabitLog selectById(int id);
    List<HabitLog> selectByNotFinish(int finish, int offset,int limit);
    List<HabitLog> selectOrderByGenerateTime(int offset,int limit);
    HabitLog selectByObject(String object);

    int insertHabitLog(HabitLog habitLog);

    int updateUpdatetime(int id,int updatetime);
    int updateFinish(int id,int finish);
    int updateComment(int id,String comment);
}
