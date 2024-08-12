package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends ArrayAdapter<City> {
    public CitiesAdapter(@NonNull Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        City city = getItem(position);
        // check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_information,parent,false);
        }

        //lookup view for data population
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_zipCode = (TextView)convertView.findViewById(R.id.tv_zipCode);
        // Populate the data into the template view using the data object

        tv_name.setText(city.name);
        tv_zipCode.setText(city.zipCode);

        //Return the completed view to render on screen

        return convertView;
    }
}
