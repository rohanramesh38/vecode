package com.example.veccode.utils;

public class profile {

    String Batch,Dept,Dob,Medal,Name,Reg,Section,Image,Lang,Book,Piechart;

    public profile() {
    }

    public profile(String batch, String dept, String dob, String medal, String name, String reg, String section,String lang,String image,String book,String piechart) {
        Batch = batch;
        Dept = dept;
        Dob = dob;
        Medal = medal;
        Name = name;
        Reg = reg;
        Section = section;
        Image=image;
        Lang=lang;
        Book=book;
        Piechart=piechart;

    }

    public String getPiechart() {
        return Piechart;
    }

    public void setPiechart(String piechart) {
        Piechart = piechart;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getDept() {
        return Dept;
    }

    public String getBook() {
        return Book;
    }

    public void setBook(String book) {
        Book = book;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getMedal() {
        return Medal;
    }

    public void setMedal(String medal) {
        Medal = medal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getReg() {
        return Reg;
    }

    public void setReg(String reg) {
        Reg = reg;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(String lang) {
        Lang = lang;
    }
}

