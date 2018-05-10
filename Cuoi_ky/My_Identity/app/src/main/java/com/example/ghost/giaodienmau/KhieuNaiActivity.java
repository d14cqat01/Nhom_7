package com.example.ghost.giaodienmau;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.example.ghost.giaodienmau.model.KhieuNai;
import com.example.ghost.giaodienmau.model.LaySo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KhieuNaiActivity extends AppCompatActivity {

    private EditText edit_ten;
    private EditText edit_thanhpho;
    private EditText edit_quan;
    private EditText edit_diachi;
    private EditText edit_noidung;
    private Button button_gui;
    private String ID;
    private String password;
    private String username;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tocao_khieunai);
        ID = Global.getId();
        username = Global.getUsername();
        password = Global.getPassword();
        Toast.makeText(KhieuNaiActivity.this, ID, Toast.LENGTH_SHORT).show();
        edit_ten = findViewById(R.id.edit_ten);
        edit_thanhpho = findViewById(R.id.edit_thanhpho);
        edit_quan = findViewById(R.id.edit_quan);
        edit_diachi = findViewById(R.id.edit_diachi);
        edit_noidung = findViewById(R.id.edit_noidung);
        button_gui = findViewById(R.id.button_gui);
        database = FirebaseDatabase.getInstance().getReference();
        button_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent in = new Intent(KhieuNaiActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void doClickButton() {
        String ten = edit_ten.getText().toString().trim();
        String thanhpho = edit_thanhpho.getText().toString().trim();
        String quan = edit_quan.getText().toString().trim();
        String diachi = edit_diachi.getText().toString().trim();
        String noidung = edit_noidung.getText().toString().trim();
        KhieuNai khieunai = new KhieuNai(ten, thanhpho, quan, diachi, noidung, ID);
        if (!ten.isEmpty() && !thanhpho.isEmpty() && !quan.isEmpty() && !diachi.isEmpty() && !noidung.isEmpty()) {
            DialogPassword(khieunai);
        } else {
            Toast.makeText(KhieuNaiActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }


    }

    private void InsertFirebase(final KhieuNai khieunai) {


        database.child("KhieuNai").push().setValue(khieunai, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(KhieuNaiActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();

                    long tim=System.currentTimeMillis();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String curTime =df.format(tim);


                    //mDatabase.child("ViPham").child(Global.getId()).push().setValue(new ViPham("Danh nhau","5000000","chung minh nhan dan","xa hoi",curTime));
                    HistoryUpdate historyUpdate = new HistoryUpdate(ID,"Bạn đã gửi khiếu nại về cá nhân/tổ chức : "+edit_ten.getText(), curTime);
                    database.child("History").push().setValue(historyUpdate);

                    edit_ten.setText(null);
                    edit_thanhpho.setText(null);
                    edit_quan.setText(null);
                    edit_diachi.setText(null);
                    edit_noidung.setText(null);

                } else {
                    Toast.makeText(KhieuNaiActivity.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public void DialogPassword(final KhieuNai khieunai)
    {
        final Dialog dialog =new Dialog(this);
        dialog.setContentView(R.layout.dialog_password);

        final EditText edit_password = dialog.findViewById(R.id.edit_password);
        Button button_xacnhan= dialog.findViewById(R.id.button_xacnhan);

        button_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edit_password.getText().toString().trim();
                if (pass.equalsIgnoreCase(password))
                {
                    InsertFirebase(khieunai);
                    dialog.dismiss();

                }
                else
                {

                    Toast toast = Toast.makeText(KhieuNaiActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.YELLOW);
                    toast.show();
                }
            }
        });
        dialog.show();


    }

}
