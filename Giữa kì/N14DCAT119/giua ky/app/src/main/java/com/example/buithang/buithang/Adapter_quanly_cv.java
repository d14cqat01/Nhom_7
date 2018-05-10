package com.example.buithang.buithang;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lephi on 11/04/2018.
 */

public class Adapter_quanly_cv extends BaseAdapter implements Filterable {
    ArrayList<CongViec> congViecs;
    Context context;

    ArrayList<CongViec> TimKiemDuocLuuVaoDay ;
    TimKiem Chucnangtimkiem;

    public Adapter_quanly_cv(ArrayList<CongViec> congViecs, Context context)
    {
        this.context=context;
        this.congViecs=congViecs;
        TimKiemDuocLuuVaoDay=congViecs;
        getFilter();
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

    @Override
    public Filter getFilter() {
        if(Chucnangtimkiem==null)
        {
            Chucnangtimkiem=new TimKiem();
        }
        return Chucnangtimkiem;

    }

    public class Text
    {
        TextView ten_cv;
        TextView noidung_cv;
        TextView ghichu_cv;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        Text text;
        if(convertView==null)
        {
            text=new Text();
            convertView=inflater.inflate(R.layout.custom_lisview_cv_quanly,null);
            text.ten_cv=(TextView)convertView.findViewById(R.id.quantri_ten);
            text.noidung_cv=(TextView)convertView.findViewById(R.id.quantri_nd);
            text.ghichu_cv=(TextView)convertView.findViewById(R.id.quantri_ghichu);
            convertView.setTag(text);
        }
        else
        {
            text=(Text) convertView.getTag();
        }
        text.ten_cv.setText(congViecs.get(position).getTenCongViec());
        text.noidung_cv.setText(congViecs.get(position).getNoiDung());
        text.ghichu_cv.setText(congViecs.get(position).getGhiChu());
        return convertView;
    }


    class  TimKiem extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<CongViec> filterList = new ArrayList<CongViec>();
                for (int i = 0; i < TimKiemDuocLuuVaoDay.size(); i++)
                {
                    if ((TimKiemDuocLuuVaoDay.get(i).getTenCongViec().toUpperCase())
                            .contains(constraint.toString().toUpperCase()))
                    {
                        CongViec c = TimKiemDuocLuuVaoDay.get(i);
                        filterList.add(c);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else {
                results.count = TimKiemDuocLuuVaoDay.size();
                results.values = TimKiemDuocLuuVaoDay;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            congViecs = (ArrayList<CongViec>) results.values;
            notifyDataSetChanged();
        }
    }
}
