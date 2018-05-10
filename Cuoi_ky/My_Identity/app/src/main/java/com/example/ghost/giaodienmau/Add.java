package com.example.ghost.giaodienmau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;

public class Add extends AppCompatActivity implements View.OnClickListener{
    private Button bchon;
    private Button bluu;
    private EditText lydo,sotien;
    ArrayList<Money> mangMoney;
    ListviewAdapter listviewAdapter;
    SQLite sqLite;
    Dialog dialog;
    int flag = 1;
    public static final String BUNDLE = "bundle";
    public static final String LYDO = "lydo";
    public static final String SOTIEN = "sotien";
    public static final String LUACHON = "luachon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add);
        ConnectView();

    }

    private void ConnectView() {
        bchon = (Button) findViewById (R.id.bchon);
        bluu = (Button) findViewById (R.id.bluu);
        lydo = (EditText) findViewById (R.id.etldo);
        sotien = (EditText) findViewById (R.id.etmoney);

        bchon.setOnClickListener (this);
        bluu.setOnClickListener (this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId ()==R.id.bchon){
            showAlterDialog();
        }else if(v.getId ()==R.id.bluu){
            doclickluu();
        }
    }

    private void doclickluu() {

        if(lydo.getText().toString().equals("") || sotien.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(Add.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Các trường không được rỗng");
            builder.setPositiveButton("OK",null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }
        sqLite = new SQLite (Add.this);
        Money money = new Money (flag,sotien.getText ().toString ()+"",lydo.getText ().toString ()+"");
        mangMoney = new ArrayList<> ();
        sqLite.loadData (mangMoney);
        mangMoney.add (money);
        sqLite.saveData (mangMoney);
        Intent in = new Intent (Add.this,Tongquan.class);
        startActivity (in);
    }

    private void showAlterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setTitle ("Chọn ");
        builder.setCancelable (false);
        builder.setPositiveButton ("Thu", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText (Add.this,"Bạn chọn Thu",Toast.LENGTH_SHORT).show ();
                bchon.setText ("Thu");
                flag =1;
            }
        });
        builder.setNegativeButton ("Chi", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText (Add.this,"Bạn chọn Chi",Toast.LENGTH_SHORT).show ();
                bchon.setText ("Chi");
                flag = 0;
            }
        });
        AlertDialog alertDialog = builder.create ();
        alertDialog.show ();
    }

}
