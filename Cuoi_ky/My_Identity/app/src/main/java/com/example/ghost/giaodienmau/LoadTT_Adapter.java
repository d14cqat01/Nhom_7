package com.example.ghost.giaodienmau;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by king_ on 12-Mar-18.
 */

public class LoadTT_Adapter extends ArrayAdapter<Thong_tin011> {
    Activity context;
    int resource;
    List<Thong_tin011> objects;

    public LoadTT_Adapter(@NonNull Activity context, int resource, @NonNull List<Thong_tin011> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        Thong_tin011 tt=this.objects.get(position);

        TextView name=(TextView) row.findViewById(R.id.txtNAME);
        TextView cmnd=(TextView) row.findViewById(R.id.txtCMND);
        TextView ngaysinh=(TextView) row.findViewById(R.id.txtNGAYSINH);
        TextView noisinh=(TextView) row.findViewById(R.id.txtNOISINH);
        TextView so_BH=(TextView) row.findViewById(R.id.txtBH);
        TextView email=(TextView) row.findViewById(R.id.txtEMAIL);
        final TextView editEmail=(TextView) row.findViewById(R.id.itemEMAIL);
        TextView edtiSDT=(TextView) row.findViewById(R.id.itemSDT);
        TextView soDT=(TextView) row.findViewById(R.id.txtSDT);

        name.setText(tt.getName());
        cmnd.setText(tt.getCMND());
        ngaysinh.setText(tt.getNgay());
        noisinh.setText(tt.getQueQuan());
        so_BH.setText(tt.getSo_BH());
        email.setText(tt.getEmail());
        soDT.setText(tt.getSo_dt());

        edtiSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent1();
            }
        });
        editEmail.setOnClickListener(new   View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent2();
            }
        });
        return row;
    }

    private void addEvent2() {
        Intent intent=new Intent(this.context,Edit_mail.class);
        this.context.startActivityForResult(intent,11);

    }

    private void addEvent1() {
        Intent intent=new Intent(this.context,Edit_Sdt.class);
        this.context.startActivityForResult(intent,21);

    }

}
