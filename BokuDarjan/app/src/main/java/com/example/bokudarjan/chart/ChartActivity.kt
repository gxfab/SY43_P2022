package com.example.bokudarjan.chart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bokudarjan.R

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ChartActivity", "Entering chart activity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
    }
}