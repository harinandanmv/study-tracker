package com.example.study_tracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SubjectsActivity extends AppCompatActivity {

    private static final int REQ_ADD_SUBJECT = 101;
    private LinearLayout linearLayoutSubjects;
    private Button btnAddNew;
    private List<Subject> subjectList;
    private SubjectStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        linearLayoutSubjects = findViewById(R.id.linearLayoutSubjects);
        btnAddNew = findViewById(R.id.btnAddNew);

        storage = new SubjectStorage(this);
        subjectList = storage.loadSubjects();
        showSubjects();

        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(SubjectsActivity.this, AddSubjectActivity.class);
            startActivityForResult(intent, REQ_ADD_SUBJECT);
        });
    }

    private void showSubjects() {
        linearLayoutSubjects.removeAllViews();
        for (Subject s : subjectList) {
            TextView tv = new TextView(this);
            tv.setText(s.getName() + " — " + s.getGoal());
            tv.setTextSize(18f);
            tv.setPadding(12, 18, 12, 18);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(0, 0, 0, 12);
            tv.setLayoutParams(lp);

            linearLayoutSubjects.addView(tv);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ADD_SUBJECT && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("subjectName");
            String goal = data.getStringExtra("subjectGoal");
            int hour = data.getIntExtra("reminderHour", -1);
            int minute = data.getIntExtra("reminderMinute", -1);

            if (!name.trim().isEmpty() && !goal.trim().isEmpty()) {
                Subject newSubject = new Subject(name.trim(), goal.trim());
                newSubject.setReminderHour(hour);
                newSubject.setReminderMinute(minute);
                subjectList.add(newSubject);
                storage.saveSubjects(subjectList);
                showSubjects();

                // Schedule reminder if time set
                if (hour >= 0 && minute >= 0) scheduleReminder(newSubject);
            }
        }
    }

    private void scheduleReminder(Subject subject) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, subject.getReminderHour());
        calendar.set(Calendar.MINUTE, subject.getReminderMinute());
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("subjectName", subject.getName());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
