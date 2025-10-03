package com.example.study_tracker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView listViewSubjects;
    private SubjectStorage storage;
    private List<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        listViewSubjects = findViewById(R.id.listViewSubjects);

        storage = new SubjectStorage(this);
        subjectList = storage.loadSubjects();

        // Show subjects for selected date
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String dateKey = getDateKey(year, month, dayOfMonth);
            showSubjectsForDate(dateKey);
        });

        // Initialize today's date
        Date today = new Date(calendarView.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        showSubjectsForDate(sdf.format(today));
    }

    private String getDateKey(int year, int month, int day) {
        // Month is 0-based
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);
    }

    private void showSubjectsForDate(String dateKey) {
        // For simplicity, assign all subjects to any date when clicked
        List<String> names = new ArrayList<>();
        for (Subject s : subjectList) {
            names.add(s.getName() + " — " + s.getGoal());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listViewSubjects.setAdapter(adapter);

        // Optional: allow user to pick which subjects to assign in the future
        listViewSubjects.setOnItemClickListener((parent, view, position, id) -> {
            Subject clicked = subjectList.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Study Subject")
                    .setMessage("You selected: " + clicked.getName())
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
