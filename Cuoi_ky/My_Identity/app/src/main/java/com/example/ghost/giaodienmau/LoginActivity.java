package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by ghost on 07-Mar-18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btl;
    private Button bfpass;
    private EditText usn;
    private EditText pwd;
    private TextView thongbao;
    private HashMap<String,String> hashMap;
    DatabaseReference database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //Ham ket noi
        ConnectView ();
    }
    public void ConnectView(){
        btl = (Button) findViewById (R.id.login);
        usn = (EditText) findViewById (R.id.idusername);
        pwd = (EditText) findViewById (R.id.pass);
        thongbao = (TextView) findViewById (R.id.thongbao);
        bfpass = (Button) findViewById (R.id.bfpass);
        hashMap=new HashMap<String, String>();

        btl.setOnClickListener (this);
        bfpass.setOnClickListener (this);
    }
    public void onClick(View view) {
        final String username = usn.getText ().toString ();
        database = FirebaseDatabase.getInstance ().getReference ();
        switch (view.getId ()){
            case R.id.login:{
                Global.username=username;
                database.child ("Login").child(username).addChildEventListener (new ChildEventListener () {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            String key=dataSnapshot.getKey();
                            String value=dataSnapshot.getValue().toString();
                           hashMap.put(key,value);
                        if(hashMap.containsKey("Password")) {
                            Global.password=hashMap.get("Password");
                            doClickButton();
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                break;
            }case R.id.bfpass: {
                Intent in = new Intent (LoginActivity.this, ForgotPasswordActivity.class);
                startActivity (in);
                break;
            }
        }
//        UserAccount account = new UserAccount ("N14DCAT080","haocao");
//        database.child ("Login").child (account.getId ()).push ().setValue (account.getPwd ());

    }

    private void doClickButton(){
        String pw = pwd.getText ().toString ();

                if(pw.equals (hashMap.get("Password"))){
                    Intent in = new Intent (this,Main1_011.class);
                    in.putExtra("ID",hashMap.get("ID"));
                    Global.id=hashMap.get("ID");
                    startActivity (in);

                }
            else {
                thongbao.setText("User hoặc  Password sai");
          }


    }
}
