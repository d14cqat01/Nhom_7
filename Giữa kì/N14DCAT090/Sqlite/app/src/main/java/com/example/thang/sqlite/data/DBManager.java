package com.example.thang.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.example.thang.sqlite.module.Student;

/**
 * Created by thang on 19-Apr-18.
 */

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME ="students_manager";
    private static final String TABLE_NAME ="students";
    private static final String ID ="id";
    private static final String NAME ="name";
    private static final String NUMBER ="number";
    private static final String ADDRESS ="address";
    private static final String EMAIL ="email";
    private static int VERSION = 1;

    private Context context;

    private  String SQLQuery = "CREATE TABLE "+TABLE_NAME +" (" +
    ID +" integer primary key, "+
    NAME + " TEXT, "+
    EMAIL +" TEXT, "+
    NUMBER+" TEXT," +
    ADDRESS +" TEXT)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, VERSION);
        this.context = context;
        Log.d(TAG,"DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG,"onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG,"onUpgrade: ");
    }
    public void hello(){
        Toast.makeText(context,"Xin Chào",Toast.LENGTH_SHORT).show();
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(NUMBER, student.getNumber());
        values.put(EMAIL, student.getEmail());
        values.put(ADDRESS, student.getAddress());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
        Log.d(TAG,"addStudent Thành Công");
    }
    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1)+"");
                student.setEmail(cursor.getString(2));
                student.setNumber(cursor.getString(3));
                student.setAddress(cursor.getString(4));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }

        db.close();
        return listStudent;
    }
    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,student.getName());
        contentValues.put(NUMBER,student.getNumber());
        contentValues.put(ADDRESS,student.getAddress());
        contentValues.put(EMAIL,student.getEmail());



         return db.update(TABLE_NAME,contentValues,ID +"=?",new String[] { String.valueOf(student.getId())});


    }
    public int deleteStudent(int id){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, ID + " = ?", new String[] { String.valueOf(id) });

    }
}
