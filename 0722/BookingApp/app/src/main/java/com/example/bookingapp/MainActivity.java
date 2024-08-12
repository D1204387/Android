package com.example.bookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    private int amount;
    private String etItem;
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
                        if (o.getData() != null && o.getResultCode() == Activity.RESULT_OK) {
                            etItem = o.getData().getStringExtra("ITEM");
                            amount = o.getData().getIntExtra("AMOUNT", 0);
                            TextView tv_sum = (TextView) findViewById(R.id.et_sum);
                            int sum = Integer.parseInt(tv_sum.getText().toString());

                            switch (etItem) {
                                case "食":

                                    TextView tv_meal = (TextView) findViewById(R.id.et_meal_amount);
                                    int mealBalance = Integer.parseInt(tv_meal.getText().toString());
                                    mealBalance += amount;
                                    tv_meal.setText(String.valueOf(mealBalance));
                                    tv_sum.setText(String.valueOf(sum + amount));
                                    break;
                                case "衣":
                                    TextView tv_clothes = (TextView) findViewById(R.id.et_clothes_amount);
                                    int clothesBalance = Integer.parseInt(tv_clothes.getText().toString());
                                    clothesBalance += amount;
                                    tv_clothes.setText(String.valueOf(clothesBalance));
                                    tv_sum.setText(String.valueOf(sum + amount));

                                    break;
                                default:
                                    TextView tv_trans = (TextView) findViewById(R.id.et_trans_amount);
                                    int transBalance = Integer.parseInt(tv_trans.getText().toString());
                                    transBalance += amount;
                                    tv_trans.setText(String.valueOf(transBalance));
                                    tv_sum.setText(String.valueOf(sum + amount));
                                    break;
                            }


                        }

                    }
                }
        );


    }

        public void NewExpenditure(View view){
        //按下按鈕"新增資料",跳到NewExpense
        Intent intent = new Intent(this, NewExpense.class);
        intentActivityResultLauncher.launch(intent);
    }
}