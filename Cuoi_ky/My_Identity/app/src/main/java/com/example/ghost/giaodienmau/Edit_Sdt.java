package com.example.ghost.giaodienmau;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.CoBan;
import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.example.ghost.giaodienmau.model.KhieuNai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edit_Sdt extends AppCompatActivity {
    private EditText edit_sdt;
    private Button button_save;
    private DatabaseReference database;
    private String id;
    private String password;
    private String username;
    private String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__sdt);
        id = Global.getId();
        password=Global.getPassword();
        username = Global.getUsername();
        edit_sdt = findViewById(R.id.edit_sdt);
        button_save=findViewById(R.id.button_save);
        database = FirebaseDatabase.getInstance().getReference();
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClickButton();
            }
        });


    }
    private void doClickButton() {
       sdt =  edit_sdt.getText().toString().trim();
       int check = checkNumberPhone(sdt);


       if (check == 2)
       {
           Toast.makeText(Edit_Sdt.this,"số điện thoại không hợp lệ!",Toast.LENGTH_SHORT).show();

       }
       else if (check == 3)
       {
           Toast.makeText(Edit_Sdt.this,"Độ dài chuỗi không hợp lệ!",Toast.LENGTH_SHORT).show();

       }
        else {


            DialogPassword();
       }


    }
    private void InsertFirebase()
    {


        database.child("Data").child(id).child("CoBan").child("sdt").setValue(sdt, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText( Edit_Sdt.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    String noidung = "Bạn đã thay đổi số điện thoại thành "+ sdt;

                    long tim=System.currentTimeMillis();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String curTime =df.format(tim);


                    //mDatabase.child("ViPham").child(Global.getId()).push().setValue(new ViPham("Danh nhau","5000000","chung minh nhan dan","xa hoi",curTime));
                    HistoryUpdate historyUpdate = new HistoryUpdate(id,noidung, curTime);
                    database.child("History").push().setValue(historyUpdate);

                    edit_sdt.setText("");

                } else {
                    Toast.makeText(Edit_Sdt.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int checkNumberPhone(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);

        if (number.length() == 10 || number.length() == 11) {
            if (number.length() == 10) {
                if (number.substring(0, 2).equals("09")) {
                    return 1;
                } else {
                    return 2;
                }
            } else
            if (number.substring(0, 2).equals("01")) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }
    public void DialogPassword()
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
                    InsertFirebase();
                    dialog.dismiss();

                }
                else
                {
                    Toast toast = Toast.makeText(Edit_Sdt.this, "Sai mật khẩu", Toast.LENGTH_SHORT);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.YELLOW);
                    toast.show();
                }
            }
        });
        dialog.show();


    }

}
