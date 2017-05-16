package cc.gukeer.support.analysis.entity;

/**
 * Created by lx on 2016/12/6.
 */
public class LeleInfo {
    private String id;
    private String grade;
    private String course;
    private String chapter;
    private String category;
    private String name;
    private String medium_link;
    private String poster;
    private String duration;
    private String shortdesc;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getCourse() {
        return course;
    }

    public String getCategory() {
        return category;
    }

    public String getMedium_link() {
        return medium_link;
    }

    public String getPoster() {
        return poster;
    }

    public String getDuration() {
        return duration;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMedium_link(String medium_link) {
        this.medium_link = medium_link;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}
