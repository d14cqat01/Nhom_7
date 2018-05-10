package com.example.ghost.giaodienmau;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ghost.giaodienmau.model.OfflineActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ham ket noi
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
                Intent in = new Intent(MainActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void ConnectView(){
        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById (R.id.bt2);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener (this);
    }
    public void onClick(View view) {
        if(view.getId ()==R.id.bt1)
        doClickButton();
        else if (view.getId ()==R.id.bt2)
            doClickButton2 ();
    }

    private void doClickButton2() {

        Intent in = new Intent (this,OfflineActivity.class);
        startActivity (in);
    }

    private void doClickButton(){
        Intent in = new Intent (this,LoginActivity.class);
        startActivity (in);
    }

}
