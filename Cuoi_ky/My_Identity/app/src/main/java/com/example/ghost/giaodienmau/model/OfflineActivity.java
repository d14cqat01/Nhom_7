package com.example.ghost.giaodienmau.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ghost.giaodienmau.BHYTActivity;
import com.example.ghost.giaodienmau.BangLaiActivity;
import com.example.ghost.giaodienmau.CanCuocActivity;
import com.example.ghost.giaodienmau.HinhAd_Main2;
import com.example.ghost.giaodienmau.HistoryActivity;
import com.example.ghost.giaodienmau.KhanCapActivity;
import com.example.ghost.giaodienmau.KhieuNaiActivity;
import com.example.ghost.giaodienmau.Main2_011;
import com.example.ghost.giaodienmau.Note_011;
import com.example.ghost.giaodienmau.R;
import com.example.ghost.giaodienmau.Tongquan;
import com.example.ghost.giaodienmau.UpdateActivity;

import java.util.ArrayList;

public class OfflineActivity extends AppCompatActivity {
    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    ArrayList<String> dsChu;
    HinhAd_Main2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        addControl();
        addEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khancap, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.khancap: {
                Intent in = new Intent(OfflineActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    private void addEvent() {
        gvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent in = new Intent (OfflineActivity.this,Tongquan.class);
                        startActivity (in);
                        break;
                    }
                    case 1:{
                        Intent in = new Intent (OfflineActivity.this,Note_011.class);
                        startActivity (in);
                        break;
                    }


                }
            }
        });

    }
    private void addControl() {


        gvHinh=(GridView)findViewById(R.id.gvHinh);
        dsHinh=new ArrayList<>();
        dsChu=new ArrayList<>();
        adapter=new HinhAd_Main2(OfflineActivity.this,R.layout.item_main2,dsHinh,dsChu);
        dsHinh.add(R.drawable.quanlythunhap);
        dsHinh.add(R.drawable.notepad);
        dsChu.add("Quản Lý Chi Tiêu");
        dsChu.add("Ghi Chú");



        gvHinh.setAdapter(adapter);



    }
}
