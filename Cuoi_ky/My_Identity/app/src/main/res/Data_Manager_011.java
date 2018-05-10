

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king_ on 24-Apr-18.
 */

public class Data_Manager_011 extends SQLiteOpenHelper {
    private static final String Database = "Note_Data";
    private static final String Table = "Note";
    private static final String Id = "id";
    private static final String Tieude = "tieude";
    private static final String Ngay = "ngay_thang";
    private static final String Noidung = "noidung";
    private Context context;
    private String sqlQuery = "CREATE TABLE " + Table + " (" +
            Id + " integer primary key, " +
            Tieude + " TEXT, " +
            Noidung + " TEXT," +
            Ngay + " DATE)";

    public Data_Manager_011(Context context) {
        super(context, Database, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add_Note(Note_class_011 noteadd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, noteadd.getMaNote());
        values.put(Tieude, noteadd.getTieude());
        values.put(Ngay, noteadd.getDate());
        values.put(Noidung, noteadd.getNoidung());
        db.insert(Table, null, values);

        db.close();
    }

    public List<Note_class_011> getNote() {
        List<Note_class_011> list = new ArrayList<Note_class_011>();
        String select = " SELECT * FROM " + Table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(select,null);
        if (cursor.moveToFirst()) {
            do {
                Note_class_011 note_class_011 = new Note_class_011();
                note_class_011.setMaNote(cursor.getInt(0));
                note_class_011.setTieude(cursor.getString(1));
                note_class_011.setNoidung(cursor.getString(2));
                note_class_011.setDate(cursor.getString(3));
                list.add(note_class_011);

            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
    public int Update(Note_class_011 noteadd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tieude, noteadd.getTieude());
        values.put(Ngay, noteadd.getDate());
        values.put(Noidung, noteadd.getNoidung());
        return db.update(Table,values,Id+"=?", new String[]{String.valueOf(noteadd.getMaNote())});
    }
    public int Delete(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table,Id+"=?", new String[]{String.valueOf(ID)});
    }

}
