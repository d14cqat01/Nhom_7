package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Support extends AppCompatActivity {
    private String sdt;
    private TextView text_sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        text_sdt = findViewById(R.id.text_sdt);
        text_sdt.setText("(028).38.288.158");
        sdt = "02838288158";

    }
    public void onClick(View v) {
        Intent intentcall = new Intent();
        intentcall.setAction(Intent.ACTION_CALL);
        intentcall.setData(Uri.parse("tel:" + sdt)); // set the Uri
        startActivity(intentcall);
    }
}
