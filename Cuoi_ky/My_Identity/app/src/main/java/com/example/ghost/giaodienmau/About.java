package com.example.ghost.giaodienmau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private TextView text1;
    private TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text1.setText("_      Phần mềm quản Căn Cước Công Dân đã được đội Phòng Chống Tội Phạm Công Nghệ Cao TPHCM (trực thuộc Bộ Công An)cho ra đời, nhằm tối ưu hóa việc quản lý và dễ dàng sử dụng cho người dân.");
        text2.setText("_      Nghị định số 137/2018/NĐ-CP ngày 15/04/2018 của Chính phủ quy định chi tiết một số điều và biện pháp thi hành Luật Căn cước công dân");
    }
}
