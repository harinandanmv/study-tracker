package com.example.study_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSubjects, btnProgress, btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubjects = findViewById(R.id.btnSubjects);
        btnProgress = findViewById(R.id.btnProgress);
        btnCalendar = findViewById(R.id.btnCalendar);

        btnSubjects.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubjectsActivity.class);
            startActivity(intent);
        });

        btnProgress.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProgressActivity.class);
            startActivity(intent);
        });

        btnCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }
}
