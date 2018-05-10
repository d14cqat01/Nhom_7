package com.example.ghost.giaodienmau;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by king_ on 12-Mar-18.
 */

public class HinhAd_Main1 extends ArrayAdapter<Integer> {
    Activity context;
    int resource;
    List<Integer> objects;

    public HinhAd_Main1(Activity context, int resource, @NonNull List<Integer> objects) {
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
        ImageView img=(ImageView) row.findViewById(R.id.iHinh2);
        img.setImageResource(this.objects.get(position));
        return row;
    }
}
