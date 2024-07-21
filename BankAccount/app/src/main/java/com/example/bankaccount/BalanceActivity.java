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

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_balance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void TWDAccount(View view){
        EditText money = (EditText) findViewById(R.id.amount);
        double transactionAmount = Double.parseDouble(money.getText().toString());

        Intent intent = new Intent();
        if(view.getId() == R.id.deposit){
            intent.putExtra("TWDResult", transactionAmount);
        } else if(view.getId() == R.id.withdraw){
            intent.putExtra("TWDResult", -transactionAmount);
        }

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}