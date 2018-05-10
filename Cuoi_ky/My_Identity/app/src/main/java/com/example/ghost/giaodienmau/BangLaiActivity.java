package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ghost.giaodienmau.model.Global;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BangLaiActivity extends AppCompatActivity {
    private String ID;
    private DatabaseReference database;
    private TextView text_so,text_hoten,text_ngaysinh,text_quoctich,text_diachi,text_hang,text_giatri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banglai);
        ID = Global.getId();
        text_so =  findViewById(R.id.text_so);
        text_hoten =  findViewById(R.id.text_hoten);
        text_ngaysinh =  findViewById(R.id.text_ngaysinh);
        text_quoctich =  findViewById(R.id.text_quoctich);
        text_diachi =  findViewById(R.id.text_diachi);
        text_hang=  findViewById(R.id.text_hang);
        text_giatri =  findViewById(R.id.text_giatri);
        database = FirebaseDatabase.getInstance().getReference().child("Data").child(ID);

        database.child("CoBan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                switch (dataSnapshot.getKey().toString())
                {

                    case "hoten":
                        text_hoten.setText(dataSnapshot.getValue().toString());
                        break;
                    case "ngaysinh":
                        text_ngaysinh.setText(dataSnapshot.getValue().toString());
                        break;
                    case "noithuongtru":
                        text_diachi.setText(dataSnapshot.getValue().toString());
                        break;
                    case "quoctich":
                        text_quoctich.setText(dataSnapshot.getValue().toString());
                        break;


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

        database.child("BangLai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                switch (dataSnapshot.getKey().toString())
                {
                    case "hang":
                        text_hang.setText(dataSnapshot.getValue().toString());
                        break;
                    case "hansudung":
                        text_giatri.setText(dataSnapshot.getValue().toString());
                        break;

                    case "so":
                        text_so.setText(dataSnapshot.getValue().toString());
                        break;

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

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khancap, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.khancap: {
                Intent in = new Intent(BangLaiActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
