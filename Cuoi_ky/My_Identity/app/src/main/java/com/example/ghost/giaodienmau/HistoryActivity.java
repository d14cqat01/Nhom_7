package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Global;
import com.example.ghost.giaodienmau.model.History;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    Button button;
    ListView listView;

    ArrayList<HistoryUpdate> arrayHistory;

    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.history);


        arrayHistory = new ArrayList<HistoryUpdate>();

        button = (Button)findViewById(R.id.DeleteHistory);
        listView = (ListView)findViewById(R.id.listViewHistory);


        final HistoryAdapter adapter = new HistoryAdapter(
                HistoryActivity.this,
                R.layout.history_adapter,
                arrayHistory
        );
        listView.setAdapter(adapter);


        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("History").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(!dataSnapshot.equals(null)){
                    HistoryUpdate historyUpdate = (HistoryUpdate)dataSnapshot.getValue(HistoryUpdate.class);

                    if(historyUpdate.getId().equals(Global.getId())){
                        historyUpdate.setKey(dataSnapshot.getKey());
                        //Toast.makeText(HistoryActivity.this,historyUpdate.getKeys(),Toast.LENGTH_LONG).show();
                        arrayHistory.add(historyUpdate);
                    }
                }



                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();

                for(int i=0;i<arrayHistory.size();i++){
                    if(arrayHistory.get(i).getKeys().equals(key)){
                        arrayHistory.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mData.child("ViPham").child(Global.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ViPham viPham = (ViPham)dataSnapshot.getValue(ViPham.class);
                HistoryUpdate historyUpdate = new HistoryUpdate(Global.getId(),"Lỗi:"+viPham.vipham+"\n" +
                        "Thu giữ:"+viPham.thugiu+"\n" +
                        "Số tiền phat:"+viPham.sotienphat +"\n"+
                        "Thu giữ:" +viPham.thugiu+"\n"+
                        "Chi tiết: "+viPham.chitiet,viPham.getTime());

                historyUpdate.setKey(dataSnapshot.getKey());
                arrayHistory.add(historyUpdate);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();

                for(int i=0;i<arrayHistory.size();i++){
                    if(arrayHistory.get(i).getKeys().equals(key)){
                        arrayHistory.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(HistoryUpdate i: arrayHistory){
                    mData.child("History").child(i.getKeys()).removeValue();
                    mData.child("ViPham").child(Global.getId()).child(i.getKeys()).removeValue();
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.back:
                // Gọi màn hình AditionSportActivity
                Intent in = new Intent (HistoryActivity.this,Main2_011.class);
                startActivity (in);
        }
        return super.onOptionsItemSelected(item);
    }
}
