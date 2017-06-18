package com.app.noteapp;

import com.orm.SugarRecord;

/**
 * Created by Taufik on 06/06/17.
 */

public class Note extends SugarRecord<Note> {

    String title,desc;

    NoteCategory category;

    public Note(){

    }

    public Note(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public Note(String title, String desc,NoteCategory category) {
        this(title,desc);
        this.category = category;
    }


    public void setCategory(NoteCategory category) {
        this.category = category;
    }

    public NoteCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
