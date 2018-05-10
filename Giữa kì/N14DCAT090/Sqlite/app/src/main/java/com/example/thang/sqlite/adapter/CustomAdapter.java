package com.example.thang.sqlite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.thang.sqlite.R;
import com.example.thang.sqlite.module.Student;

import java.util.List;

/**
 * Created by thang on 20-Apr-18.
 */

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resoure;
    private List<Student> listStudent;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.listStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView)convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber = (TextView)convertView.findViewById(R.id.tv_number);
            viewHolder.tvAddress = (TextView)convertView.findViewById(R.id.tv_address);
            viewHolder.tvEmail = (TextView)convertView.findViewById(R.id.tv_email);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
            Student student = listStudent.get(position);
            viewHolder.tvId.setText(String.valueOf(student.getId()));
            viewHolder.tvName.setText(student.getName());
            viewHolder.tvNumber.setText(student.getNumber());
            viewHolder.tvAddress.setText(student.getAddress());
            viewHolder.tvEmail.setText(student.getEmail());
        return convertView;
    }

    public class ViewHolder{
        private TextView tvId;
        private TextView tvName;
        private TextView tvNumber;
        private TextView tvAddress;
        private TextView tvEmail;
    }
}
