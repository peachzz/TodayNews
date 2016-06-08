package com.terry.todaynews.bean;

import java.io.Serializable;

public class NewsItem implements Serializable {

    /**
     * 标题
     */
    private String title;
    /**
     * 链接
     */
    private String link;
    /**
     * 图片链接
     */
    private String imageLink;
    /**
     * 更新时间
     */
    private String upDate;
    /**
     * 类型
     */
    private int type;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 时间
     */
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

    public int getType() {
        return type;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsItem[] = {title =" + title + ",link =" + link + ",upDate =" + upDate + ",imageLink =" + imageLink + ",summary =" + summary + ",date =" + date + "}";
    }
}
