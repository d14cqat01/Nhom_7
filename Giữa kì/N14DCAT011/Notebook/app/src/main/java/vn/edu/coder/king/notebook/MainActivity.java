package vn.edu.coder.king.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Note_Adapter noteApdapter;
    List<Note_class_011> note;
    ExpandableListView listView;
    ImageButton addnote;
    Data_Manager_011 data_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }
    private void addEvent() {
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Note_add.class);
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
        noteApdapter=new Note_Adapter(MainActivity.this,note);
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


