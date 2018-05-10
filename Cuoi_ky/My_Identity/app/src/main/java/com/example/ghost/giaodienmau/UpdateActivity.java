package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
    private String username;
    private String id;
    DatabaseReference mDatabase ;
    StorageReference mStorage;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://database-ab95c.appspot.com");    //change the url according to your firebase app

    Spinner spinner;
    Button guiyeucau, huy;
    EditText muc, noidung;
    ImageView imgSua;
    ArrayList<String> arrayLoaiGiayTo;
    ArrayAdapter arrayAdapter;

    RadioGroup radioGroup;
    RadioButton VtGiayTo;
    RadioButton VtApp;
    String loaiGiayTo;
    String urlAnh="";

    int REQUEST_CODE_IMAGE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // lay id
        username = Global.getUsername();
        id = Global.getId();



        anhxa();


        arrayLoaiGiayTo.add("Chứng minh nhân dân");
        arrayLoaiGiayTo.add("Bảo hiểm y tế");
        arrayLoaiGiayTo.add("Bằng lái xe");


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayLoaiGiayTo);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(UpdateActivity.this, arrayLoaiGiayTo.get(i),Toast.LENGTH_LONG).show();
                loaiGiayTo = arrayLoaiGiayTo.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Open image
        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                //String picturepath = pictureDirectory.getPath();
                //Uri data = Uri.parse(picturepath);
                //imgSua.setBackground(data);
                Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

        //huy
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this, Main2_011.class);
                startActivity(i);
            }
        });


        // Gửi yêu cầu
        guiyeucau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dat ten file hinh anh





                //Thong tin khac
                String title = muc.getText().toString();
                String nd =noidung.getText().toString();
                String vitriluu=getViTri();
                if(title.equals("") || nd.equals("") || loaiGiayTo.equals("")){
                    Toast.makeText(UpdateActivity.this,"Chưa điền nội dung thông tin chỉnh sửa",Toast.LENGTH_SHORT).show();
                }else {
                    if(vitriluu.equals("App")){

                    }else {

                        Calendar calendar =Calendar.getInstance();


                        StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");



                        // Get the data from an ImageView as bytes
                        // Get the data from an ImageView as bytes
                        imgSua.setDrawingCacheEnabled(true);
                        imgSua.buildDrawingCache();
                        Bitmap bitmap = imgSua.getDrawingCache();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] data = baos.toByteArray();

                        UploadTask uploadTask = mountainsRef.putBytes(data);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                Toast.makeText(UpdateActivity.this,"Loi "+exception,Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                // ...
                                //Toast.makeText(UpdateActivity.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                                Uri download = taskSnapshot.getDownloadUrl();
                                urlAnh =download.toString();
                                Toast.makeText(UpdateActivity.this,urlAnh,Toast.LENGTH_LONG).show();
                            }
                        });

                        CaNhanUpdate updateObject = new CaNhanUpdate(Global.getId(),vitriluu,loaiGiayTo,title,nd,urlAnh);

                        mDatabase.child("UpdateInformation").push().setValue(updateObject);
                        Toast.makeText(UpdateActivity.this,"Gửi thông tin thành công",Toast.LENGTH_SHORT).show();

//                        HistoryUpdate historyUpdate = new HistoryUpdate("xxx",vitriluu);
//                        mDatabase.child("History").push().setValue(historyUpdate);
//                        Toast.makeText(UpdateActivity.this,"Gửi thông tin thành công",Toast.LENGTH_SHORT).show();
//                        muc.setText("");
//                        noidung.setText("");
                        long tim=System.currentTimeMillis();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                        String curTime =df.format(tim);


                        //mDatabase.child("ViPham").child(Global.getId()).push().setValue(new ViPham("Danh nhau","5000000","chung minh nhan dan","xa hoi",curTime));
                        HistoryUpdate historyUpdate = new HistoryUpdate(Global.getId(),"Chỉnh sửa: "+loaiGiayTo, curTime);
                        mDatabase.child("History").push().setValue(historyUpdate);
                        muc.setText("");
                        noidung.setText("");






                    }

                }

            }
        });





    }


    // Gán hình ảnh cần sửa
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_IMAGE && resultCode ==RESULT_OK && data!=null){
            Uri uri=data.getData();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSua.setImageBitmap(bitmap);
        }
    }

    private void anhxa() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        arrayLoaiGiayTo = new ArrayList<String>();
        spinner = (Spinner)findViewById(R.id.spinnerLuaChon);
        muc = (EditText)findViewById(R.id.editMucsua);
        noidung = (EditText)findViewById(R.id.editNdsua);
        guiyeucau = (Button)findViewById(R.id.btnGui);
        huy = (Button)findViewById(R.id.btnHuy);
        imgSua = (ImageView)findViewById(R.id.imgButton);
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        VtGiayTo = (RadioButton)findViewById(R.id.vtgiayto);
        VtApp = (RadioButton)findViewById(R.id.vtapp);


    }

    private String getViTri(){
        RadioButton vt = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

        if(vt==VtGiayTo){
            // Toast.makeText(UpdateActivity.this,VtGiayTo.getText().toString(),Toast.LENGTH_LONG).show();
            return VtGiayTo.getText().toString();
        }else {
            return VtApp.getText().toString();
        }



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khancap, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.khancap: {
                Intent in = new Intent(UpdateActivity.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
