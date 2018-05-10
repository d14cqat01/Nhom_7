package com.example.ghost.giaodienmau;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by king_ on 24-Apr-18.
 */

public class Note_Adapter extends BaseExpandableListAdapter {
    Activity context;
    List<Note_class_011> noteclass;


    public Note_Adapter(Activity context, List<Note_class_011> note) {
        this.context = context;
        this.noteclass = note;
    }

    @Override
    public int getGroupCount() {
        return noteclass.size();
    }

    @Override
    public int getChildrenCount(int pos) {
        return 1;
    }

    @Override
    public Object getGroup(int pos) {
        return noteclass.get(pos);
    }

    @Override
    public Object getChild(int pos, int child) {
        return noteclass.get(pos).getNoidung();
    }

    @Override
    public long getGroupId(int pos) {// lay note thu may
        return pos;
    }

    @Override
    public long getChildId(int pos, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int pos, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.note_gruop,null);
        TextView textView=(TextView)view.findViewById(R.id.textViewNote);
        Note_class_011 note=noteclass.get(pos);
        textView.setText(note.getTieude());
        return view;
    }

    @Override
    public View getChildView(final int pos, int child, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.note_child,null);
        TextView textView=(TextView)view.findViewById(R.id.textChildNote);
        final Note_class_011 note1=noteclass.get(pos);
        textView.setText(note1.getNoidung());
        TextView textClock=(TextView) view.findViewById(R.id.textClock_note);
        textClock.setText(note1.getDate());
        Button img=(Button)view.findViewById(R.id.buttomNote);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent(note1);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int pos, int child) {
        return true;
    }
    private void addEvent(Note_class_011 note) {
        Intent intent=new Intent(this.context,Note_edit.class);
        intent.putExtra("note_cl",note);
        this.context.startActivityForResult(intent,99);


    }
}
