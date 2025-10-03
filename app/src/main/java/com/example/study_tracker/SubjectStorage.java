package com.example.study_tracker;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubjectStorage {

    private static final String PREFS_NAME = "study_prefs";
    private static final String KEY_SUBJECTS = "subjects";

    private SharedPreferences prefs;

    public SubjectStorage(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSubjects(List<Subject> subjects) {
        try {
            JSONArray array = new JSONArray();
            for (Subject s : subjects) {
                JSONObject obj = new JSONObject();
                obj.put("name", s.getName());
                obj.put("goal", s.getGoal());
                obj.put("completed", s.isCompleted());
                obj.put("hour", s.getReminderHour());
                obj.put("minute", s.getReminderMinute());
                array.put(obj);
            }
            prefs.edit().putString(KEY_SUBJECTS, array.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Subject> loadSubjects() {
        List<Subject> list = new ArrayList<>();
        String json = prefs.getString(KEY_SUBJECTS, "");
        if (!json.isEmpty()) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Subject s = new Subject(obj.getString("name"), obj.getString("goal"));
                    s.setCompleted(obj.getBoolean("completed"));
                    s.setReminderHour(obj.getInt("hour"));
                    s.setReminderMinute(obj.getInt("minute"));
                    list.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
