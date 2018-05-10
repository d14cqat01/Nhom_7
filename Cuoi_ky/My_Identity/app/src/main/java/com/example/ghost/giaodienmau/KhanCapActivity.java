package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ghost.giaodienmau.model.KhanCap;
import com.example.ghost.giaodienmau.model.KhanCapAdapter;

import java.util.ArrayList;

public class KhanCapActivity extends AppCompatActivity {
    private ArrayList<KhanCap> khancapList;
    private KhanCapAdapter khancapAdapter;
    private ListView list_khancap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khan_cap);

        list_khancap = findViewById(R.id.list_khancap);
        khancapList = new ArrayList<>();
        KhanCap congan= new KhanCap("Công An",R.drawable.iconcongan,"113");//113
        KhanCap capcuu = new KhanCap("Cấp Cứu",R.drawable.iconcapcuu,"115");//115
        KhanCap cuuhoa =new KhanCap("Cứu Hỏa",R.drawable.iconcuuhoa,"114");//114
        KhanCap hotrotreem = new KhanCap("Hộ trợ trẻ em",R.drawable.icontreem,"18001567"); //18001567
        KhanCap hotronguoitieudung = new KhanCap("Hổ trợ người tiêu dùng",R.drawable.iconnguoitieudung,"18006838"); //1800.6838
        KhanCap tocaothucphamban = new KhanCap("Phản ánh tình trạng thực phẩm bẩn",R.drawable.iconthucpham,"0911811556");//0911.811.556
        KhanCap viettel = new KhanCap("Tổng đài hộ trợ khách hàng Viettel",R.drawable.iconviettel,"18008098");//1800 8098
        KhanCap vinaphone = new KhanCap("Tổng đài hộ trợ khách hàng Vinaphone",R.drawable.iconvinaphone,"18001091");//1800.1091

        KhanCap mobiphone = new KhanCap("Tổng đài hộ trợ khách hàng Mobiphone",R.drawable.iconmobiphone,"18001090");// 18001090

        khancapList.add(congan);
        khancapList.add(capcuu);
        khancapList.add(cuuhoa);
        khancapList.add(hotrotreem);
        khancapList.add(hotronguoitieudung);
        khancapList.add(tocaothucphamban);
        khancapList.add(viettel);
        khancapList.add(vinaphone);
        khancapList.add(mobiphone);
        khancapAdapter = new KhanCapAdapter(this,R.layout.activity_khan_cap,khancapList);
        list_khancap.setAdapter(khancapAdapter);
        list_khancap.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                KhanCap khanCap = khancapList.get(i);
                intentCallCLick(khanCap.getNumber());
                return false;
            }
        });
    }
    private void intentCallCLick(String number)
    {
        Intent intentcall = new Intent();
        intentcall.setAction(Intent.ACTION_CALL);
        intentcall.setData(Uri.parse("tel:" + number)); // set the Uri
        startActivity(intentcall);

    }
//    private void checkAndRequestPermissions() {
//        String[] permissions = new String[]{
//                Manifest.permission.CALL_PHONE,
//                Manifest.permission.SEND_SMS
//        };
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(permission);
//            }
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
//        }
//    }
}
