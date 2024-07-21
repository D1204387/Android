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

public class USDCurrencyExchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usdcurrencyexchange);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void USDExchange(View view) {
        EditText money = (EditText) findViewById(R.id.amount);
        EditText USDRate = (EditText) findViewById(R.id.USDRate);

        double rate = Double.parseDouble(USDRate.getText().toString());
        double UtoT_Exchange = 0;
        double TtoU_Exchange = 0;

        double USD = 0;
        double TWD = 0;

        if (view.getId() == R.id.UtoT) {
            USD = Double.parseDouble(money.getText().toString());
            UtoT_Exchange = USD * rate;
        } else if (view.getId() == R.id.TtoU) {
            TWD = Double.parseDouble(money.getText().toString());
            TtoU_Exchange = TWD / rate;
        }

        Intent intent = new Intent();
        intent.putExtra("UtoT_USD", USD);
        intent.putExtra("TtoU_TWD", TWD);
        intent.putExtra("UtoT_ExchangeResult", UtoT_Exchange);
        intent.putExtra("TtoU_ExchangeResult", TtoU_Exchange);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}