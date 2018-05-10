package com.example.buithang.buithang;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter extends BaseAdapter {
    ArrayList<CongViec> congViecs;
    Context context;
    public Adapter(ArrayList<CongViec> congViecs, Context context)
    {
        this.context=context;
        this.congViecs=congViecs;
    }
    public int getCount() {
        return congViecs.size();
    }

    @Override
    public Object getItem(int position) {
        return congViecs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Text
    {
        TextView ten_cv;
        TextView ghichu_cv;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        Text text;
        if(convertView==null)
        {
            text=new Text();
            convertView=inflater.inflate(R.layout.dialog_ds_congviec,null);
            text.ten_cv=(TextView)convertView.findViewById(R.id.custom_list_tencv);
            text.ghichu_cv=(TextView)convertView.findViewById(R.id.custom_list_ghichucv);
            convertView.setTag(text);
        }
        else
        {
            text=(Text) convertView.getTag();
        }
        text.ten_cv.setText(congViecs.get(position).getTenCongViec());
        text.ghichu_cv.setText(congViecs.get(position).getGhiChu());
        return convertView;
    }
}
