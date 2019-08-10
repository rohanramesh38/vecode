package com.example.codeplus.ui;

import java.util.ArrayList;

public class AbstractModel {

    private String title;

    private String message;

    private int img;

    public AbstractModel(String title, String message,int img) {
        this.title = title;
        this.message = message;
        this.img=img;
    }

    public AbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getImg() {
        return img;
    }

    public void setIMg(int img) {
        this.img = img;
    }
}
