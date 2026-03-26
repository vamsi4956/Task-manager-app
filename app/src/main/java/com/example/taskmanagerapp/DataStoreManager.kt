package com.example.taskmanagerapp

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("settings")

suspend fun saveName(context: Context, name: String) {
    val key = stringPreferencesKey("username")
    context.dataStore.edit {
        it[key] = name
    }
}