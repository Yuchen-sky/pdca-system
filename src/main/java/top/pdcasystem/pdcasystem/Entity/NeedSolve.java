package top.pdcasystem.pdcasystem.Entity;

public class NeedSolve {
    private int id;
    private String object;
    private String content;
    private String comment;
    private int weight;
    private int afraid;
    private int finish;
    private int delay;
    private int golevel;
    private int tempdeadline;
    private int deadline;
    private int updatetime;
    private int generatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAfraid() {
        return afraid;
    }

    public void setAfraid(int afraid) {
        this.afraid = afraid;
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



    public int getGolevel() {
        return golevel;
    }

    public void setGolevel(int golevel) {
        this.golevel = golevel;
    }

    public int getTempdeadline() {
        return tempdeadline;
    }

    public void setTempdeadline(int tempdeadline) {
        this.tempdeadline = tempdeadline;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public int getGeneratetime() {
        return generatetime;
    }

    public void setGeneratetime(int generatetime) {
        this.generatetime = generatetime;
    }


    @Override
    public String toString() {
        return "NeedSolve{" +
                "id=" + id +
                ", object='" + object + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", weight=" + weight +
                ", afraid=" + afraid +
                ", finish=" + finish +
                ", delay=" + delay +
                ", golevel=" + golevel +
                ", tempdeadline=" + tempdeadline +
                ", deadline=" + deadline +
                ", updatetime=" + updatetime +
                ", generatetime=" + generatetime +
                '}';
    }
}
