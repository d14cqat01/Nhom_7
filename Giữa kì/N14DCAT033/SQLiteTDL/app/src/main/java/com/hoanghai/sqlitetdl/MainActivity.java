package com.hoanghai.sqlitetdl;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = findViewById(R.id.lvCongViec);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this,R.layout.item_congviec,arrayCongViec);
        lvCongViec.setAdapter(adapter);

        database = new Database(this,"todolist.sqlite",null,1);

        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

        GetDataCongViec();

    }

    private void GetDataCongViec(){
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()){
            int id = dataCongViec.getInt(0);
            String tenCV = dataCongViec.getString(1);
            arrayCongViec.add(new CongViec(id, tenCV));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_congviec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuThemCV){
            DialogThemCV();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThemCV(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themcongviec);

        final EditText edtTenCV = dialog.findViewById(R.id.edtTenCV);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCV = edtTenCV.getText().toString();
                if(tenCV.equals("")){
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập tên công việc", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.QueryData("INSERT INTO CongViec VALUES(null,'"+ tenCV +"')");
                    Toast.makeText(MainActivity.this,"Thêm thành công "+ tenCV, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogSuaCV(String tenCV, final int id){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_suacongviec);

        final EditText edtSuaCV = dialog.findViewById(R.id.edtSuaTenCV);
        Button btnLuu = dialog.findViewById(R.id.btnLuu);
        Button btnHuy = dialog.findViewById(R.id.btnHuySua);

        edtSuaCV.setText(tenCV);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCVMoi = edtSuaCV.getText().toString();
                database.QueryData("UPDATE CongViec SET TenCV = '"+ tenCVMoi +"' WHERE Id = '"+ id +"'" );
                Toast.makeText(MainActivity.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void XoaCV(String tenCV, int id){
        database.QueryData("DELETE FROM CongViec WHERE Id = '"+ id +"'");
        Toast.makeText(MainActivity.this,"Bạn đã xóa " + tenCV,Toast.LENGTH_SHORT).show();
        GetDataCongViec();
    }
}
