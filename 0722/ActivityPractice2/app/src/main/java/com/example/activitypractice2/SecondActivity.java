package com.example.activitypractice2;

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

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    String fruit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            String fruit = String.format(bundle.getString("Fruit"));

            TextView name = (TextView) findViewById(R.id.Name);
            name.setText(fruit);

            ImageView img =(ImageView) findViewById(R.id.image);
            String fileName = fruit.toLowerCase();
            int imgId = getResources().getIdentifier(fileName,"drawable", getPackageName());
            Drawable drawableImg = ContextCompat.getDrawable(this, imgId);
            img.setImageDrawable(drawableImg);
        }
    }

    public void CloseActivity(View view){
        finish();

    }

}