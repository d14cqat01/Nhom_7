package com.example.namxathu.myapplication.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namxathu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.namxathu.myapplication.R.drawable.iconnu;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private ArrayList<Student> arrayStudent;

    public StudentAdapter( Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayStudent = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.hoten_list = convertView.findViewById(R.id.hoten_list);
            viewHolder.mssv_list = convertView.findViewById(R.id.mssv_list);
            viewHolder.lop_list = convertView.findViewById(R.id.lop_list);
            viewHolder.image_gioitinh = convertView.findViewById(R.id.image_gioitinh);
            viewHolder.button_xoa = convertView.findViewById(R.id.button_save);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.button_xoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayStudent.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        Student student =  arrayStudent.get(position);

        if(student.getGioitinh().compareToIgnoreCase("NAM")==0)
            viewHolder.image_gioitinh.setImageResource(R.drawable.iconnam);
        else viewHolder.image_gioitinh.setImageResource(R.drawable.iconnu);
        viewHolder.hoten_list.setText(student.getHoten());
        viewHolder.mssv_list.setText(student.getMssv());
        viewHolder.lop_list.setText(student.getLop());
        return convertView;
    }

    public class ViewHolder
    {
        TextView hoten_list;
        TextView mssv_list;
        TextView lop_list;
        ImageView image_gioitinh;
        Button button_xoa;
    }
}
