package com.example.hao.anhhao;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText id_name;
    private EditText id_addr;
    private EditText id_phone;
    private Button butt_save;
    private Spinner id_spin;
    private ListView list_nhahang;
    private ArrayList<NhaHang> nhaHangList;
    private NhaHangAdapter nhahangAdapter;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_name = findViewById(R.id.id_name);
        id_addr = findViewById(R.id.id_addr);
        id_phone = findViewById(R.id.id_phone);
        id_spin = findViewById(R.id.id_spin);
        butt_save = findViewById(R.id.butt_save);
        list_nhahang = findViewById(R.id.list_nhahang);
        nhaHangList =new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference();
        List<String> list = new ArrayList<>();
        list.add("Việt Nam");
        list.add("Nước Ngoài");
        ArrayAdapter<String> adapter =new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        id_spin.setAdapter(adapter);
        id_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, id_spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        nhahangAdapter = new NhaHangAdapter(this,R.layout.activity_main,nhaHangList);
        list_nhahang.setAdapter(nhahangAdapter);


        butt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    doClickButton();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NhaHang nhahang = dataSnapshot.getValue(NhaHang.class);
                nhaHangList.add(nhahang);
                nhahangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                NhaHang nhahang = dataSnapshot.getValue(NhaHang.class);
                ChangeList(nhahang);
                nhahangAdapter.notifyDataSetChanged();

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
        list_nhahang.setOnItemLongClickListener(new ItemLongClickRemove());
        list_nhahang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaHang nhahang = nhaHangList.get(position);
                id_name.setText(nhahang.getHoten());
                id_addr.setText(nhahang.getDiachi());
                id_phone.setText(nhahang.getPhone());

                if(nhahang.getQuocgia().compareTo("Việt Nam")==0) {
                    id_spin.setSelection(0);
                }
                else {id_spin.setSelection(1);}

            }
        });

    }


    private void doClickButton() throws InterruptedException {
        String hoten = id_name.getText().toString().trim();
        String diachi= id_addr.getText().toString();
        String sdt= id_phone.getText().toString();
        String quocgia = id_spin.getSelectedItem().toString();

        NhaHang nhahang = new NhaHang(hoten,diachi,sdt,quocgia);

        InsertFirebase(nhahang,hoten,diachi,sdt);
        id_name.setText(null);
        id_addr.setText(null);
        id_phone.setText(null);

    }
    private void InsertFirebase(NhaHang nhahang,String hoten,String diachi,String sdt)
    {
        if(!hoten.isEmpty() && !diachi.isEmpty()  && !sdt.isEmpty()) {
            database.child(nhahang.getDiachi()).setValue(nhahang, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }




    }
    private void ChangeList(NhaHang nhahang)
    {

        for(int i=0;i<nhaHangList.size();i++)
        {
            if(nhaHangList.get(i).getDiachi().compareToIgnoreCase(nhahang.getDiachi())==0)
            {
                nhaHangList.remove(i);
            }

        }
        nhaHangList.add(nhahang);

    }


    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NhaHang nhanvien = nhaHangList.get(position);
                    database.child(nhanvien.getDiachi()).removeValue();
                    nhaHangList.remove(nhanvien);
                    nhahangAdapter.notifyDataSetChanged();

                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}
