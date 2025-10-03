package com.example.study_tracker;

public class Subject {
    private String name;
    private String goal;
    private boolean isCompleted;
    private int reminderHour;
    private int reminderMinute;

    public Subject(String name, String goal) {
        this.name = name;
        this.goal = goal;
        this.isCompleted = false;
        this.reminderHour = -1;
        this.reminderMinute = -1;
    }

    public String getName() { return name; }
    public String getGoal() { return goal; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public int getReminderHour() { return reminderHour; }
    public void setReminderHour(int reminderHour) { this.reminderHour = reminderHour; }

    public int getReminderMinute() { return reminderMinute; }
    public void setReminderMinute(int reminderMinute) { this.reminderMinute = reminderMinute; }
}
