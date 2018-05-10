package com.example.ghost.giaodienmau;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edit_mail extends AppCompatActivity {
    private EditText edit_tenemail;
    private EditText edit_duoiemail;
    private Button button_save;
    private DatabaseReference database;
    private String id;
    private String password;
    private String username;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mail);
        edit_tenemail = findViewById(R.id.edit_tenemail);
        edit_duoiemail = findViewById(R.id.edit_duoiemail);
        button_save = findViewById(R.id.button_save);
        id = Global.getId();
        password=Global.getPassword();
        username =Global.getUsername();
        database = FirebaseDatabase.getInstance().getReference();
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClickButton();
            }
        });
    }
    private void doClickButton() {
        email = edit_tenemail.getText().toString().trim() + "@" +edit_duoiemail.getText().toString().trim();
        boolean check = checkEmail(email);

        if (check == true) {
            DialogPassword();
        } else {

            Toast.makeText(Edit_mail.this, "Email của bạn chưa hợp lệ!", Toast.LENGTH_SHORT).show();

        }
    }



    private boolean checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }

    }
    private void InsertFirebase()
    {


        database.child("Data").child(id).child("CoBan").child("email").setValue(email, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText( Edit_mail.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    String noidung = "Bạn đã thay đổi số điện thoại thành "+ email;

                    long tim=System.currentTimeMillis();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String curTime =df.format(tim);


                    //mDatabase.child("ViPham").child(Global.getId()).push().setValue(new ViPham("Danh nhau","5000000","chung minh nhan dan","xa hoi",curTime));
                    HistoryUpdate historyUpdate = new HistoryUpdate(id,noidung, curTime);
                    database.child("History").push().setValue(historyUpdate);

                    edit_tenemail.setText("");
                    edit_duoiemail.setText("");


                } else {
                    Toast.makeText(Edit_mail.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

                    Toast toast = Toast.makeText(Edit_mail.this, "Sai mật khẩu", Toast.LENGTH_SHORT);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.YELLOW);
                    toast.show();
                }
            }
        });
        dialog.show();


    }
}
