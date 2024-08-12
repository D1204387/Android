package com.example.activitypractice_2nd;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //這行程式碼使用 this.getIntent().getExtras() 來獲取從上一個 Activity 傳遞過來的 Bundle 資料。
        // this 代表當前的 Activity，getIntent() 取得這個 Activity 是由哪個 Intent 啟動的，
        // 而 getExtras() 則從這個 Intent 中提取附加的 Bundle 資料。

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            String fruit = String.format(bundle.getString("Fruit"));

            //set Name
            TextView name = (TextView) findViewById(R.id.Name);
            name.setText(fruit);

            //set image
            ImageView img = (ImageView) findViewById(R.id.image);
            String filename = fruit.toLowerCase();
//            使用 getResources().getIdentifier() 方法根據 filename（即小寫的水果名稱）
//             來獲取對應的圖片資源 ID
//            getIdentifier() 方法的參數包括資源名稱（這裡是 filename）、資源類型
//             （這裡是 "drawable"）、以及包名（通過 getPackageName() 獲取當前應用的包名）
//            使用 ContextCompat.getDrawable(this, imgId) 方法來獲取對應的 Drawable 資源，
//             這個方法能確保在各種版本的 Android 系統中都能正確地獲取資源。
//            最後，使用 setImageDrawable(drawableImg) 方法將獲取到的 Drawable 資源設置為
//             ImageView 的顯示圖片。
            int imgId = getResources().getIdentifier(filename, "drawable",getPackageName());


            Drawable drawableImg = ContextCompat.getDrawable(this, imgId);

            img.setImageDrawable(drawableImg);

        }
    }

    public void CloseActivity(View view){
        finish();
    }
}