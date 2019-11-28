package top.pdcasystem.pdcasystem.Entity;

public class LoginTicket {
    private int id;
    private String ticket;
    private int userid;
    private int status;
    private int expire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", userid=" + userid +
                ", status=" + status +
                ", expire=" + expire +
                '}';
    }
}
