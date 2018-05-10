package com.example.hao.anhhao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anhhao on 20/04/2018.
 */

public class NhaHangAdapter extends ArrayAdapter<NhaHang>
{
    private Context context;
    private int resource;
    private ArrayList<NhaHang> arrayNhahang;
    public NhaHangAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NhaHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayNhahang = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dsnhahang, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.pic_list = convertView.findViewById(R.id.pic_list);
            viewHolder.name_list = convertView.findViewById(R.id.name_list);
            viewHolder.addr_list = convertView.findViewById(R.id.addr_list);
            viewHolder.phone_list = convertView.findViewById(R.id.phone_list);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhaHang nhahang = arrayNhahang.get(position);
        if (nhahang.getQuocgia().compareTo("Việt Nam") == 0)
        {
            viewHolder.pic_list.setImageResource(R.drawable.android_vietnam);
        }
        else {
            viewHolder.pic_list.setImageResource(R.drawable.android_international);
        }
        viewHolder.name_list.setText(nhahang.getHoten());
        viewHolder.addr_list.setText(nhahang.getDiachi());
        viewHolder.phone_list.setText(nhahang.getPhone());
        return convertView;

    }
    private class ViewHolder {
        TextView name_list;
        TextView addr_list;
        TextView phone_list;
        ImageView pic_list;
    }
}
