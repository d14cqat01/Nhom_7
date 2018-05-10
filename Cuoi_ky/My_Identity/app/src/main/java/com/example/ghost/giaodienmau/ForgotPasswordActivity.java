package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ghost on 1/1/2018.
 */
public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bThaydoi;
    private EditText et0,et1,et2;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forget_password);

        ConnectView();
    }

    private void ConnectView() {
        bThaydoi = (Button) findViewById (R.id.bThaydoi);
        et0 = (EditText) findViewById (R.id.et0);
        et1 = (EditText) findViewById (R.id.et1);
        et2 = (EditText) findViewById (R.id.et2);

        bThaydoi.setOnClickListener (this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId ()==R.id.bThaydoi){
            Xuly();
        }
    }

    private void Xuly() {
        database = FirebaseDatabase.getInstance ().getReference ();
        Map <String , Object> update = new HashMap<String,Object> ();
        String username = et0.getText ().toString ()+"";
        String pass1 = et1.getText ().toString ()+"";
        String pass2 = et2.getText ().toString ()+"";
        update.put ("Password",pass1);
        String id;
        if(pass1.equals (pass2)){
            database.child ("Login").child (username).updateChildren (update);
            Intent in = new Intent (ForgotPasswordActivity.this,LoginActivity.class);
            startActivity (in);

        }else Toast.makeText (ForgotPasswordActivity.this,"Mật Khẩu không trùng nhau",Toast.LENGTH_SHORT).show ();
    }


}
