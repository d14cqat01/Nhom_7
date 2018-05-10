package com.example.ghost.giaodienmau.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghost.giaodienmau.R;

import java.util.ArrayList;

/**
 * Created by namxathu on 28/04/2018.
 */

public class KhanCapAdapter extends ArrayAdapter<KhanCap>
{
    private Context context;
    private int resource;
    private ArrayList<KhanCap> arrayKhanCap;
    public KhanCapAdapter(@NonNull Context context, int resource, @NonNull ArrayList<KhanCap> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource=resource;
        this.arrayKhanCap=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.danhsach_khancap, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.noidung = convertView.findViewById(R.id.noidung);
            viewHolder.number =convertView.findViewById(R.id.number);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        KhanCap khancap = arrayKhanCap.get(position);
        viewHolder.noidung.setText(khancap.getNoidung());
        viewHolder.image.setImageResource(khancap.getImage());
        viewHolder.number.setText(khancap.getNumber());

        return convertView;
    }
    public class ViewHolder
    {
        ImageView image;
        TextView noidung;
        TextView number;
    }
}
