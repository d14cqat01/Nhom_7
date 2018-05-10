package com.example.ghost.giaodienmau;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.DanhGia;
import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.example.ghost.giaodienmau.model.KhieuNai;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Rating extends AppCompatActivity {
    private DatabaseReference database;
    private String username;
    RatingBar ratingBar;
    private EditText et;
    Dialog dialog;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        //        connectview();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        et = findViewById(R.id.et);
        button = (Button) findViewById(R.id.button);
        database = FirebaseDatabase.getInstance().getReference();
        username = Global.getUsername();
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.MAGENTA);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(Rating.this, "Stars :" + rating, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClickButton();
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
                Intent in = new Intent(Rating.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    private void doClickButton() {
       String sao = String.valueOf(ratingBar.getRating());
       String noidung = et.getText().toString();
       DanhGia danhgia = new DanhGia(sao,noidung,username);
       InsertFirebase(danhgia,noidung);
    }

    private void InsertFirebase(final DanhGia danhgia, String noidung) {
        if (!noidung.isEmpty() ) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Rating.this);
            alertDialogBuilder.setMessage("Bán muốn gửi thông tin này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    database.child("DanhGia").child(username).setValue(danhgia, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(Rating.this, "Cám ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show();
                                et.setText(null);

                            } else {
                                Toast.makeText(Rating.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();


        } else {
            Toast.makeText(Rating.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}