package com.example.ghost.giaodienmau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;


/**
 * Created by IT on 1/5/2018.
 */

public class SQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_thuchi";
    private static final int DB_VERSION = 1;

    private static final String TB_NAME = "tb_quanli";
    private static final String TB_COL_ID = "id";
    private static final String TB_COL_MUCDICH = "mucdich";
    private static final String TB_COL_SOTIEN = "sotien";
    private static final String TB_COL_LYDO = "lydo";


    public SQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TB_NAME + "( " +
                TB_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_COL_MUCDICH + " TEXT, " +
                TB_COL_SOTIEN + " TEXT, " +
                TB_COL_LYDO + " TEXT )"
        );
    }

    public void loadData(ArrayList<Money> arrPersion){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TB_NAME,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(TB_COL_ID));
                int mucdich = cursor.getInt (cursor.getColumnIndex(TB_COL_MUCDICH));
                String sotien = cursor.getString(cursor.getColumnIndex(TB_COL_SOTIEN));
                String lydo = cursor.getString(cursor.getColumnIndex(TB_COL_LYDO));
                arrPersion.add(new Money (id,mucdich,sotien,lydo));
                Log.d("DB",id+"");
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void saveData(ArrayList<Money> personArrayList){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TB_NAME);
        db.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME = '" + TB_NAME + "'");
        for (Money nv : personArrayList){
            ContentValues values = new ContentValues();
            values.put(TB_COL_MUCDICH,nv.getMucdich ());
            values.put(TB_COL_SOTIEN, nv.getSotien ());
            values.put(TB_COL_LYDO, nv.getLydo ());
            db.insert(TB_NAME,null,values);

        }
        db.close();
    }

    public void delete(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_NAME,TB_COL_ID + " =?",new String[]{id});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
