package top.pdcasystem.pdcasystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import top.pdcasystem.pdcasystem.Dao.NeedSolveMapper;
import top.pdcasystem.pdcasystem.Entity.NeedSolve;

import java.util.List;

@Service
public class NeedSolveService {

    @Autowired
    private NeedSolveMapper needSolveMapper;



    public int addNeedSolve(NeedSolve needSolve){
        if (needSolve == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        // 转义html标记
        needSolve.setObject(HtmlUtils.htmlEscape(needSolve.getObject()));
        needSolve.setContent(HtmlUtils.htmlEscape(needSolve.getContent()));
        needSolve.setComment(HtmlUtils.htmlEscape(needSolve.getComment()));
        return needSolveMapper.insertNeedSolve(needSolve);
    }

    public int insertNeedSolve(NeedSolve needSolve){
        return needSolveMapper.insertNeedSolve(needSolve);
    }

    public  List<NeedSolve> selectNeedSolve(int offset, int limit){
        return needSolveMapper.selectNeedSolve(offset,limit);
    }
    public  List<NeedSolve> selectNeedSolveNotFinish(int offset, int limit, int finish1, int finish2){
        return needSolveMapper.selectNeedSolveNotFinish(offset, limit, finish1, finish2);
    }
    public  NeedSolve selectNeedSolveByid(int i){
        return needSolveMapper.selectNeedSolveByid(i);
    }
    public  int updateAll(NeedSolve needSolve){
        return needSolveMapper.updateAll(needSolve);
    }
    public  int updateFinish(int id, int finish, int golevel, String comment, int tempdeadline){
        return needSolveMapper.updateFinish(id, finish, golevel, comment, tempdeadline);
    }
    public  int updateDelay(int id, int delay, int golevel, String comment, int tempdeadline){
        return needSolveMapper.updateDelay(id, delay, golevel, comment, tempdeadline);
    }
    public  int updateFinishIt(int id ,int finish){
        return needSolveMapper.updateFinishIt(id, finish);
    }
    public int updateTempdeadline(int finish1, int finish2){
        return needSolveMapper.updateTempdeadline(finish1, finish2);
    }

}
