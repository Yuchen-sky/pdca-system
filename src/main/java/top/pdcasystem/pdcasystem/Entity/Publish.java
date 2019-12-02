package top.pdcasystem.pdcasystem.Entity;

public class Publish {
    private int id;
    private String source;
    private int sourceid;
    private String sourceObject;
    private String locate;
    private String title;
    private String comment;
    private int generatetime;
    private int updatetime;
    private int achievement;
    private String achieveComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(String sourceObject) {
        this.sourceObject = sourceObject;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public String getAchieveComment() {
        return achieveComment;
    }

    public void setAchieveComment(String achieveComment) {
        this.achieveComment = achieveComment;
    }


    @Override
    public String toString() {
        return "Publish{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", sourceid=" + sourceid +
                ", sourceObject='" + sourceObject + '\'' +
                ", locate='" + locate + '\'' +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", generatetime=" + generatetime +
                ", updatetime=" + updatetime +
                ", achievement=" + achievement +
                ", achieveComment='" + achieveComment + '\'' +
                '}';
    }
}
