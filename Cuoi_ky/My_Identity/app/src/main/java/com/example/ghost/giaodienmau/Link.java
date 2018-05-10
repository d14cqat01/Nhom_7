package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Link extends AppCompatActivity implements View.OnClickListener{
    private Button bFb;
    private Button bGmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        ConnectView();
    }
    private void ConnectView() {
        bFb = (Button) findViewById(R.id.bFb);
        bFb.setOnClickListener( this);
        bGmail = (Button) findViewById(R.id.bGmail);
        bGmail.setOnClickListener( this);
    }


    public void onClick(View v) {
        if (v.getId()== R.id.bFb){
            doclick1();
        }
        else if (v.getId()==R.id.bGmail){
            doclick2();
        }
    }

    private void doclick1() {
        Intent in = new Intent(Link.this,Facebook.class);
        startActivity(in);
    }

    private void doclick2() {
        Intent in = new Intent(Link.this,Gmail.class);
        startActivity(in);
    }
}
