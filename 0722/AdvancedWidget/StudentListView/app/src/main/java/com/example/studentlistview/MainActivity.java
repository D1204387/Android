package com.example.studentlistview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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

        ArrayList<Student> studentsArray = new ArrayList<Student>();
        studentsArray.add(new Student("Alice",1));
        studentsArray.add(new Student("Bob",2));
        studentsArray.add(new Student("Charlie",3));
        studentsArray.add(new Student("Tom",4));
        StudentAdapter adapter = new StudentAdapter(this, studentsArray);
        ListView studentListView = (ListView) findViewById(R.id.studentListView);
        studentListView.setAdapter(adapter);
    }
}