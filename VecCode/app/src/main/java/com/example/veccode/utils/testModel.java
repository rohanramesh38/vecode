package com.example.veccode.utils;

public class testModel {

    String choice,expl,ques,sol;

    public testModel() {
    }

    public testModel(String choice, String expl, String ques, String sol) {
        this.choice = choice;
        this.expl = expl;
        this.ques = ques;
        this.sol = sol;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getSol() {
        return sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }
}
