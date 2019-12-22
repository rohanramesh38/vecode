package com.example.veccode.utils;

public class instaModel {
String date,desc,image,link,like;

    public instaModel() {
    }

    public instaModel(String date, String desc, String image, String link, String like) {
        this.date = date;
        this.desc = desc;
        this.image = image;
        this.link = link;
        this.like = like;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}

