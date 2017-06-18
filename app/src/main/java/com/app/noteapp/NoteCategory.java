package com.app.noteapp;

import com.orm.SugarRecord;

/**
 * Created by Taufik on 06/14/17.
 */

public class NoteCategory extends SugarRecord<NoteCategory> {

    private String name,code;

    public NoteCategory(){}

    public NoteCategory(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
