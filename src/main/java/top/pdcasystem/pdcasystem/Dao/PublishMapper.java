package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.Publish;

import java.util.List;

@Mapper
public interface PublishMapper {

    List<Publish> selectByUpdate(int offset, int limit);
    Publish selectByid(int id);
    int insertPublish(Publish publish);
}
