package com.example.taskmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val name = findViewById<EditText>(R.id.etName)
        val btn = findViewById<Button>(R.id.btnLogin)

        btn.setOnClickListener {

            val username = name.text.toString().trim()

            // ✅ Validation
            if (username.isEmpty()) {
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Save using DataStore
            lifecycleScope.launch {
                saveName(this@LoginActivity, username)
            }

            // ✅ Move to Dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
    }
}