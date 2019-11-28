package top.pdcasystem.pdcasystem.Dao;

import org.apache.ibatis.annotations.Mapper;
import top.pdcasystem.pdcasystem.Entity.LoginTicket;

@Mapper
public interface LoginTicketMapper {
    LoginTicket selectById(int id);
    LoginTicket selectByTicket(String ticket);
    LoginTicket selectByUserId(int userid);

    int insertTicket(LoginTicket loginTicket);
    int updateTicket(int userid, String ticket);
    int updateExpireById(int id, int expire);
    int updateExpire(String ticket, int expire);
    int updateStatus(String ticket, int status);

}
