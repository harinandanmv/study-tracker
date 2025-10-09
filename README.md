# 📚 Study-Tracker

**An intuitive and beginner-friendly Android app to help students manage their study schedule, track progress, and receive reminders for study sessions. Study smarter, not harder! 🚀**

---

## 🌟 Features

* **📖 Add Subjects**
  Easily add subjects with daily or weekly study goals to keep track of your learning plan.

* **📊 Progress Tracking**
  Mark topics as completed and visualize your progress with a simple progress bar, motivating you to stay on track.

* **📅 Calendar View**
  View your scheduled subjects on a calendar. Plan your week efficiently and never miss a study session.

* **⏰ Study Reminders**
  Set reminders for study slots and receive notifications at the scheduled time, ensuring consistency in learning.

* **🧩 Beginner-Friendly**
  No complex database required. Uses SharedPreferences for storing your subjects and progress.

---

## 🛠️ Technologies Used

* **Language:** Java
* **Platform:** Android Studio
* **Storage:** SharedPreferences (JSON-based storage)
* **Notifications:** AlarmManager & BroadcastReceiver
* **UI:** LinearLayouts, Buttons, EditTexts, CalendarView, ProgressBar

---

## 🚀 Getting Started

### Prerequisites

* Android Studio installed on your system
* Basic knowledge of Android app development
* Android device or emulator to run the app

### Installation

1. Clone the repository:

```bash
git clone https://github.com/harinandanmv/study-tracker.git
```

2. Open the project in **Android Studio**.

3. Build and run the app on an emulator or physical device.

---

## 🗂️ App Structure

```
com.example.study_tracker
│
├── MainActivity.java       # Dashboard linking all features
├── SubjectsActivity.java   # Add and view subjects
├── AddSubjectActivity.java # Add a new subject + set reminder
├── ProgressActivity.java   # Track completed topics
├── CalendarActivity.java   # View scheduled subjects
├── Subject.java            # Model class for Subject
├── SubjectStorage.java     # SharedPreferences storage helper
└── ReminderReceiver.java   # BroadcastReceiver for notifications
```

---

## ✨ How to Use

1. **Add Subjects 📖:** Click on "Subjects", add the subject name & goal, optionally set a reminder.
2. **Track Progress 📊:** Click on "Progress" to mark topics completed and see your progress bar update.
3. **View Calendar 📅:** Click on "Calendar" to see subjects scheduled per day.
4. **Get Reminders ⏰:** Notifications will trigger at the reminder time set for each subject, helping you stay consistent.

---

## 🌈 Future Improvements

* Assign subjects to specific dates in the calendar.
* Add weekly/daily recurring reminders.
* Add topic-level tracking inside each subject.
* Enhance UI with Material Design components for better aesthetics.

---

## 📄 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
