package top.pdcasystem.pdcasystem.Entity;

import java.sql.Date;

public class DateUtil {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateUtil{" +
                "date=" + date +
                '}';
    }

    private Date date;

}
