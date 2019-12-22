package com.example.veccode.utils;

public class gameModel {

    String ques;
    String ans;
    String tag;
    String expl;

    public gameModel() {
    }

    public gameModel(String ques, String ans, String tag, String expl) {
        this.ques = ques;
        this.ans = ans;
        this.tag = tag;
        this.expl = expl;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }
}
