package com.example.study_tracker;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProgressActivity extends AppCompatActivity {

    private LinearLayout linearLayoutProgress;
    private ProgressBar progressBar;
    private SubjectStorage storage;
    private List<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        linearLayoutProgress = findViewById(R.id.linearLayoutProgress);
        progressBar = findViewById(R.id.progressBar);

        storage = new SubjectStorage(this);
        subjectList = storage.loadSubjects();

        showProgress();
    }

    private void showProgress() {
        linearLayoutProgress.removeAllViews();

        int completedCount = 0;
        for (Subject s : subjectList) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, 12, 0, 12);

            TextView tv = new TextView(this);
            tv.setText(s.getName() + " — " + s.getGoal());
            tv.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            tv.setTextSize(16f);

            Button btnMark = new Button(this);
            btnMark.setText(s.isCompleted() ? "Completed" : "Mark Done");
            btnMark.setEnabled(!s.isCompleted());

            btnMark.setOnClickListener(v -> {
                s.setCompleted(true);
                storage.saveSubjects(subjectList); // save updated status
                showProgress(); // refresh list
            });

            if (s.isCompleted()) completedCount++;

            row.addView(tv);
            row.addView(btnMark);

            linearLayoutProgress.addView(row);
        }

        // Update ProgressBar
        int progress = subjectList.size() > 0 ? (completedCount * 100 / subjectList.size()) : 0;
        progressBar.setProgress(progress);
    }
}
