package com.example.videodemo.net;

import java.io.Serializable;

public class ADBean implements Serializable {

    private int id;
    private int sortNum;
    private String image;
    private String link;
    private String title;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ADBean{" +
                "id=" + id +
                ", sortNum=" + sortNum +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
