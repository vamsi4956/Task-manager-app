package com.example.taskmanagerapp

import android.content.Context
import androidx.work.*

class ReminderWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    override fun doWork(): Result {
        println("Reminder: Do your tasks!")
        return Result.success()
    }
}