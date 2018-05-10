package com.example.aipham.truyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edText;
    Button bThem, bSua, bXoa;
    ListView lvTruyen;
    ArrayList<String> arrayTruyen;
    DatabaseReference mData;
    String chon="";
    int vitri = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //firebase
        edText = (EditText) findViewById(R.id.editText);
        bThem = (Button) findViewById(R.id.btThem);
        bSua = (Button) findViewById(R.id.btSua);
        bXoa = (Button) findViewById(R.id.btXoa);
        lvTruyen = (ListView) findViewById(R.id.listTruyen);

        mData = FirebaseDatabase.getInstance().getReference();
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            //Đổ dữ liệu xuống
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayTruyen = new ArrayList<String>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    arrayTruyen.add(ds.getKey().toString());
                    Log.d("tmppp", ds.getKey().toString());
                }
                final ArrayAdapter adapterTruyen = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayTruyen);
                //Gọi lvTruyen.setAdapter, truyền biến adapterTruyen
                lvTruyen.setAdapter(adapterTruyen);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Ánh xạ

        //Khởi tạo mảng arrayTruyen


        //Thêm phần tử vào mảng arrayTruyen để nó có dữ liệu
//        arrayTruyen.add("Cho anh nhìn về em");
//        arrayTruyen.add("Nơi nào đông ấm");
//        arrayTruyen.add("Tháng sáu trời xanh lam");
//        arrayTruyen.add("Mây trên đồng bay mãi");
//        arrayTruyen.add("Nợ em một đời hạnh phúc");
//        arrayTruyen.add("Chỉ vì phút giây được gặp em");

        //Goi ArrayAdapter, dat ten bien, khoi tao, 3 tham so (context, kiểu layout de do du lieu ra, mang de lay du lieu ra)


        //Khai bao su kien cho btThem
        bThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Để thêm vào, lấy nội dung từ trường edittext
                //Khai báo biến string để lưu dữ liệu từ edittext
                String them = edText.getText().toString();
                //Thêm vào mảng
//                arrayTruyen.add(them);
                mData.child(them).setValue("a");
//                //Cập nhật adapter
//                adapterTruyen.notifyDataSetChanged();
                //Trả trường edittext về null
                edText.setText("");
            }
        });

        //Khai bao su kien cho btSua
        //Tạo sự kiện click vào listview
        lvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edText.setText(arrayTruyen.get(position));
                vitri = position;
                chon = arrayTruyen.get(position);
            }
        });
        //Làm việc với btSua
        bSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy nội dung đã chỉnh sửa
//                String xoa = edText.getText().toString();
                mData.child(chon).removeValue();
//                Toast.makeText(Admin.this, "Delete successfully ", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this,"Đã xóa ",Toast.LENGTH_SHORT).show();
//              arrayTruyen.remove(vitri);
//              //Cập nhật adapter
////                adapterTruyen.notifyDataSetChanged();
//                //Trả trường edittext về null
//                edText.setText("");
//                arrayTruyen.set(vitri,sua);
                String them = edText.getText().toString();
                //Thêm vào mảng
//                arrayTruyen.add(them);
                mData.child(them).setValue("a");
//                //Cập nhật adapter
//                adapterTruyen.notifyDataSetChanged();
                //Trả trường edittext về null
                Toast.makeText(MainActivity.this,"Đã sửa ",Toast.LENGTH_SHORT).show();
//                //Cập nhật adapter
//                adapterTruyen.notifyDataSetChanged();
                //Trả trường edittext về null
                edText.setText("");
            }
        });

        //btXoa
        bXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Báo đã xóa
                String xoa = edText.getText().toString();
                mData.child(xoa).removeValue();
//                Toast.makeText(Admin.this, "Delete successfully ", Toast.LENGTH_SHORT).show();
              Toast.makeText(MainActivity.this,"Đã xóa ",Toast.LENGTH_SHORT).show();
//              arrayTruyen.remove(vitri);
//              //Cập nhật adapter
////                adapterTruyen.notifyDataSetChanged();
//                //Trả trường edittext về null
                edText.setText("");
            }
        });
    }
}
