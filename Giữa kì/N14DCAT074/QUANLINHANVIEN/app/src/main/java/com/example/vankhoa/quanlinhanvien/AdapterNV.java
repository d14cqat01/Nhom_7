package com.example.vankhoa.quanlinhanvien;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * Created by Van Khoa on 4/15/2018.
 */

public class AdapterNV extends ArrayAdapter {
    Context context;
    ArrayList<NhanVien> list;
    int layout;

    public AdapterNV(@NonNull Context context, int resource, @NonNull ArrayList<NhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, parent, false);

        TextView txtid = (TextView) convertView.findViewById(R.id.txtid);
        TextView txtten = (TextView) convertView.findViewById(R.id.txtten);
        TextView txtsdt = (TextView) convertView.findViewById(R.id.txtsdt);
        Button btnsua = (Button) convertView.findViewById(R.id.btnsua);
        Button btnxoa = (Button) convertView.findViewById(R.id.btnxoa);
//        ImageView imghinhdaidien = (ImageView) row.findViewById(R.id.imghinhdaidien);
//        ImageView imghinhdaidien = (ImageView) row.findViewById(R.id.imghinhdaidien);

        final NhanVien nhanVien = list.get(position);
        txtid.setText(nhanVien.id + "");
        txtten.setText(nhanVien.ten);
        txtsdt.setText(nhanVien.sdt);

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có chắc chắn muốn xoá nhân viên này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(nhanVien.id);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return convertView;
    }

    private void delete(int idNhanVien) {
        SQLiteDatabase database = Database.initDatabase((Activity) context, "quanlinhanvien.sqlite");
        database.delete("NhanVien", "ID = ?", new String[]{idNhanVien + ""});
        list.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);

            list.add(new NhanVien(id, ten, sdt, anh));
        }
        notifyDataSetChanged();
    }
}
