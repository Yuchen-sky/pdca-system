package top.pdcasystem.pdcasystem.Entity;

public class HabitLog {
    private int id;
    private String typed;
    private String object;
    private String content;
    private String comment;
    private int weight;
    private int finish;
    private int generatetime;
    private int updatetime;
    private int habitid;

    public int getHabitid() {
        return habitid;
    }

    public void setHabitid(int habitid) {
        this.habitid = habitid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyped() {
        return typed;
    }

    public void setTyped(String typed) {
        this.typed = typed;
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

    @Override
    public String toString() {
        return "HabitLog{" +
                "id=" + id +
                ", typed='" + typed + '\'' +
                ", object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", weight=" + weight +
                ", finish=" + finish +
                ", generatetime=" + generatetime +
                ", updatetime=" + updatetime +
                ", habitid=" + habitid +
                '}';
    }
}
