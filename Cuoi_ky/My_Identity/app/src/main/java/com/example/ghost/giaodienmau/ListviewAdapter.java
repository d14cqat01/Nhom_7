package com.example.ghost.giaodienmau;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghost.giaodienmau.model.Money;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IT on 1/5/2018.
 */

public class ListviewAdapter extends ArrayAdapter<Money> {

    Context context;
    int layout;
    ArrayList<Money> mangMoney;

    public ListviewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Money> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        mangMoney = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Holder holder;
        LayoutInflater inflater = LayoutInflater.from(context);

        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
            holder = new Holder();
            holder.tvld = convertView.findViewById (R.id.tvld);
            holder.tvst = convertView.findViewById (R.id.tvsotien);
            holder.img = convertView.findViewById (R.id.img);
            holder.chb = convertView.findViewById (R.id.check);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        final Money p = mangMoney.get(position);

        holder.tvld.setText ("Lý do : "+p.getLydo ());
        holder.tvst.setText ("Số tiền : "+p.getSotien ());
        if(p.getMucdich ()==1){
            holder.img.setImageResource (R.drawable.plus);
        }
        else
            holder.img.setImageResource (R.drawable.minus);

        holder.chb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                p.setChosen(b);
                Toast.makeText(getContext(),mangMoney.toString(),Toast.LENGTH_LONG).show();
            }
        });

        holder.chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    static class Holder{
        TextView tvld,tvst;
        ImageView img;
        CheckBox chb;
    }
}
