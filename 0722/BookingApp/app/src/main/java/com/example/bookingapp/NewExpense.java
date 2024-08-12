package com.example.bookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewExpense extends AppCompatActivity {
    public String item;
    public int meal_amt = 0;
    public int clothes_amt = 0;
    public int trans_amt = 0;
//    public int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        }


    public void submitExpense(View view){
        EditText etItem = (EditText)findViewById(R.id.item);
        EditText etAmount = (EditText)findViewById(R.id.amt);

        Intent intent = new Intent();

        intent.putExtra("ITEM",etItem.getText().toString());
        intent.putExtra("AMOUNT",Integer.parseInt(etAmount.getText().toString()));

        setResult(Activity.RESULT_OK,intent);
        finish();

    }
}