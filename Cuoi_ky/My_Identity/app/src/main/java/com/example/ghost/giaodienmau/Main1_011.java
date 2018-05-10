package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class Main1_011 extends AppCompatActivity {

    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    HinhAd_Main1 adapter;
    LoadTT_Adapter adapter2;
    ArrayList<Thong_tin011> thong_tin011;
    ListView lv;
    public Thong_tin011 tt;
    TextView txtMore;
    String id;
    ImageView imageView;
    HashMap<String,String> hashMap;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        addControl();
        addEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khancap, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.khancap: {
                Intent in = new Intent(Main1_011.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    private void addEvent() {
        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });
    }

    private void xuly() {
        Intent intent=new Intent(Main1_011.this,Main2_011.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }

    private void addControl() {
        Intent intent=getIntent();
        imageView=(ImageView)findViewById(R.id.imgID);
        id=intent.getStringExtra("ID");
        database = FirebaseDatabase.getInstance ().getReference ();
        hashMap=new HashMap<String, String>();
        tt=new Thong_tin011();
        loaddata();
        gvHinh=(GridView)findViewById(R.id.gvHinh2);
        dsHinh=new ArrayList<>();
        adapter=new HinhAd_Main1(Main1_011.this,R.layout.item1_main1,dsHinh);
        dsHinh.add(R.drawable.name);
        dsHinh.add(R.drawable.cmnd);
        dsHinh.add(R.drawable.date);
        dsHinh.add(R.drawable.place);
        dsHinh.add(R.drawable.yte);
        dsHinh.add(R.drawable.mail);
        dsHinh.add(R.drawable.telephone);
        gvHinh.setAdapter(adapter);

        lv=(ListView)findViewById(R.id.lvHinh2);
        thong_tin011=new ArrayList<>();
        thong_tin011.add(tt);
        adapter2=new LoadTT_Adapter(Main1_011.this, R.layout.item2_main1,thong_tin011);
        lv.setAdapter(adapter2);
        txtMore=(TextView)findViewById(R.id.txtMore);

    }

    private void loaddata() {
        database.child("Data").child(id).child("BaoHiem").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String key=dataSnapshot.getKey();
                        String value=dataSnapshot.getValue().toString();
                        if(key.equals("so"))
                        {
                            tt.setSo_BH(value);
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
        database.child("Data").child(id).child("CanCuoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key=dataSnapshot.getKey();
                String value=dataSnapshot.getValue().toString();
                if(key.equals("so"))
                {
                    tt.setCMND(value);
                }
                else if(key.equals("quequan"))
                {
                    tt.setQueQuan(value);
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
        database.child("Data").child(id).child("CoBan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key=dataSnapshot.getKey();
                String value=dataSnapshot.getValue().toString();

                if(key.equals("email"))
                {
                    tt.setEmail(value);
                }
                else if(key.equals("hoten"))
                {
                    tt.setName(value);
                }
                else if(key.equals("ngaysinh"))
                {
                    tt.setNgay(value);
                }
                else if(key.equals("sdt"))
                {
                    tt.setSo_dt(value);
                }
                else if(key.equals("imageID"))
                {
                    Picasso.get().load(value).into(imageView);
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key=dataSnapshot.getKey();
                String value=dataSnapshot.getValue().toString();

                if(key.equals("email"))
                {
                    tt.setEmail(value);
                }
                else if(key.equals("sdt"))
                {
                    tt.setSo_dt(value);
                }
                adapter2.notifyDataSetChanged();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==21 && resultCode==22)
        {
            String sdt=data.getStringExtra("postion");
            if(sdt==null){
                Toast.makeText(this,"Sua SDT  không thành công",Toast.LENGTH_SHORT).show();
            }
            else {
                tt.setSo_dt(sdt);
                adapter2.notifyDataSetChanged();
            }
        }
        else if(requestCode==11 && resultCode==12)
        {
            String k=data.getStringExtra("pas");
            if(k==null)
            {
                Toast.makeText(this,"Sua Email  không thành công",Toast.LENGTH_SHORT).show();
            }else {
                tt.setEmail(k);
                adapter2.notifyDataSetChanged();
            }

        }
    }
}
