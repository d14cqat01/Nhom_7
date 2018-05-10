package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Main2_011 extends AppCompatActivity {
    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    ArrayList<String> dsChu;
    HinhAd_Main2 adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControl();
        addEvent();
    }

    private void addEvent() {
        gvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent in = new Intent (Main2_011.this,CanCuocActivity.class);
                        startActivity (in);
                        break;
                    }
                    case 1:{
                        Intent in = new Intent (Main2_011.this,BHYTActivity.class);
                        startActivity (in);
                        break;
                    }
                    case 2:{
                        Intent in = new Intent (Main2_011.this,BangLaiActivity.class);
                        startActivity(in);
                        break;
                    }
                    case 3:{
                        Intent in = new Intent (Main2_011.this,Note_011.class);
                        startActivity (in);
                        break;
                    }
                    case 4:{
                        Intent in = new Intent (Main2_011.this,HistoryActivity.class);
                        in.putExtra("ID",id);
                        startActivity (in);
                        break;
                    }
                    case 5:{
                        Intent in = new Intent (Main2_011.this,UpdateActivity.class);
                        in.putExtra("ID",id);
                        startActivity (in);
                        break;

                    }
                    case 6:{
                        Intent in = new Intent (Main2_011.this,KhieuNaiActivity.class);
                        startActivity (in);
                        break;

                    }
                    case 7:{
                        Intent in = new Intent (Main2_011.this,Tongquan.class);
                        startActivity (in);
                        break;

                    }
                    case 8:{
                        Intent in = new Intent (Main2_011.this,LaySoKhamBenhActivity.class);
                        startActivity (in);
                        break;

                    }
                    case 9:{
                        Intent in = new Intent (Main2_011.this,CaiDatActivity.class);
                        startActivity (in);
                        break;

                    }

                }
            }
        });

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
                Intent in = new Intent(Main2_011.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void xuly2() {
        Intent intent=new Intent(Main2_011.this, Main1_011.class);
        startActivity(intent);
    }


    private void addControl() {

        Intent intent=getIntent();
        id=intent.getStringExtra("ID");

        gvHinh=(GridView)findViewById(R.id.gvHinh);
        dsHinh=new ArrayList<>();
        dsChu=new ArrayList<>();
        adapter=new HinhAd_Main2(Main2_011.this,R.layout.item_main2,dsHinh,dsChu);
        dsHinh.add(R.drawable.f6);
        dsHinh.add(R.drawable.d4);
        dsHinh.add(R.drawable.b2);
        dsHinh.add(R.drawable.notepad);
        dsHinh.add(R.drawable.history);
        dsHinh.add(R.drawable.update);
        dsHinh.add(R.drawable.reporter);
        dsHinh.add(R.drawable.quanlythunhap);
        dsHinh.add(R.drawable.doctor);
        dsHinh.add(R.drawable.information);
        dsChu.add("Chứng minh nhân dân");
        dsChu.add("Bảo hiểm y tế");
        dsChu.add("Bằng Lái xe");
        dsChu.add("Ghi chú");
        dsChu.add("Lịch sử");
        dsChu.add("Cập Nhật");
        dsChu.add("Báo Cáo");
        dsChu.add("Quản Lý Chi Tieu");
        dsChu.add("Lấy Số Khám Bệnh");
        dsChu.add("Thông Tin Ứng Dụng");

        gvHinh.setAdapter(adapter);



    }
}
