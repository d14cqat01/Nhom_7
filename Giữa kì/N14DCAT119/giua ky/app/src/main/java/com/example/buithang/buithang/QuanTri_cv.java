package com.example.buithang.buithang;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lephi on 11/04/2018.
 */

public class QuanTri_cv extends AppCompatActivity {
    ListView listView_quantri;
    QuanLyCongViec quanLyCongViec;
    ArrayList<CongViec> congViecs;
    EditText timkiem;
    Adapter_quanly_cv adapter_quanly_cv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantri);
        AnhXa();
        quanLyCongViec=new QuanLyCongViec(QuanTri_cv.this);
        congViecs=new ArrayList<>();
        CapNhatDuLieu();
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter_quanly_cv.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView_quantri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ThaoTac_CongViec((CongViec)adapter_quanly_cv.getItem(position));
            }
        });

    }
    public void AnhXa()
    {
        timkiem=(EditText) findViewById(R.id.timkiem);
        listView_quantri=(ListView) findViewById(R.id.listcv_quanly);
    }
    public void ThaoTac_CongViec(final CongViec congViec)
    {

        AlertDialog.Builder aBuilder=new AlertDialog.Builder(QuanTri_cv.this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_sua_xoa,null);

        final EditText ten_cv1=(EditText) view.findViewById(R.id.ten_cv_xs);
        ten_cv1.setText(congViec.getTenCongViec());

        final EditText noidung_cv1=(EditText) view.findViewById(R.id.noidung_cv_xs);
        noidung_cv1.setText(congViec.getNoiDung());

        final EditText start_cv1=(EditText) view.findViewById(R.id.start_cv_xs);
        start_cv1.setText(congViec.getThoiGianThucHien());

        final EditText end_cv1=(EditText) view.findViewById(R.id.end_cv_xs);
        end_cv1.setText(congViec.getThoiGianHoanThanh());

        final EditText ghichu_cv1=(EditText) view.findViewById(R.id.ghichu_cv_xs);
        ghichu_cv1.setText(congViec.getGhiChu());

        final Button sua=(Button) view.findViewById(R.id.sua_cv);
        final Button xoa=(Button) view.findViewById(R.id.xoa_cv);
        final Button cancel=(Button) view.findViewById(R.id.huy_tt);
        aBuilder.setView(view);
        final AlertDialog alertDialog=aBuilder.create();
        alertDialog.show();

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanLyCongViec.suaCongViec(new CongViec(ten_cv1.getText().toString(),noidung_cv1.getText().toString(),start_cv1.getText().toString(),end_cv1.getText().toString(),ghichu_cv1.getText().toString(),congViec.get_id()));
                CapNhatDuLieu();
                alertDialog.cancel();
                Toast.makeText(QuanTri_cv.this, "Đã Sữa Công Việc!", Toast.LENGTH_SHORT).show();
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanLyCongViec.xoaCongViec(congViec);
                CapNhatDuLieu();
                alertDialog.cancel();
                Toast.makeText(QuanTri_cv.this, "Đã Xóa Công Việc!", Toast.LENGTH_SHORT).show();
            }
        });
          cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }
    public void CapNhatDuLieu()
    {
        congViecs=quanLyCongViec.xemCongViec();
        adapter_quanly_cv=new Adapter_quanly_cv(congViecs,QuanTri_cv.this);
        listView_quantri.setAdapter(adapter_quanly_cv);
    }
}
