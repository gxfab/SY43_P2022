package com.example.zeroday.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.zeroday.R

class CycleSettingActivity : AppCompatActivity() {

    val cycles = arrayOf("Yearly", "Monthly", "Semi-Monthly", "Weekly")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_setting)
        val name = getIntent().getStringExtra("userName")

        val cycleSpinner = findViewById<Spinner>(R.id.cycle_setting_cycle)
        val cycleArray = ArrayAdapter(this, android.R.layout.simple_spinner_item, cycles)
        cycleArray.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        cycleSpinner.adapter = cycleArray

        findViewById<Button>(R.id.cycle_setting_continue_button).setOnClickListener()
        {

        }
    }
}