package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Note_011 extends AppCompatActivity {
    Note_Adapter noteApdapter;
    List<Note_class_011> note;
    ExpandableListView listView;
    ImageButton addnote;
    Data_Manager_011 data_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_011);
        addControl();
        addEvent();
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
                Intent in = new Intent(Note_011.this, KhanCapActivity.class);
                startActivity(in);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    private void addEvent() {
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Note_011.this,Note_add.class);
                intent.putExtra("ID", note.size());
                startActivityForResult(intent,88);
            }
        });
    }

    private void addControl() {
        listView = (ExpandableListView) findViewById(R.id.listViewNote);
        addnote = (ImageButton) findViewById(R.id.addnote_011);
        data_manager = new Data_Manager_011(this);
        note = data_manager.getNote();
        noteApdapter=new Note_Adapter(Note_011.this,note);
        listView.setAdapter(noteApdapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 && resultCode==33)
        {
            Note_class_011 note1=(Note_class_011)data.getSerializableExtra("Note_cl");
            if(note1==null) {
                Toast.makeText(this,"Cập nhật thất bại",Toast.LENGTH_SHORT).show();
            }
            else{
                data_manager.Update(note1);
                note.clear();
                note.addAll(data_manager.getNote());
                noteApdapter.notifyDataSetChanged();
                Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==88 && resultCode==22)
        {
            Note_class_011 note1=(Note_class_011)data.getSerializableExtra("Note_cl");
            if(note1==null)
            {
                Toast.makeText(this,"Thêm không thành công",Toast.LENGTH_SHORT).show();
            }else {
                data_manager.add_Note(note1);
                note.clear();
                note.addAll(data_manager.getNote());
                noteApdapter.notifyDataSetChanged();
                listView.setSelection(note.size()-1);
                Toast.makeText(this,"Thêm  thành công",Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==99 && resultCode==44)
        {
            Note_class_011 note1=(Note_class_011)data.getSerializableExtra("Note_cl");
            data_manager.Delete(note1.getMaNote());
            note.clear();
            note.addAll(data_manager.getNote());
            Toast.makeText(this,"Xóa thành công",Toast.LENGTH_SHORT).show();
            noteApdapter.notifyDataSetChanged();

        }
    }
}
