package com.example.bokudarjan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ChartActivity", "Entering chart activity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
    }
}