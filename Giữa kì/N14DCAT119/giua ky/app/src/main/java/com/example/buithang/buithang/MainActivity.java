package com.example.buithang.buithang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Button bt_them;
    QuanLyCongViec dataBase_Cv;
    ImageView xem_ds;
    Adapter adapter;
    ImageView muctieu;
    ArrayList<CongViec> congViecs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        dataBase_Cv=new QuanLyCongViec(MainActivity.this);
        congViecs=new ArrayList<>();
        congViecs=dataBase_Cv.xemCongViec();
        adapter=new Adapter(congViecs,MainActivity.this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout, R.string.open, R.string.close);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId()== R.id.menu_qlcv)
                {
                    Intent intent=new Intent(MainActivity.this,QuanTri_cv.class);
                    startActivityForResult(intent,999);
                }
                else
                {
                    ThemCongViec();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.draw_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Goals");
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemCongViec();
            }
        });

        xem_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder aBuilder= new AlertDialog.Builder(MainActivity.this);
                View view=getLayoutInflater().inflate(R.layout.dialog_listview,null);
                aBuilder.setView(view);
                dataBase_Cv=new QuanLyCongViec(MainActivity.this);
                congViecs=new ArrayList<>();
                congViecs=dataBase_Cv.xemCongViec();
                adapter=new Adapter(congViecs,MainActivity.this);
                ListView listView_dl=(ListView) view.findViewById(R.id.listview_dl);
                listView_dl.setAdapter(adapter);
                aBuilder.setCancelable(true);
                AlertDialog dialog=aBuilder.create();
                dialog.show();
            }
        });
        muctieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder aBuilder= new AlertDialog.Builder(MainActivity.this);
                View view=getLayoutInflater().inflate(R.layout.dialog_listview,null);
                aBuilder.setView(view);
                dataBase_Cv=new QuanLyCongViec(MainActivity.this);
                congViecs=new ArrayList<>();
                congViecs=dataBase_Cv.xem5CongViec();
                adapter=new Adapter(congViecs,MainActivity.this);
                ListView listView_dl=(ListView) view.findViewById(R.id.listview_dl);
                listView_dl.setAdapter(adapter);
                aBuilder.setCancelable(true);
                AlertDialog dialog=aBuilder.create();
                dialog.show();
            }
        });

    }

    public void ThemCongViec()
    {

                AlertDialog.Builder aBuilder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.dialog_them_cv,null);
                final EditText ten_cv1=(EditText) view.findViewById(R.id.ten_cv);
                final EditText noidung_cv1=(EditText) view.findViewById(R.id.noidung_cv);
                final EditText start_cv1=(EditText) view.findViewById(R.id.start_cv);
                final EditText end_cv1=(EditText) view.findViewById(R.id.end_cv);
                final EditText ghichu_cv1=(EditText) view.findViewById(R.id.ghichu_cv);
                final Button add_cv1=(Button) view.findViewById(R.id.add_cv);
                final Button cc_cv1=(Button) view.findViewById(R.id.cancel_cv);
                aBuilder.setView(view);
                final AlertDialog alertDialog=aBuilder.create();
                alertDialog.show();
                add_cv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ten_cv1.getText().toString().equals("") || noidung_cv1.getText().toString().equals("") || start_cv1.getText().toString().equals("") || end_cv1.getText().toString().equals("")|| ghichu_cv1.getText().toString().equals(""))
                        {
                            Toast.makeText(MainActivity.this, "Bạn Không Được Bỏ Trống !!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            dataBase_Cv.themCongViec(new CongViec(ten_cv1.getText().toString(),noidung_cv1.getText().toString(),start_cv1.getText().toString(),end_cv1.getText().toString(),ghichu_cv1.getText().toString()));
                            alertDialog.cancel();
                            Toast.makeText(MainActivity.this, "Đã thêm công việc", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cc_cv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });


    }
    public void AnhXa()
    {
        navigationView = (NavigationView) findViewById(R.id.nav);
        drawerLayout=(DrawerLayout) findViewById(R.id.draw_layout);
        bt_them=(Button) findViewById(R.id.bt_them);
        xem_ds=(ImageView) findViewById(R.id.xem_ds);
        muctieu=(ImageView) findViewById(R.id.muctieu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
