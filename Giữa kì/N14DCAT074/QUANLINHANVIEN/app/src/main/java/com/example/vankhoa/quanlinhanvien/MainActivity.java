package com.example.vankhoa.quanlinhanvien;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 final  String DATABASE_NAME = "quanlinhanvien.sqlite";
 SQLiteDatabase database;
 ListView listview;
 ArrayList<NhanVien> list;
 AdapterNhanVien adapter;
 Button btnThem,btnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Database.initDatabase(this, DATABASE_NAME);
        addControls();
        readData();

//        adapter = new AdapterNhanVien(this, list);
//        listview.setAdapter(adapter);

        final AdapterNV adapterNV=new AdapterNV(this, R.layout.listview,list);
        listview.setAdapter(adapterNV);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
                adapterNV.notifyDataSetChanged();
            }
        });
    }

    private void addControls() {
        listview = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        btnLoad=findViewById(R.id.btnLoad);
        btnThem=findViewById(R.id.btnThem);
    }
    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor  cursor = database.rawQuery("SELECT * from NhanVien", null);
        list.clear();
        for(int i =0;i< cursor.getCount();i++)
      {
          cursor.moveToPosition(i);
          int id= cursor.getInt(0);
          String ten = cursor.getString(1);
          String sdt = cursor.getString(2);
          byte[] anh = cursor.getBlob(3);
          list.add(new NhanVien(id,ten,sdt,anh));

      }
    }


}
