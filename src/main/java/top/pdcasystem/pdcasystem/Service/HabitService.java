package top.pdcasystem.pdcasystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.pdcasystem.pdcasystem.Dao.HabitLogMapper;
import top.pdcasystem.pdcasystem.Dao.HabitMapper;
import top.pdcasystem.pdcasystem.Entity.Habit;
import top.pdcasystem.pdcasystem.Entity.HabitLog;

import java.util.List;

@Service
public class HabitService {

    @Autowired
    HabitMapper habitMapper;

    @Autowired
    HabitLogMapper habitLogMapper;

    public Habit selecthabitById(int id){
        return habitMapper.selectById(id);
    }

    public HabitLog selecthabitlogById(int id){
        return habitLogMapper.selectById(id);
    }

    public Habit selectByObject(String object){
        return  habitMapper.selectByObject(object);
    }

    public HabitLog selectLogByObject(String object){
        return habitLogMapper.selectByObject(object);
    }

    public List<HabitLog> selectByNotFinish(int finish, int offset, int limit){
        return habitLogMapper.selectByNotFinish(finish, offset, limit);
    }

    public List<HabitLog> selectOrderByGenerateTime(int offset,int limit){
        return habitLogMapper.selectOrderByGenerateTime(offset, limit);
    }

    public List<Habit> selectByStatus(int status,int offset,int limit){
        return habitMapper.selectByStatus(status, offset, limit);
    }

    public int insertHabit(Habit habit){
        return habitMapper.insertHabit(habit);
    }

    public int updateFinish(int id, int finish){
        return habitLogMapper.updateFinish(id, finish);
    }

    public int updateComment(int id, String comment){
        return habitLogMapper.updateComment(id, comment);
    }

    public int updateHabit(Habit habit) {
       return habitMapper.updateAll ( habit.getId(), habit.getTyped(),habit.getObject(),habit.getContent(), habit.getComment(),habit.getStatus(), habit.getTimeset(),
       habit.getWeight(), habit.getTotal(), habit.getTotalfinish(), habit.getNewtotal(), habit.getNewfinish());
    }

    public void generateHabitLog( int timenow){
        List<Habit> habits = selectByStatus(0,0,500);
        for (Habit a : habits) {
            int circle = 0;
            circle = Integer.parseInt(a.getTimeset());
            System.out.println(a.toString());
            if (circle > 1) {
               if (timenow % circle == 0) {
                   HabitLog habitLog = produceHabit(a);
                   habitLogMapper.insertHabitLog(habitLog);
                   habitMapper.updateTotal(a.getId(), 1, 1);
                   continue;
               }
                System.out.println("problem");
               continue;
            }
            HabitLog habitLog = produceHabit(a);
            habitLogMapper.insertHabitLog(habitLog);
            habitMapper.updateTotal(a.getId(), 1, 1);
        }

    }

    public HabitLog produceHabit(Habit habit){
        HabitLog habitLog = new HabitLog();
        habitLog.setTyped(habit.getTyped());
        habitLog.setObject(habit.getObject());
        habitLog.setContent(habit.getContent());
        habitLog.setComment("");
        habitLog.setWeight(habit.getWeight());
        habitLog.setHabitid(habit.getId());
        return habitLog;
    }

    public int updateHabitFinishNum(int id,int totalfinish, int newfinish){
        return  habitMapper.updateFinish(id, totalfinish, newfinish);
    }
}
