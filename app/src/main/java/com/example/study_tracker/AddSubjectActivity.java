package com.example.study_tracker;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText editSubjectName, editSubjectGoal;
    private Button btnAddSubject, btnPickTime;
    private int hour = -1, minute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        editSubjectName = findViewById(R.id.editSubjectName);
        editSubjectGoal = findViewById(R.id.editSubjectGoal);
        btnAddSubject = findViewById(R.id.btnAddSubject);
        btnPickTime = findViewById(R.id.btnPickTime);

        btnPickTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (TimePicker view, int h, int m) -> {
                        hour = h;
                        minute = m;
                        btnPickTime.setText(String.format(Locale.getDefault(), "Reminder: %02d:%02d", hour, minute));
                    }, 8, 0, true);
            timePickerDialog.show();
        });

        btnAddSubject.setOnClickListener(v -> {
            String name = editSubjectName.getText().toString().trim();
            String goal = editSubjectGoal.getText().toString().trim();

            if (!name.isEmpty() && !goal.isEmpty()) {
                Intent result = new Intent();
                result.putExtra("subjectName", name);
                result.putExtra("subjectGoal", goal);
                result.putExtra("reminderHour", hour);
                result.putExtra("reminderMinute", minute);
                setResult(RESULT_OK, result);
                finish();
            } else {
                if (name.isEmpty()) editSubjectName.setError("Enter subject name");
                if (goal.isEmpty()) editSubjectGoal.setError("Enter study goal");
            }
        });
    }
}
