package top.pdcasystem.pdcasystem.Entity;

import java.sql.Date;

public class PlanLog {
    private int id;
    private int thinglevel;
    private String object;
    private String content;
    private String comment;
    private int weight;
    private int finish;
    private int delay;
    private Date initdate;
    private Date updatedate;
    private int generatetime;
    private int updatetime;

    @Override
    public String toString() {
        return "PlanLog{" +
                "id=" + id +
                ", thinglevel=" + thinglevel +
                ", object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", weight=" + weight +
                ", finish=" + finish +
                ", delay=" + delay +
                ", initdate=" + initdate +
                ", updatedate=" + updatedate +
                ", generatetime=" + generatetime +
                ", updatetime=" + updatetime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return thinglevel;
    }

    public void setLevel(int thinglevel) {
        this.thinglevel = thinglevel;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Date getInitdate() {
        return initdate;
    }

    public void setInitdate(Date initdate) {
        this.initdate = initdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public int getGeneratetime() {
        return generatetime;
    }

    public void setGeneratetime(int generatetime) {
        this.generatetime = generatetime;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }
}
