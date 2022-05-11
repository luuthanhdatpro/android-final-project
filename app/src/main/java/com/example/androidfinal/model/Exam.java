package com.example.androidfinal.model;

import java.io.Serializable;

public class Exam implements Serializable {
    int id;
    boolean isPassed;
    String title;

    public Exam(int id, boolean isPassed, String title) {
        this.id = id;
        this.isPassed = isPassed;
        this.title = title;
    }

    public Exam(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
