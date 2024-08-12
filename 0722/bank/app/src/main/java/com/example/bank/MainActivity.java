package com.example.bank;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private double amount;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

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
        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //寫另一個Activity回傳後,得到回傳的資料之後的做法
                        if(o.getData() != null  && o.getResultCode() == Activity.RESULT_OK){
                            amount = o.getData().getDoubleExtra("AMOUNT",0);
                            String action = o.getData().getStringExtra("ACTION");
                            String coin = o.getData().getStringExtra("COIN");
                            TextView tv_ntdBalance = (TextView)findViewById(R.id.balance_NTD);
                            TextView tv_result = (TextView)findViewById(R.id.result);

                            double ntdBalance = Double.parseDouble(tv_ntdBalance.getText().toString());

                            tv_result.setText("交易成功!");
                            tv_result.setTextColor(Color.parseColor("#33AA33"));

                            if(action.equals("deposit")){
                                tv_ntdBalance.setText(String.valueOf(amount + ntdBalance));
                            }else if(action.equals("withdraw")) {
                                if (amount > ntdBalance) {
                                    tv_result.setText("餘額不足,交易失敗!");
                                    tv_result.setTextColor(Color.parseColor("#AA3333"));
                                } else {
                                    tv_ntdBalance.setText(String.valueOf(ntdBalance - amount));

                                }
                            }else if(action.equals("toNTD")) {
                                double rate = o.getData().getDoubleExtra("RATE", 0);
                                if (coin.equals("USD")) {
                                    TextView tv_usdBalance = (TextView) findViewById(R.id.balance_USD);
                                    double usdBalance = Double.parseDouble(tv_usdBalance.getText().toString());

                                    if (usdBalance >= amount) {
                                        double r = (usdBalance - amount) * rate;
                                        tv_ntdBalance.setText(String.valueOf(ntdBalance + r));
                                        tv_usdBalance.setText(String.valueOf(usdBalance - amount));
                                    } else {
                                        tv_result.setText("餘額不足,交易失敗!");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));

                                    }
                                } else {
                                    TextView tv_jpyBalance = (TextView) findViewById(R.id.balance_JPY);
                                    double jpyBalance = Double.parseDouble(tv_jpyBalance.getText().toString());

                                    if (jpyBalance >= amount) {
                                        double r = (jpyBalance - amount) * rate;
                                        tv_ntdBalance.setText(String.valueOf(ntdBalance + r));
                                        tv_jpyBalance.setText(String.valueOf(jpyBalance - amount));
                                    } else {
                                        tv_result.setText("餘額不足,交易失敗!");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));

                                    }
                                }
                            }else{
                                double rate = o.getData().getDoubleExtra("RATE",0);
                                if(coin.equals("USD")){
                                    TextView tv_usdBalance = (TextView) findViewById(R.id.balance_USD);
                                    double usdBalance = Double.parseDouble(tv_usdBalance.getText().toString());

                                    if (ntdBalance >= amount) {
                                        double r = (amount) / rate;
                                        tv_ntdBalance.setText(String.valueOf(ntdBalance - amount));
                                        tv_usdBalance.setText(String.valueOf(usdBalance + r));
                                    } else {
                                        tv_result.setText("餘額不足,交易失敗!");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));

                                    }
                                } else {
                                    TextView tv_jpyBalance = (TextView) findViewById(R.id.balance_JPY);
                                    double jpyBalance = Double.parseDouble(tv_jpyBalance.getText().toString());

                                    if (ntdBalance >= amount) {
                                        double r = (amount) / rate;
                                        tv_ntdBalance.setText(String.valueOf(ntdBalance - amount));
                                        tv_jpyBalance.setText(String.valueOf(jpyBalance + r));
                                    } else {
                                        tv_result.setText("餘額不足,交易失敗!");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));

                                    }

                                }
                            }
                        }
                    }
                }
        );
    }

    public void toNTDActivity(View view){
        Intent intent = new Intent(this, NTDActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void toExchangeActivity(View view){

        String coin;
        if(view.getId() == R.id.Exchange_USD){
            coin = "USD";
        } else{
            coin = "JPY";
        }

        Intent intent = new Intent(this, ExchangeActivity.class);
        // 設定一個bundle來放資料
        Bundle bundle = new Bundle();
        bundle.putString("COIN", coin);
        // 利用intent攜帶bundle的資料
        intent.putExtras(bundle);
        intentActivityResultLauncher.launch(intent);
    }
}