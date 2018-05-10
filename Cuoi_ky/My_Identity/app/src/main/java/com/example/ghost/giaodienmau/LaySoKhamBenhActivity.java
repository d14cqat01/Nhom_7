package com.example.ghost.giaodienmau;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.example.ghost.giaodienmau.model.KhieuNai;
import com.example.ghost.giaodienmau.model.LaySo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaySoKhamBenhActivity extends AppCompatActivity {
    private EditText edit_ten;
    private EditText edit_bhyt;
    private EditText edit_cmnd;
    private Spinner spinner;
    private Spinner spinner1;
    private Button button_gui;
    private DatabaseReference database;
    private List<String> listBV;
    private String id;
    private String password;
    private String hoten;
    private String sobhyt;
    private String socmnd;
    private ArrayAdapter<String> adapter2;
    private int number;
    private String so1;
    private int so = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_so_kham_benh);
        edit_ten = findViewById(R.id.edit_ten);
        edit_bhyt = findViewById(R.id.edit_bhyt);
        edit_cmnd = findViewById(R.id.edit_cmnd);
        spinner= findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        button_gui = findViewById(R.id.button_gui);
        listBV = new ArrayList<>();
        id = Global.getId();
        password =Global.getPassword();
        database = FirebaseDatabase.getInstance().getReference();

        database.child("Data").child(id).child("CoBan").child("hoten").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hoten = dataSnapshot.getValue().toString();
                edit_ten.setText(hoten);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.child("Data").child(id).child("BaoHiem").child("so").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               sobhyt = dataSnapshot.getValue().toString();
                edit_bhyt.setText(sobhyt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.child("Data").child(id).child("CanCuoc").child("so").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               socmnd = dataSnapshot.getValue().toString();
                edit_cmnd.setText(socmnd);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Phan spinner
        List<String> list1 = new ArrayList<>();
        list1.add("Khám BHYT");
        list1.add("Khám dịch vụ");
        ArrayAdapter<String> adapter1 =new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(LaySoKhamBenhActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // Phan spinner1

        adapter2 =new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,listBV);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        database.child("CSYT").child("CoSo").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listBV.add(dataSnapshot.getValue().toString());
                spinner1.setAdapter(adapter2);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(LaySoKhamBenhActivity.this, spinner1.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                        number = i;
                        database.child("CSYT").child("STT").child(String.valueOf(number)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                so1 = dataSnapshot.getValue().toString();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
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

        button_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClickButton();
            }
        });



    }
    private void doClickButton() {
        String ten = edit_ten.getText().toString().trim();
        String bhyt = edit_bhyt.getText().toString().trim();
        String cmnd = edit_cmnd.getText().toString().trim();
        String dichvu = spinner.getSelectedItem().toString();
        String benhvien = spinner1.getSelectedItem().toString();

        LaySo layso = new LaySo(so,ten,bhyt,cmnd,dichvu,benhvien,id);
        if(!ten.isEmpty() && !cmnd.isEmpty())
        {
            if(bhyt.isEmpty())
            {
                if(dichvu.equalsIgnoreCase("Khám BHYT"))
                {
                    Toast.makeText(LaySoKhamBenhActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(dichvu.equalsIgnoreCase("Khám dịch vụ"))
                {
                    DialogPassword(layso);
                }
            }
            else
            {
                DialogPassword(layso);
            }
        }
        else
        {
            Toast.makeText(LaySoKhamBenhActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }

    private void InsertFirebase(LaySo layso)
    {

        so = Integer.parseInt(so1) +1;
        database.child("CSYT").child("STT").child(String.valueOf(number)).setValue(so);
        layso.setSo(so);
        database.child("CSYT").child("LaySo").child(String.valueOf(number)).push().setValue(layso, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    String mes = "Số của bạn là : " + so ;
                    Toast.makeText(LaySoKhamBenhActivity.this, mes, Toast.LENGTH_SHORT).show();

                    long tim=System.currentTimeMillis();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String curTime =df.format(tim);


                    //mDatabase.child("ViPham").child(Global.getId()).push().setValue(new ViPham("Danh nhau","5000000","chung minh nhan dan","xa hoi",curTime));
                    HistoryUpdate historyUpdate = new HistoryUpdate(id,"Bạn lấy số khám bệnh tại : "+ spinner1.getSelectedItem().toString(), curTime);
                    database.child("History").push().setValue(historyUpdate);


                } else {
                    Toast.makeText(LaySoKhamBenhActivity.this, "Không thể lấy số", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void DialogPassword(final LaySo layso)
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
                    InsertFirebase(layso);
                    dialog.dismiss();

                }
                else
                {

                    Toast toast = Toast.makeText(LaySoKhamBenhActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.YELLOW);
                    toast.show();
                }
            }
        });
        dialog.show();


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
                Intent in = new Intent(LaySoKhamBenhActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
