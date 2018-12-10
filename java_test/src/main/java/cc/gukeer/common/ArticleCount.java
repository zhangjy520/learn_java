package cc.gukeer.common;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

public class ArticleCount implements Serializable {
    public String bigArticle; //1
    public String article;//2
    public String genre;//3
    public String style;//4
    public String mode;//5
    public String topic;//6
    public String classe;//7
    public String medium;//8
    public String publisher;//9
    public String publishlocation;//10
    public String publishdate;//11
    public String title;//12
    public String text;//12
    public Integer textCount;//12

    public String nationality;//13
    public String edition;//14
    public String name;//15
    public String sex;//16
    public String nativelang;//17
    private final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return "Article{" +
                "bigArticle='" + bigArticle + '\'' +
                ", article='" + article + '\'' +
                ", genre='" + genre + '\'' +
                ", style='" + style + '\'' +
                ", mode='" + mode + '\'' +
                ", topic='" + topic + '\'' +
                ", classe='" + classe + '\'' +
                ", medium='" + medium + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishlocation='" + publishlocation + '\'' +
                ", publishdate='" + publishdate + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", nationality='" + nationality + '\'' +
                ", edition='" + edition + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nativelang='" + nativelang + '\'' +
                ", serialVersionUID=" + serialVersionUID +
                '}';
    }

    @ExcelField(title = "大语篇编号", align = 2, sort = 1, groups = {1, 2})
    public String getBigArticle() {
        return bigArticle;
    }

    public void setBigArticle(String bigArticle) {
        this.bigArticle = bigArticle;
    }

    @ExcelField(title = "小语篇编号", align = 2, sort = 2, groups = {1, 2})
    public String getArticle() {
        return article;
    }

    @ExcelField(title = "字数", align = 2, sort = 3, groups = {1, 2})
    public Integer getTextCount() {
        return textCount;
    }

    public void setTextCount(Integer textCount) {
        this.textCount = textCount;
    }

    public void setArticle(String article) {
        this.article = article;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNativelang() {
        return nativelang;
    }

    public void setNativelang(String nativelang) {
        this.nativelang = nativelang;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishlocation() {
        return publishlocation;
    }

    public void setPublishlocation(String publishlocation) {
        this.publishlocation = publishlocation;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public long getSerialVersionUID() {
        return serialVersionUID;
    }
}