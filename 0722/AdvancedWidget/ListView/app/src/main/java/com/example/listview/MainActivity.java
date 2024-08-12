package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private ListView citiesListView;
    private List<String> cities = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<City> cityArrayList = new ArrayList<City>();
        cityArrayList.add(new City("Taipei",100));
        cityArrayList.add(new City("Taichung",400));
        CitiesAdapter adapter = new CitiesAdapter(this, cityArrayList);
        ListView cityListView = (ListView)findViewById(R.id.cities);
        cityListView.setAdapter(adapter);


//        citiesListView = (ListView) findViewById(R.id.cities);
//        setCities();
//
//        citiesListView.setOnItemClickListener(this);
    }

//    public void setCities(){
//        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
//        cities.add("花蓮縣");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cities);
//        citiesListView.setAdapter(adapter);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        String[] cities = getResources().getStringArray(R.array.cities);
//
//        TextView tv = (TextView) findViewById(R.id.result);
//
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("城市");
//        dialog.setMessage("您選的是:" + cities[i]);
//        dialog.setCancelable(true);
//        dialog.setPositiveButton("確定",null);
//        dialog.setNeutralButton("取消",null);
//        dialog.setNegativeButton("放棄",null);
//
//        dialog.show();
//
////        Toast.makeText(this, "您選的是:" + cities[i],Toast.LENGTH_LONG).show();
////        tv.setText("您選的是:" + cities[i]);
//
//    }
}