package com.example.androidfinal.model;

import java.io.Serializable;

public class Sign implements Serializable {
    private int id;
    private String title;
    private String name;
    private String meaning;
    private String image;
    private String type;

    public Sign(int id, String title, String name, String meaning, String image, String type) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.meaning = meaning;
        this.image = image;
        this.type = type;
    }

    public Sign(String title, String name, String meaning, String image) {
        this.title = title;
        this.name = name;
        this.meaning = meaning;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
