package com.example.eventpractice;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

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

        Button l = (Button) findViewById(R.id.largeButton);
        Button s =(Button) findViewById(R.id.smallButton);
        l.setOnClickListener(this);
        l.setOnLongClickListener(this);
        s.setOnClickListener(this);
        s.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        float currentSizeSp = tv_text.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        if(view.getId() == R.id.largeButton) {
            tv_text.setTextSize(currentSizeSp + 5);
            tv_size.setText(String.valueOf(currentSizeSp + 5));
            tv_text.setTextColor(Color.parseColor("#992233"));
        }else{
            tv_text.setTextSize(currentSizeSp - 5);
            tv_size.setText(String.valueOf(currentSizeSp - 5));
            tv_text.setTextColor(Color.parseColor("#229933"));
        }



    }

    @Override
    public boolean onLongClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        tv_text.setTextSize(30);
        tv_size.setText(String.valueOf("30"));
        tv_text.setTextColor(Color.parseColor("#333333"));
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        return false;
    }
}