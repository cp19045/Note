package com.example.memorandum;

import java.util.ArrayList;

public class Note {
    private String title;
    private String text;
    private String type;
    private String uri;
    //static public final Note[] notes = {new Note("12333", "我的一天", "学习"),
     //       new Note("12333", "我的ssss一天", "学习"),
     //       new Note("12333", "我的ddd一天", "学习")};


    public Note(String title, String text, String type,String uri){
        this.title = title;
        this.text = text;
        this.type = type;
        this.uri = uri;
    }

    public Note(String title, String text, String type){
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public Note() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getType(){
        return type;
    }

    public String toString() {
        return this.title;
    }

}
