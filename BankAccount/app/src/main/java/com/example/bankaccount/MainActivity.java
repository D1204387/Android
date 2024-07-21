package com.example.bankaccount;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> intentActivityResultLanucher;
    private double TWDTotal = 0;
    private double JPYTotal = 0;
    private double USDTotal = 0;
    private int count = 0;


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
                        if (o.getData() != null && o.getResultCode() == Activity.RESULT_OK) {
                            processActivityResult(o.getData());
                        }
                    }
                }
        );
    }

    public void processActivityResult(Intent data) {
        double twdResult = data.getDoubleExtra("TWDResult", 0);
        if (twdResult != 0) {
            if (TWDTotal + twdResult < 0) {
                count += 1;
            } else {
                TWDTotal += twdResult;
                count = 0;
            }
        }

        double JtoTExchangeResult = data.getDoubleExtra("JtoT_ExchangeResult", 0);
        double TtoJExchangeResult = data.getDoubleExtra("TtoJ_ExchangeResult", 0);

        if (JtoTExchangeResult != 0) {
            double JtoT_JPYAmount = data.getDoubleExtra("JtoT_JPY", 0);
            if (JPYTotal - JtoT_JPYAmount < 0) {
                count += 1;
            } else {
                JPYTotal -= JtoT_JPYAmount;
                TWDTotal += JtoTExchangeResult;
                count = 0;
            }
        }

        double UtoTExchangeResult = data.getDoubleExtra("UtoT_ExchangeResult", 0);
        double TtoUExchangeResult = data.getDoubleExtra("TtoU_ExchangeResult", 0);

        if (JtoTExchangeResult != 0) {
            double UtoT_USDAmount = data.getDoubleExtra("UtoT_JPY", 0);
            if (USDTotal - UtoT_USDAmount < 0) {
                count += 1;
            } else {
                USDTotal -= UtoT_USDAmount;
                TWDTotal += UtoTExchangeResult;
                count = 0;
            }
        } else if (TtoUExchangeResult != 0) {
            double TtoU_TWDAmount = data.getDoubleExtra("TtoU_TWD", 0);
            if (TWDTotal - TtoU_TWDAmount < 0) {
                count += 1;
            } else {
                TWDTotal -= TtoU_TWDAmount;
                USDTotal += TtoUExchangeResult;
                count = 0;
            }
        }

        updateUI();

    }

    public void GotoTWDAccount(View view) {
        Intent intent = new Intent(this, BalanceActivity.class);
        intentActivityResultLanucher.launch(intent);
    }

    public void GotoJPYExchange(View view) {
        Intent intent = new Intent(this, JPYCurrencyExchange.class);
        intentActivityResultLanucher.launch(intent);
    }

    public void GotoUSDExchange(View view) {
        Intent intent = new Intent(this, USDCurrencyExchange.class);
        intentActivityResultLanucher.launch(intent);
    }

    public void updateUI() {
        TextView NTD_result = (TextView) findViewById(R.id.NTDResult);
        NTD_result.setText(String.valueOf(TWDTotal));

        TextView JPY_result = (TextView) findViewById(R.id.JPYResult);
        JPY_result.setText(String.valueOf(JPYTotal));

        TextView USD_result = (TextView) findViewById(R.id.USDResult);
        USD_result.setText(String.valueOf(USDTotal));

        TextView TextResult = (TextView) findViewById(R.id.Result);
        if (count != 0) {
            TextResult.setText("餘額不足,交易失敗");
            TextResult.setTextColor(Color.parseColor("#CC0000"));
        } else {
            TextResult.setText("交易成功");
            TextResult.setTextColor(Color.parseColor("#00CC00"));
        }
    }
}