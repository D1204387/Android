package com.example.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        EditText num1 = (EditText) findViewById(R.id.num1);
        num1.setText("0");
        EditText num2 = (EditText) findViewById(R.id.num2);
        num2.setText("0");

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String[] op = getResources().getStringArray(R.array.calculate);

        EditText et_num1 = (EditText) findViewById(R.id.num1);
        double num1 =Double.parseDouble(et_num1.getText().toString());
        EditText et_num2 = (EditText) findViewById(R.id.num2);
        double num2 = Double.parseDouble(et_num2.getText().toString());

        double result = 0.0;

        switch (op[i]){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        TextView tv_result = (TextView) findViewById(R.id.tv_result);
        tv_result.setText("Answer: " + (String.valueOf(result)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}