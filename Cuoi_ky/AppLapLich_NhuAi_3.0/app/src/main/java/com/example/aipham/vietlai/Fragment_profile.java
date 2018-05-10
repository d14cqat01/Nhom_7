package com.example.aipham.vietlai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_profile extends AppCompatActivity implements View.OnClickListener {
    private Button button_signup;
    private TextView textView;
    private DatabaseReference database;
    private EditText edit_mail;
    private EditText edit_password;
    private Button login;
    ArrayList<Account> listAccount;
    HashMap<String,Object> a = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        connectview();
        database = FirebaseDatabase.getInstance().getReference().child("applaplich");
        Toast.makeText(Fragment_profile.this,database.getKey(),Toast.LENGTH_LONG).show();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = edit_mail.getText().toString();

                final String password = edit_password.getText().toString();

                if (!mail.isEmpty() && !password.isEmpty()) {

                    database.child("Login").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(Fragment_profile.this,mail,Toast.LENGTH_LONG).show();
                            Account account = dataSnapshot.getValue(Account.class);
                            a.put(dataSnapshot.getKey(), dataSnapshot.getValue());
//                            if(account.getEmail().equalsIgnoreCase(mail)){
//                                Toast.makeText(Fragment_profile.this,"đúng",Toast.LENGTH_LONG).show();
//                            }
//                            else
//                                Toast.makeText(Fragment_profile.this,"sai",Toast.LENGTH_LONG).show();
                            textView.append(account.getEmail());

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

                } else {
                    Toast.makeText(Fragment_profile.this,"đúng",Toast.LENGTH_LONG).show();
//
                }
            }
        });


    }

    private void connectview() {
        button_signup = (Button) findViewById(R.id.button_goto_signup);
        edit_mail = findViewById(R.id.mail_login);
        edit_password = findViewById(R.id.pwd_login);
        login = findViewById(R.id.button_goto_login);
        textView = (TextView) findViewById(R.id.signup_text);
        button_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_goto_signup) {
            Intent in = new Intent(this, SignUp.class);
            startActivity(in);
        }
    }
}

