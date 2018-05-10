package com.example.ghost.giaodienmau;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;

public class Tongquan extends AppCompatActivity implements View.OnClickListener{
    private Button bct,btn,btq;
    TextView tv;
    ListView lw;
    ArrayList<Money> mangMoney;
    ListviewAdapter listviewAdapter;
    SQLite sqLite;
    public static final String VITRI = "vitri";
    int sum = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tongquan);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled (true);
        actionBar.setDisplayShowTitleEnabled (false);
        connectview();
        sqLite = new SQLite (Tongquan.this);

        mangMoney = new ArrayList<> ();
        sqLite.loadData(mangMoney);
        tongtien();
        listviewAdapter = new ListviewAdapter (Tongquan.this,R.layout.list_item,mangMoney);
        lw.setAdapter (listviewAdapter);
    }

    private void tongtien() {
//        for(int i=0;i<mangMoney.size ();i++){
//            if(mangMoney.get (i).getMucdich ()==1){
//                String tmp =mangMoney.get (i).getSotien ();
//                String so ="";
//                for(int j=0;j<tmp.length ()-2;j++){
//                    so += tmp.charAt (i);
//                }
//                int tien = Integer.parseInt (so);
//                sum +=tien;
//            }else{
//                String tmp =mangMoney.get (i).getSotien ();
//                String so ="";
//                for(int j=0;j<tmp.length ()-2;j++){
//                    so += tmp.charAt (i);
//                }
//                int tien = Integer.parseInt (so);
//                sum -=tien;
//            }
//        }
        tv.setText (sum+" đ");
    }


    private void connectview() {
        bct = (Button) findViewById (R.id.bct);
        btn = (Button) findViewById (R.id.btn);
        btq = (Button) findViewById (R.id.btq);
        lw = (ListView) findViewById (R.id.lwtq);
        tv = (TextView) findViewById (R.id.conlai);


        btq.setOnClickListener (this);
        bct.setOnClickListener (this);
        btn.setOnClickListener (this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actice_nemu, menu);
        getMenuInflater ().inflate (R.menu.login_menu,menu);
        getMenuInflater ().inflate (R.menu.offline_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.madd:{
                doclickadd();
                break;
            }

            case android.R.id.home:
            {
                onBackPressed ();
                break;
            }
            case R.id.mdelete:{
                delete();
                break;
            }
            case R.id.medit:{
                edit();
                break;
            }
        }
        listviewAdapter.notifyDataSetChanged ();
        return super.onOptionsItemSelected(item);
    }

    private void edit() {
        for (int i = 0; i < mangMoney.size (); i++) {
            if (mangMoney.get (i).isChosen ()) {
                Intent in =new Intent (Tongquan.this,Sua.class);
                Money money = mangMoney.get (i);
                in.putExtra ("money",money);
                mangMoney.remove (i);
                sqLite.saveData (mangMoney);
                startActivity (in);
            }
        }
    }

    void delete() {
        for (int i = 0; i < mangMoney.size (); i++) {
            if (mangMoney.get (i).isChosen ()) {
                mangMoney.remove (i);
            }
        }
        sqLite.saveData (mangMoney);

    }

    private void doclickadd() {
        Intent in = new Intent (Tongquan.this,Add.class);
        startActivity (in);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btq:{
                break;
            }
            case R.id.bct:{
                doclickbct ();
                break;
            }
            case R.id.btn:{
                doclickbtn ();
                break;
            }
        }
    }


    private void doclickbct() {
        Intent in = new Intent (Tongquan.this,Chitieu.class);
        startActivity (in);
    }
    private void doclickbtn() {
        Intent in = new Intent (Tongquan.this,ThuNhap.class);
        startActivity (in);
    }
}
