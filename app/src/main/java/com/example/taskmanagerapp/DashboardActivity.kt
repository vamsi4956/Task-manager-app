package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val username = intent.getStringExtra("USERNAME")
        Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TaskFragment())
            .commit()
        startReminderWork()
    }

    private fun startReminderWork() {
        val workRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "task_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}