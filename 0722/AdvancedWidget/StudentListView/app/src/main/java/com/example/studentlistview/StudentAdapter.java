package com.example.studentlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(@NonNull Context context, ArrayList<Student> students) {
        super(context, 0,students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Student user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_information,parent,false);
        }
        TextView tv_name =(TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_studentID = (TextView) convertView.findViewById(R.id.tv_studentID);
        tv_name.setText(user.name);
        tv_studentID.setText(String.valueOf(user.studentID));
        return convertView;
    }
}
