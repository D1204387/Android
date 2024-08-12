package com.example.bank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExchangeActivity extends AppCompatActivity {
    private String coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exchange);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            coin = String.format(bundle.getString("COIN"));

            //set Name
            TextView tv_title = (TextView) findViewById(R.id.exTitle);
            Button tontdButton = (Button) findViewById(R.id.toNTD);
            Button ntdtoButton = (Button) findViewById(R.id.NTDto);

            if (coin.equals("USD")) {
                tv_title.setText("美金換匯");
                tontdButton.setText("美金換新台幣");
                ntdtoButton.setText("新台幣換美金");
            } else {
                tv_title.setText("日圓換匯");
                tontdButton.setText("日圓換新台幣");
                ntdtoButton.setText("新台幣換日圓");
            }
        }
    }

    public void exchangeCoin(View view){
        EditText et_amount = (EditText)findViewById(R.id.exchange_amount);
        EditText et_rate = (EditText)findViewById(R.id.exchangeRate);

        Intent intent = new Intent();

        if(view.getId() == R.id.toNTD) {
            intent.putExtra("ACTION", "toNTD");
        }else {
            intent.putExtra("ACTION", "NTDto");
        }

         intent.putExtra("COIN",coin);
         intent.putExtra("AMOUNT",Double.parseDouble(et_amount.getText().toString()));
         intent.putExtra("RATE",Double.parseDouble(et_rate.getText().toString()));
         setResult(Activity.RESULT_OK, intent); // // 設置結果為 RESULT_OK 並將 Intent 返回給調用的 Activity
         finish();// 結束當前的 Activity，返回到前一個 Activity
         }
    }
