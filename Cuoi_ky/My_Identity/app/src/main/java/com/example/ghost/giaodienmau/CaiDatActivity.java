package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class CaiDatActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton bGThieu;
    private ImageButton bRate;
    private ImageButton bHTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);
        ConnectView();
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
                Intent in = new Intent(CaiDatActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    public void ConnectView(){
        bGThieu = (ImageButton) findViewById(R.id.bGThieu);
        bRate= (ImageButton) findViewById(R.id.bRate);
        bHTro = (ImageButton) findViewById(R.id.bHTro);
        bHTro.setOnClickListener( this);
        bGThieu.setOnClickListener( this);
        bRate.setOnClickListener(this);
    }


    public void onClick(View v) {
        if(v.getId()==R.id.bGThieu){
            doclick1();
        }
        else if (v.getId()==R.id.bRate){
            doclick2();
        }

        else if (v.getId()==R.id.bHTro){
            doclick4();
        }
    }

    private void doclick4() {
        Intent in = new Intent(CaiDatActivity.this,Support.class);
        startActivity(in);
    }

    private void doclick2() {
        Intent in = new Intent(CaiDatActivity.this,Rating.class);
        startActivity(in);
    }

    private void doclick1() {
        Intent in = new Intent(CaiDatActivity.this,About.class);
        startActivity(in);
    }


}
