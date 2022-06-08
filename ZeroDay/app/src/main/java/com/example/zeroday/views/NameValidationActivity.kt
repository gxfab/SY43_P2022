package com.example.zeroday.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.zeroday.R

class NameValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_validation)
        val name = getIntent().getStringExtra("userName")
        val nextButton = findViewById<ImageButton>(R.id.name_validation_button).setOnClickListener(){

        }
    }
}