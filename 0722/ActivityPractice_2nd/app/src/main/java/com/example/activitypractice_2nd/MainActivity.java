package com.example.activitypractice_2nd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void SentFruitName(View view){
        //按下按鈕A,傳送"Apple",按下按鈕B,傳送"Banana"

        String fruit;
        if(view.getId() == R.id.buttonA) {
            fruit = "Apple";
        }else{
            fruit = "Banana";
        }

        Intent intent = new Intent(this, MainActivity2.class);

        //設定一個bundle來放資料

        Bundle bundle = new Bundle();
        bundle.putString("Fruit", fruit);

        //利用intent攜帶bundle的資料
        //使用 intent.putExtras(bundle) 方法將 Bundle 中的資料附加到 Intent 中。
        intent.putExtras(bundle);
        //然後使用 startActivity(intent) 方法啟動 MainActivity2，並將附帶的資料傳遞給它。
        startActivity(intent);
    }


}