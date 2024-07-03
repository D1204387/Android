package com.example.temperatureconvert;

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

    public void convertToC(View view){

        Button convertToCButton = (Button)findViewById(R.id.convertToC);
        EditText temperatureEditView = (EditText)findViewById(R.id.temperature);
        TextView resultView = (TextView)findViewById(R.id.resultView);

        double fTemperature = Double.parseDouble(temperatureEditView.getText().toString());
        double cTemperature = (fTemperature - 32 ) * 5/9;
        resultView.setText(String.valueOf(cTemperature));
    }

    public void convertToF(View view){

        Button convertToFButton = (Button)findViewById(R.id.convertToF);
        EditText temperatureEditView = (EditText)findViewById(R.id.temperature);
        TextView resultView = (TextView)findViewById(R.id.resultView);

        double cTemperature = Double.parseDouble(temperatureEditView.getText().toString());
        double fTemperature = cTemperature * 9/5 + 32;
        resultView.setText(String.valueOf(fTemperature));

    }
}