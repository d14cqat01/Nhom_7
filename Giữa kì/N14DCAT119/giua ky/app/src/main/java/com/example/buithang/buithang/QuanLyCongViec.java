package com.example.buithang.buithang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuanLyCongViec extends SQLiteOpenHelper {
    public QuanLyCongViec(Context context) {
        super(context, "quanlycongviec", null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        String sql="create table qlcv " +
                "( " +
                "_id integer primary key autoincrement, " +
                "TenCongViec text, " +
                "NoiDung text, " +
                "ThoiGianThucHien text, " +
                "ThoiGianDuKienHoanThanh text, " +
                "GhiChu text " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.rawQuery("drop table if exists qlcv", null);
    }

    public void themCongViec(CongViec c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenCongViec",c.getTenCongViec());
        values.put("NoiDung",c.getNoiDung());
        values.put("ThoiGianThucHien",c.getThoiGianThucHien());
        values.put("ThoiGianDuKienHoanThanh",c.getThoiGianHoanThanh());
        values.put("GhiChu",c.getGhiChu());
        db.insert("qlcv", null, values);
    }
    public ArrayList<CongViec> xemCongViec()
    {
      ArrayList<CongViec> ds=new ArrayList<CongViec>();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor c=db.rawQuery("select * from qlcv",null);
        if(c.moveToFirst())
    {
        do{
            int _id=c.getInt(0);
            String TenCongViec=c.getString(1);
            String NoiDung=c.getString(2);
            String ThoiGianThucHien=c.getString(3);
            String ThoiGianDuKienHoanThanh=c.getString(4);
            String GhiChu=c.getString(5);
            CongViec cv=new CongViec(TenCongViec,NoiDung,ThoiGianThucHien,ThoiGianDuKienHoanThanh,GhiChu,_id);
            ds.add(cv);
        }while (c.moveToNext());
    }
        return ds;
    }
  public ArrayList<CongViec> xem5CongViec()
    {
       int dem=0;
        ArrayList<CongViec> ds=new ArrayList<CongViec>();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor c=db.rawQuery("select * from qlcv",null);
        if(c.moveToFirst())
    {
        do{
            int _id=c.getInt(0);
            String TenCongViec=c.getString(1);
            String NoiDung=c.getString(2);
            String ThoiGianThucHien=c.getString(3);
            String ThoiGianDuKienHoanThanh=c.getString(4);
            String GhiChu=c.getString(5);
            CongViec cv=new CongViec(TenCongViec,NoiDung,ThoiGianThucHien,ThoiGianDuKienHoanThanh,GhiChu,_id);
            ds.add(cv);
            dem++;
        }while (c.moveToNext() && dem<5);
    }
        return ds;
    }



    public void suaCongViec(CongViec c)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenCongViec",c.getTenCongViec());
        values.put("NoiDung",c.getNoiDung());
        values.put("ThoiGianThucHien",c.getThoiGianThucHien());
        values.put("ThoiGianDuKienHoanThanh",c.getThoiGianHoanThanh());
        values.put("GhiChu",c.getGhiChu());
        database.update("qlcv",values,"_id=?",new String[]{c.get_id()+""});
    }
    public  void xoaCongViec(CongViec c)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.delete("qlcv","_id=?",new String[]{c.get_id()+""});
    }
}

