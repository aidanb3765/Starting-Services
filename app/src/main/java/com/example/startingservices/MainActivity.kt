package com.example.startingservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val countdownValue = editText.text.toString().toIntOrNull() ?: 0

            val intent = Intent(this, CountdownService::class.java)
            intent.putExtra(CountdownService.COUNTDOWN_VALUE, countdownValue)
            startService(intent)
        }
    }
}
