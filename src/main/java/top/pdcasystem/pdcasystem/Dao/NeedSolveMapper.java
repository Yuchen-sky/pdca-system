package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.NeedSolve;

import java.util.List;

@Mapper
public interface NeedSolveMapper {

    int insertNeedSolve(NeedSolve needSolve);
    List<NeedSolve> selectNeedSolve(int offset, int limit);
    List<NeedSolve> selectNeedSolveNotFinish(int offset, int limit, int finish1, int finish2);
    NeedSolve selectNeedSolveByid(int i);
    int updateAll(NeedSolve needSolve);
    int updateFinish(int id, int finish, int golevel, String comment, int tempdeadline);
    int updateFinishIt(int id, int finish);
    int updateDelay(int id, int delay, int golevel, String comment, int tempdeadline);
    int updateTempdeadline(int finish1,int finish2);
}
