package com.example.ghost.giaodienmau;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by king_ on 07-Mar-18.
 */

public class HinhAd_Main2 extends ArrayAdapter<Integer>
    {
    Activity context;
    int resource;
    List<Integer> object;
    List<String> string;

    public HinhAd_Main2(Activity context, int resource, @NonNull List<Integer> objects, @NonNull List<String> string) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.object=objects;
        this.string=string;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        ImageView img=(ImageView) row.findViewById(R.id.iHinh);
        img.setImageResource(this.object.get(position));
        TextView text=(TextView) row.findViewById(R.id.tvmenu);
        text.setText(this.string.get(position));
        return row;
    }
}
