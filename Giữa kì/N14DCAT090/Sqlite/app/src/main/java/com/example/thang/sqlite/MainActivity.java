package com.example.thang.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thang.sqlite.adapter.CustomAdapter;
import com.example.thang.sqlite.data.DBManager;
import com.example.thang.sqlite.module.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

        private EditText edtId;
        private EditText edtNAme;
        private EditText edtNumber;
        private EditText edtAddress;
        private EditText edtEmail;
        private Button btnLuu;
        private Button btnUpdate;
        private ListView lvStudent;
        private DBManager dbManager;
        private CustomAdapter customAdapter;
        private List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dbManager.hello();
        initWidget();
        studentList = dbManager.getAllStudent();
        setAdapter();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = creatStudent();
                if(student!=null){
                    dbManager.addStudent(student);
                }
                update();
                setAdapter();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = creatStudent();
                student.setId(Integer.parseInt(String.valueOf(edtId.getText())));
                student.setName(edtNAme.getText()+"");
                student.setNumber(edtNumber.getText()+"");
                student.setAddress(edtAddress.getText()+"");
                student.setEmail(edtEmail.getText()+"");
                int result = dbManager.updateStudent(student);
                if(result>0){
                    update();
                }
                    btnLuu.setEnabled(true);
                    btnUpdate.setEnabled(false);

            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                edtId.setText(String.valueOf(student.getId()));
                edtNAme.setText(student.getName());
                edtNumber.setText(student.getNumber());
                edtAddress.setText(student.getAddress());
                edtEmail.setText(student.getEmail());
                btnLuu.setEnabled(false);
                btnUpdate.setEnabled(true);

            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                int result = dbManager.deleteStudent(student.getId());
                if(result>0){
                    Toast.makeText(MainActivity.this,"Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    update();
                }else {
                    Toast.makeText(MainActivity.this,"Chưa Xóa Được", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private Student creatStudent(){
        String name = edtNAme.getText().toString();
        String number = edtNumber.getText()+"";
        String address = String.valueOf(edtAddress.getText());
        String email = edtEmail.getText().toString();

        Student student = new Student(name,number,address,email);
        return student;
    }
    private void initWidget(){
        edtId = (EditText) findViewById(R.id.edt_id);
        edtNAme = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnLuu = (Button) findViewById(R.id.btn_save);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        lvStudent = (ListView) findViewById(R.id.lv_student);

    }
    private void setAdapter(){
        if(customAdapter==null){
            customAdapter = new CustomAdapter(this,R.layout.item_list_student,studentList);
            lvStudent.setAdapter(customAdapter);
        }else {
            lvStudent.setSelection(customAdapter.getCount()-1);
            customAdapter.notifyDataSetChanged();
        }

    }
    public void update(){
        studentList.clear();
        studentList.addAll(dbManager.getAllStudent());
        if(customAdapter!=null){
            customAdapter.notifyDataSetChanged();
        }

    }


}
