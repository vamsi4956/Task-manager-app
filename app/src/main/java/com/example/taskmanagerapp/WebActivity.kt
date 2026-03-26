package com.example.taskmanagerapp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val web = WebView(this)
        setContentView(web)

        web.settings.javaScriptEnabled = true
        web.loadUrl("https://www.google.com")
    }
}