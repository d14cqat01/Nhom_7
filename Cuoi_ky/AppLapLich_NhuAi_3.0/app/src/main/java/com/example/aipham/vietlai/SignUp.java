package com.example.aipham.vietlai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private DatabaseReference database;
    private EditText edit_mail,edit_password;
    private Button dangky;
   private Account acc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edit_mail = findViewById(R.id.mail_signup);
        edit_password = findViewById(R.id.pwd_signup);
        dangky = findViewById(R.id.button_signup);
        database = FirebaseDatabase.getInstance().getReference();

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edit_mail.getText().toString();
                String password = edit_password.getText().toString();
                if(!mail.isEmpty() && !password.isEmpty() )
                {
                    Account account =new  Account(mail,password);
                   database.child("Login").push().setValue(account, new DatabaseReference.CompletionListener() {
                       @Override
                       public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                           if(databaseError!=null)
                           {
                               Toast.makeText(SignUp.this,"Gui thanh cong !",Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Toast.makeText(SignUp.this,"Gui that bai !",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }
                else
                {
                    Toast.makeText(SignUp.this,"Ban chua nhap du thong tin !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
