package com.app.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taufik on 06/06/17.
 */

public class UpdateActivity extends AppCompatActivity {

    EditText title,desc;
    Button btn;
    Note note;
    Spinner listOfCategory;
    List<NoteCategory> categoryList;
    List<String> category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_update_note);

        listOfCategory = (Spinner) findViewById(R.id.listOfCategory);

        categoryList = NoteCategory.listAll(NoteCategory.class);

        category = new ArrayList<>();
        for(NoteCategory note : categoryList){
            category.add(note.getName());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,category);

        this.listOfCategory.setAdapter(adapter);

        title = (EditText) findViewById(R.id.title_note_form);
        desc = (EditText) findViewById(R.id.desc_note_form);

        btn = (Button) findViewById(R.id.saveBtn);

        long id = getIntent().getLongExtra("ID",0);
        if( id != 0 ){
            note = Note.findById(Note.class,id);
            this.listOfCategory.setSelection(category.indexOf( note.getCategory().getName() ));

            if( note != null ){
                title.setText(note.getTitle());
                desc.setText(note.getDesc());
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleCtx = title.getText().toString();
                String descCtx = desc.getText().toString();
                int selectedItem = category.indexOf(listOfCategory.getSelectedItem());
                Log.d("SelectedItem",listOfCategory.getSelectedItem()+" yang terpilih");
                if( note == null ){
                    note = new Note();
                }
                note.setTitle(titleCtx);
                note.setDesc(descCtx);
                note.setCategory(categoryList.get(selectedItem));

                note.save();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                UpdateActivity.this.finish();
            }
        });

        super.onCreate(savedInstanceState);
    }
}
