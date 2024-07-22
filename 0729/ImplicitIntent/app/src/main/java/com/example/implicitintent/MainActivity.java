package com.example.implicitintent;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private View view;

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

    public void GotoFCU(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.fcu.edu.tw"));
        startActivity(i);

    }

    public void GotoMap(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:0,0?q=逢甲大學"));
        startActivity(i);
    }

    public void clickDIAL(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_DIAL);
        startActivity(i);
    }

    public void clickCALL(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel: +123456"));
        startActivity(i);
    }
    public void sendEmail(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:d1204387@o365.fcu.edu.tw"));
        startActivity(i);
    }

    public void searchWeb(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_WEB_SEARCH);
        String searchString = ((EditText)findViewById(R.id.searchText)).getText().toString();
        i.putExtra(SearchManager.QUERY, searchString);
        startActivity(i);
    }

    public void share(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        String shareString = ((EditText)findViewById(R.id.shareText)).getText().toString();
        i.putExtra(Intent.EXTRA_TEXT, shareString);
        startActivity(i);
    }


}