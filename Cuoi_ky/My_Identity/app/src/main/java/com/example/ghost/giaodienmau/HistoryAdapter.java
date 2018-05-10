package com.example.ghost.giaodienmau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<HistoryUpdate> arrayHistory;


    public HistoryAdapter(Context context, int layout, List<HistoryUpdate> historyList){
        mycontext = context;
        myLayout = layout;
        arrayHistory = historyList;
    }
    @Override
    public int getCount() {
        return arrayHistory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout, null);
        //Anh xa va gan gia tri
        TextView time = (TextView)view.findViewById(R.id.TimeEdit);
        TextView noidung = (TextView)view.findViewById(R.id.NoiDungEdit);
        time.setText(arrayHistory.get(i).getTime());
        noidung.setText(arrayHistory.get(i).getNoidung());

        return view;
    }
}
