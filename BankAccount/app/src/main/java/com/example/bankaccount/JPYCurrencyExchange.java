package com.example.bankaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JPYCurrencyExchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jpycurrencyexchange);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void JPYExchange(View view){
        EditText money = (EditText) findViewById(R.id.amount);
        EditText JPYRate = (EditText) findViewById(R.id.JPYRate);

        double rate = Double.parseDouble(JPYRate.getText().toString());
        double JtoT_Exchange = 0;
        double TtoJ_Exchange = 0;

        double JPY = 0;
        double TWD = 0;

        if(view.getId() == R.id.JtoT) {
            JPY = Double.parseDouble(money.getText().toString());
            JtoT_Exchange = JPY * rate;
        } else if(view.getId() == R.id.TtoJ) {
            TWD = Double.parseDouble(money.getText().toString());
            TtoJ_Exchange = TWD / rate;
        }

        Intent intent = new Intent();
        intent.putExtra("JtoT_JPY", JPY);
        intent.putExtra("TtoJ_TWD", TWD);
        intent.putExtra("JtoT_ExchangeResult", JtoT_Exchange);
        intent.putExtra("TtoJ_ExchangeResult", TtoJ_Exchange);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}
