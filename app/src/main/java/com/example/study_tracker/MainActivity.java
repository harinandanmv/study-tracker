package com.example.study_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubjects = findViewById(R.id.btnSubjects);
        Button btnCalendar = findViewById(R.id.btnCalendar);
        Button btnProgress = findViewById(R.id.btnProgress);

        btnSubjects.setOnClickListener(v -> {
            // Later: open SubjectsActivity
        });

        btnCalendar.setOnClickListener(v -> {
            // Later: open CalendarActivity
        });

        btnProgress.setOnClickListener(v -> {
            // Later: open ProgressActivity
        });
    }
}
