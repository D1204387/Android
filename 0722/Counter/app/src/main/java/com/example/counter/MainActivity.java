package com.example.counter;

import static kotlin.text.Typography.times;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void Count(View viw){

        EditText et_limit = (EditText) findViewById(R.id.limit);
        TextView tv_times = (TextView) findViewById(R.id.times);
        Button bt_count =(Button)findViewById(R.id.count);

        int times = Integer.parseInt(tv_times.getText().toString());
        int limit = Integer.parseInt(et_limit.getText().toString());


        if(limit> times){
            times += 1;
            tv_times.setText(String.valueOf(times));

        }else{
            tv_times.setText("已達上限");
            bt_count.setEnabled(false);

        }




    }

    public void Init(View view){
        Button bt_count = (Button)findViewById(R.id.count);
        TextView tv_count = (TextView) findViewById(R.id.times);

        tv_count.setText("0");
        bt_count.setEnabled(true);


    }
}

