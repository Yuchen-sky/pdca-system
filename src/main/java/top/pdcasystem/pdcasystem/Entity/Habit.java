package top.pdcasystem.pdcasystem.Entity;

public class Habit {
    private int id;
    private String typed;
    private String object;
    private String content;
    private String comment;
    private int status;
    private int generatetime;
    private int updatetime;
    private String timeset;
    private int weight;
    private int total;
    private int totalfinish;
    private int newtotal;
    private int newfinish;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getTimeset() {
        return timeset;
    }

    public void setTimeset(String timeset) {
        this.timeset = timeset;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalfinish() {
        return totalfinish;
    }

    public void setTotalfinish(int totalfinish) {
        this.totalfinish = totalfinish;
    }

    public int getNewtotal() {
        return newtotal;
    }

    public void setNewtotal(int newtotal) {
        this.newtotal = newtotal;
    }

    public int getNewfinish() {
        return newfinish;
    }

    public void setNewfinish(int newfinish) {
        this.newfinish = newfinish;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", typed='" + typed + '\'' +
                ", object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", generatetime=" + generatetime +
                ", updatetime=" + updatetime +
                ", timeset='" + timeset + '\'' +
                ", weight=" + weight +
                ", total=" + total +
                ", totalfinish=" + totalfinish +
                ", newtotal=" + newtotal +
                ", newfinish=" + newfinish +
                '}';
    }
}
