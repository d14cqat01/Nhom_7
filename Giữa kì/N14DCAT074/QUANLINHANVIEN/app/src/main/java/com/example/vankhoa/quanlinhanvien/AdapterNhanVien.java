package com.example.vankhoa.quanlinhanvien;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Van Khoa on 4/14/2018.
 */

public class AdapterNhanVien extends BaseAdapter{

    Context context;
    ArrayList<NhanVien> list;

    public AdapterNhanVien(Context context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
    }
    //    public AdapterNhanVien( Context context,ArrayList<NhanVien> list) {
//        this.context = context;
//        this.list = list;
//    }


    @Override
    public int getCount() {


//        return list.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View row = inflater.inflate(R.layout.listview, null);

       // ImageView imghinhdaidien = (ImageView) row.findViewById(R.id.imghinhdaidien);
        TextView txtid = (TextView) row.findViewById(R.id.txtid);
        TextView txtten = (TextView) row.findViewById(R.id.txtten);
        TextView txtsdt = (TextView) row.findViewById(R.id.txtsdt);
        Button btnsua = (Button) row.findViewById(R.id.btnsua);
        Button btnxoa = (Button) row.findViewById(R.id.btnxoa);

        NhanVien nhanvien = list.get(i);
       txtid.setText(nhanvien.id+"");
       txtten.setText(nhanvien.ten);
       txtsdt.setText(nhanvien.sdt);
//       Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(nhanvien.anh,0,nhanvien.anh.length);
//       imghinhdaidien.setImageBitmap(bmHinhDaiDien);
        return row;
    }
}
