package com.example.ghost.giaodienmau;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note_edit extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    Button but_Up;
    Button but_Delete;
    Button but_Thoat;
    Intent intent;
    Note_class_011 note;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit);
        addControl();
        addEvent();
    }

    private void addEvent() {
        intent=getIntent();
        note=(Note_class_011) intent.getSerializableExtra("note_cl");
        textView1.setText(note.getTieude());
        textView2.setText(note.getNoidung());
        but_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTieude(textView1.getText().toString());
                note.setNoidung(textView2.getText().toString());
                note.setDate(ngay());
                intent.putExtra("Note_cl",note);
                setResult(33,intent);
                finish();
            }
        });
        but_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("Note_cl",note);
                setResult(44,intent);
                finish();
            }
        });
        but_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(33,intent);
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
        textView1=(TextView)findViewById(R.id.edit_Tieude);
        textView2=(TextView)findViewById(R.id.edit_ND);
        but_Up=(Button)findViewById(R.id.edit_UP);
        but_Thoat=(Button)findViewById(R.id.editThoat);
        but_Delete=(Button)findViewById((R.id.editXoa));
    }
}
