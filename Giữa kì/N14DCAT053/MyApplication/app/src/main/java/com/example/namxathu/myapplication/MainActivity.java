package com.example.namxathu.myapplication;

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

import com.example.namxathu.myapplication.model.Student;
import com.example.namxathu.myapplication.model.StudentAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText edit_hoten,edit_mssv,edit_lop;
    private Button button_save;
    private Spinner spinner_gioitinh;
    private ListView list_student;
    private  ArrayList<Student> studentsList;
    private  StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();
        studentsList = new ArrayList<>();
        connectView();


        // Phan spinner
        List<String> list = new ArrayList<>();
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapter =new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner_gioitinh.setAdapter(adapter);
        spinner_gioitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, spinner_gioitinh.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        studentAdapter =  new StudentAdapter(this,R.layout.activity_main,studentsList);
        list_student.setAdapter(studentAdapter);

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                studentsList.add(student);
                studentAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                ChangeList(student);
                studentAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Student student = dataSnapshot.getValue(Student.class);
                    studentsList.remove(student);
                    studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentsList.get(position);
                edit_hoten.setText(student.getHoten());
                edit_mssv.setText(student.getMssv());
                edit_lop.setText(student.getLop());
                if(student.getGioitinh().compareTo("Nam")==0) {
                    spinner_gioitinh.setSelection(0);
                }
                else {spinner_gioitinh.setSelection(1);}

            }
        });
        list_student.setOnItemLongClickListener(new ItemLongClickRemove());


    }

    private void connectView()
    {
        edit_hoten = findViewById(R.id.edit_hoten);
        edit_mssv = findViewById(R.id.edit_mssv);
        edit_lop = findViewById(R.id.edit_lop);
        spinner_gioitinh = findViewById(R.id.spinner_gioitinh);
        button_save = findViewById(R.id.button_save);
        list_student = findViewById(R.id.list_student);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doClickButton();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void doClickButton() throws InterruptedException {
        String hoten = edit_hoten.getText().toString().trim();
        String mssv = edit_mssv.getText().toString().trim();
        String lop = edit_lop.getText().toString().trim();
        String gioitinh = spinner_gioitinh.getSelectedItem().toString();

        Student student = new Student(hoten,mssv,lop,gioitinh);

        InsertFirebase(student,hoten,mssv,lop);
        edit_hoten.setText(null);
        edit_mssv.setText(null);
        edit_lop.setText(null);



    }

    private void InsertFirebase(Student student,String hoten,String mssv,String lop)
        {
        if(!hoten.isEmpty() && !mssv.isEmpty() && !lop.isEmpty()) {
            database.child(student.getMssv()).setValue(student, new DatabaseReference.CompletionListener() {
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


    private void ChangeList(Student student)
    {

        for(int i=0;i<studentsList.size();i++)
        {
            if(studentsList.get(i).getMssv().compareToIgnoreCase(student.getMssv())==0)
            {
                studentsList.remove(i);
            }

        }
        studentsList.add(student);

    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Student student = studentsList.get(position);
                   database.child(student.getMssv()).removeValue();
                   studentsList.remove(student);
                   studentAdapter.notifyDataSetChanged();

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
