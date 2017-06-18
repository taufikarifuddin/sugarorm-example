package com.app.noteapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Taufik on 06/06/17.
 */

public class CustomListNoteAdapter extends BaseAdapter {

    List<Note> notes;
    LayoutInflater inflater;
    Context ctx;
    CustomListNoteAdapter(Context ctx,List<Note> notes){
        this.notes = notes;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ctx = ctx;
    }



    @Override
    public int getCount() {
        return this.notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            convertView = inflater.inflate(R.layout.adapter_list_note,parent,false);
        }

        final Note note = this.notes.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.title_note);
        TextView desc = (TextView) convertView.findViewById(R.id.detail_note);
        TextView category = (TextView) convertView.findViewById(R.id.category_name);


        Button editBtn = (Button) convertView.findViewById(R.id.edit_notes_btn);
        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_notes);

        title.setText(note.getTitle());
        desc.setText(note.getDesc());
        category.setText(note.getCategory().getName());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                dialog.setMessage("Apakah anda yakin ?")
                    .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Note noteObj = Note.findById(Note.class,note.getId());
                            if( noteObj != null ){
                                noteObj.delete();
                                notes.remove(note);
                                notifyDataSetChanged();
                            }
                        }
                    }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,UpdateActivity.class);
                intent.putExtra("ID",note.getId());
                ctx.startActivity(intent);
            }
        });

        return convertView;
    }
}
