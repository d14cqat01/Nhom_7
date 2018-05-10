package com.example.hoans_000.diemdanhfirebase;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTen;
    Button btnThem;
    Button btnUpdate;
    Button btnDelete;
    ListView lvHangXe;
    DatabaseReference mdata;
    ArrayList<String> mangHangXe;
    int vitri = 0;
    String value;
    String key;
    String t;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mdata = database.getReference();


        Toast.makeText(this,"cham",Toast.LENGTH_SHORT).show();
        lvHangXe= (ListView) findViewById(R.id.listviewHangXe);



        mangHangXe=new ArrayList<String>();


        mdata.removeValue();

        for(int i=1;i<5;i++){

            mdata.child("array").push().setValue("value "+i);

        }

        edtTen=(EditText) findViewById(R.id.editTextTen);
        btnThem=(Button) findViewById(R.id.buttonThem);
        btnUpdate=(Button) findViewById(R.id.buttonUpdate);
        btnDelete=(Button) findViewById(R.id.buttonDelete);

        final CustomList adapter = new CustomList(this, mangHangXe);

        lvHangXe.setAdapter(adapter);

        mangHangXe.clear();

        mdata.child("array").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                mangHangXe.add(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
                size=mangHangXe.size();

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

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten=edtTen.getText().toString();

                mdata.child("array").push().setValue(ten);
                edtTen.setText("");
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t =edtTen.getText().toString();
                mangHangXe.set(vitri,t);
                edtTen.setText("");

                mdata.child("array").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if(value.equalsIgnoreCase(dataSnapshot.getValue().toString()))
                        {
                            Log.e("log","co");
                            mdata.child("array").child(dataSnapshot.getKey().toString()).setValue(""+t);
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
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mdata.child("array").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if(value.equalsIgnoreCase(dataSnapshot.getValue().toString()))
                        {
                            Log.e("log","xoa :"+dataSnapshot.getKey().toString());

                            mdata.child("array").child(dataSnapshot.getKey().toString()).removeValue();

                            mangHangXe.clear();
                            mdata.child("array").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                    mangHangXe.add(dataSnapshot.getValue().toString());
                                    adapter.notifyDataSetChanged();
                                    size=mangHangXe.size();

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
        });

        lvHangXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                edtTen.setText(mangHangXe.get(position));

                value=mangHangXe.get(position);
                Log.e("log",""+value);

                vitri=position;

            }
        });





    }
}
