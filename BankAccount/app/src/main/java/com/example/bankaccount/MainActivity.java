package com.example.bankaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private double total;
    private ActivityResultLauncher<Intent> intentActivityResultLanucher;
    private TextView value;


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

        intentActivityResultLanucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        // 這裡寫另一個Activity回傳後，獲取資料並處理的地方
                        if(o.getData() != null && o.getResultCode() == Activity.RESULT_OK){
                            bmi = o.getData().getDoubleExtra("BMI", -1);
                            updateUI();
                        }
                    }
                }
        );
    }

    public void GotoCalBalance(View view){
        Intent intent = new Intent(this, BalanceActivity.class);

        if

    }

    public void deposit(View View){

        EditText et_amt = (EditText) findViewById(R.id.amount);
        double add = Double.parseDouble(et_amt.getText().toString());

        total = 0;
        total += add;

        Intent intent = new Intent();
        intent.putExtra("Total", total);
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void withdraw(View View){

        EditText et_amt = (EditText) findViewById(R.id.amount);
        double deduct = Double.parseDouble(et_amt.getText().toString());

        total = 0;
        total -= deduct;

        Intent intent = new Intent();
        intent.putExtra("Total", total);
        setResult(Activity.RESULT_OK);
        finish();

    }
}