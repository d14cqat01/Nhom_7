package com.example.vankhoa.quanlinhanvien;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemActivity extends AppCompatActivity {
    EditText edtID,edtTen,edtSDT;
    final  String DATABASE_NAME = "quanlinhanvien.sqlite";
    SQLiteDatabase database;

    Button btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        edtID=findViewById(R.id.edtID);
        edtTen=findViewById(R.id.edtTen);
        edtSDT=findViewById(R.id.edtSDT);
        btnLuu=findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(Integer.parseInt(edtID.getText().toString()),edtTen.getText().toString(),edtSDT.getText().toString());
            }
        });
    }

    public void save(Integer id, String ten, String SDT){
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("Ten",ten);
        contentValues.put("SDT",SDT);
        database=Database.initDatabase(this,DATABASE_NAME);
        database.insert("NhanVien",null,contentValues);
        finish();
    }




}
