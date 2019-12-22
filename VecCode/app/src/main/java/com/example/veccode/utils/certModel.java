package com.example.veccode.utils;

public class certModel {

    String image,link,name,desc,rules;

    public certModel() {
    }


    public certModel(String image, String link, String name, String desc, String rules) {
        this.image = image;
        this.link = link;
        this.name = name;
        this.desc=desc;
        this.rules=rules;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
