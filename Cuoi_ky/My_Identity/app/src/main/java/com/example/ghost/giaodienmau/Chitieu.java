package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Chitieu extends AppCompatActivity implements View.OnClickListener{
    private Button bct,btn,btq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_chitieu);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled (true);
        actionBar.setDisplayShowTitleEnabled (false);
        connectview();
    }

    private void connectview() {
        bct = (Button) findViewById (R.id.bct);
        btn = (Button) findViewById (R.id.btn);
        btq = (Button) findViewById (R.id.btq);
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
//            case R.id.add:
//            {
//                // Gọi màn hình AditionSportActivity
//                break;
//            }

            case android.R.id.home:
            {
                onBackPressed ();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btq:{
                doclickbtq();
                break;
            }
            case R.id.bct:{
                break;
            }
            case R.id.btn:{
                doclickbtn ();
                break;
            }
        }
    }

    private void doclickbtq() {
        Intent in = new Intent (this,Tongquan.class);
        startActivity (in);
    }
    private void doclickbtn() {
        Intent in = new Intent (this,ThuNhap.class);
        startActivity (in);
    }
}
