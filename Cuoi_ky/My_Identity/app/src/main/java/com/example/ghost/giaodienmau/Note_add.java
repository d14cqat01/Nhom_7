package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note_add extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    Button add;
    Button thoat;
    Intent intent;
    Note_class_011 note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add);
        addControl();
        addEvent();
    }

    private void addEvent() {
        intent=getIntent();
        note=new Note_class_011();
        int ID= (int)intent.getIntExtra("ID",-1);
        note.setMaNote(ID);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTieude(textView1.getText().toString());
                note.setNoidung(textView2.getText().toString());
                note.setDate(ngay());
                intent.putExtra("Note_cl",note);
                setResult(22,intent);
                finish();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(22,intent);
                finish();
            }
        });
    }
    private String ngay()
    {
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    private void addControl() {
        textView1=(TextView)findViewById(R.id.add_Tieude);
        textView2=(TextView)findViewById(R.id.add_ND);
        add =(Button)findViewById(R.id.noteAdd);
        thoat =(Button)findViewById(R.id.noteThoat);
    }
}

