package com.example.bankaccount;

import android.content.Intent;
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

    public void deposit(View View){

        EditText amt = (EditText) findViewById(R.id.amount);

        if



    }

    public void withdraw(View View){

    }
}