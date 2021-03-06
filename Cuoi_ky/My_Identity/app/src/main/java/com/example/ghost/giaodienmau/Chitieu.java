package com.example.ghost.giaodienmau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;

public class Chitieu extends AppCompatActivity implements View.OnClickListener{
    private Button bct,btn,btq;
    TextView tv;
    ListView lw;
    ArrayList<Money> mangMoney;
    ArrayList<Money> mangchi;
    ListviewAdapter listviewAdapter;
    SQLite sqLite;
    String cl;
    public static final String VITRI = "vitri";
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_chitieu);
        ActionBar actionBar = getSupportActionBar ();
        actionBar.setDisplayHomeAsUpEnabled (true);
        actionBar.setDisplayShowTitleEnabled (false);
        connectview();
        Intent in = getIntent ();
        cl = in.getStringExtra ("cl");
        String chi = in.getStringExtra ("chi");
        sqLite = new SQLite (Chitieu.this);

        mangMoney = new ArrayList<> ();
        sqLite.loadData(mangMoney);
        tv.setText (chi+" VNĐ");;
        loc ();
        listviewAdapter = new ListviewAdapter (Chitieu.this,R.layout.list_item,mangchi);
        lw.setAdapter (listviewAdapter);
    }

    public void loc(){
        mangchi = new ArrayList<> ();
        for(int i=0;i<mangMoney.size ();i++){
            if(mangMoney.get (i).getMucdich ()==0){
                mangchi.add (mangMoney.get (i));
            }
        }
    }



    private void connectview() {
        bct = (Button) findViewById (R.id.bct);
        btn = (Button) findViewById (R.id.btn);
        btq = (Button) findViewById (R.id.btq);
        lw = (ListView) findViewById (R.id.lwct);
        tv = (TextView) findViewById (R.id.ctconlai);


        btq.setOnClickListener (this);
        bct.setOnClickListener (this);
        btn.setOnClickListener (this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actice_nemu, menu);
        getMenuInflater ().inflate (R.menu.login_menu,menu);
        getMenuInflater ().inflate (R.menu.offline_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.madd:{
                doclickadd();
                break;
            }

            case android.R.id.home:
            {
                onBackPressed ();
                break;
            }
            case R.id.mdelete:{
                delete();
                break;
            }
            case R.id.medit:{
                edit();
                break;
            }
        }
        listviewAdapter.notifyDataSetChanged ();
        return super.onOptionsItemSelected(item);
    }

    private void edit() {
        for (int i = 0; i < mangMoney.size (); i++) {
            if (mangMoney.get (i).isChosen ()) {
                Intent in =new Intent (Chitieu.this,Sua.class);
                Money money = mangMoney.get (i);
                in.putExtra ("money",money);
                mangMoney.remove (i);
                sqLite.saveData (mangMoney);
                startActivity (in);
            }
        }
    }

    void delete() {
        for (int i = 0; i < mangMoney.size (); i++) {
            if (mangMoney.get (i).isChosen ()) {
                mangMoney.remove (i);
            }
        }
        sqLite.saveData (mangMoney);

    }

    private void doclickadd() {
        Intent in = new Intent (Chitieu.this,Add.class);
        in.putExtra ("cl",cl);
        startActivity (in);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.btq:{
                doclickbtq();
                break;
            }
            case R.id.bct:{
                break;
            }
            case R.id.btn:{
                doclickbtn ();
                break;
            }
        }
    }

    private void doclickbtq() {
        Intent in = new Intent (this,Tongquan.class);
        startActivity (in);
    }
    private void doclickbtn() {
        Intent in = new Intent (this,ThuNhap.class);
        startActivity (in);
    }
}
