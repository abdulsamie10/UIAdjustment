package com.example.androidappexercise

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var timeView: TextView
    private lateinit var dayView: TextView
    private lateinit var dateView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeView = findViewById(R.id.time)
        dayView = findViewById(R.id.dayView)
        dateView = findViewById(R.id.dateView)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = CardAdapter(8)
        recyclerView.adapter = adapter

        // Initialize date and time formats
        val handler = Handler(Looper.getMainLooper())
        val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        // Update time, day, and date in real-time
        handler.post(object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val date = Date(currentTime)
                val formattedDate = dateFormat.format(date)
                val formattedTime = timeFormat.format(date)
                val formattedDay = dayFormat.format(date)

                // Update views
                timeView.text = formattedTime
                dayView.text = formattedDay
                dateView.text = formattedDate

                // Repeat the update every second
                handler.postDelayed(this, 1000)
            }
        })
    }
}

