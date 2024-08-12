package com.example.bank;

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

public class NTDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ntdactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void GoBack(View view){
        double amount = Double.parseDouble(((EditText)findViewById(R.id.amount_NTD)).getText().toString());

        Intent intent = new Intent();// 創建一個新的 Intent，用於返回數據

        if(view.getId() == R.id.deposit){// 判斷是存款按鈕還是取款按鈕被點擊
            intent.putExtra("ACTION", "deposit"); // 如果是存款，將 "deposit" 字符串附加到 Intent 中，標記行為是存款
        }else{
            intent.putExtra("ACTION", "withdraw");
        }

        intent.putExtra("AMOUNT", amount); // 將金額附加到 Intent 中
        setResult(Activity.RESULT_OK, intent); // 設置結果為 RESULT_OK 並將 Intent 返回給調用的 Activity
        finish();// 結束當前的 Activity，返回到前一個 Activity
    }
}