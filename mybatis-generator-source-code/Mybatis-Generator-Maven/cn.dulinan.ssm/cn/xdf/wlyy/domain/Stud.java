/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2016年09月06日 13:54:24
 * <p>2016-3-21
 * Company: 北京新东方学校
 * <p>
 * Author: dulinan@xdf.cn
 * <p>
 * Version: 1.0
 * <p>
 */
package cn.xdf.wlyy.domain;

public class Stud {

    private String id;
    private String name;
    private Integer score;
    private String subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }
}