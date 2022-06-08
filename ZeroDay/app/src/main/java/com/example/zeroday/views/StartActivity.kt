package com.example.zeroday.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.zeroday.R
import android.content.Intent
import android.graphics.Color
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<Button>(R.id.start_next_button).setOnClickListener(){
            val nameObject = findViewById<EditText>(R.id.start_name_edit)
            val name = nameObject.text.toString()

            if(name.isNotEmpty())
            {
                val intent = Intent(this, NameValidationActivity::class.java).apply {
                    putExtra("userName", name)
                }
                startActivity(intent)
            }
            else
            {
                nameObject.setHint("Enter your name")
                nameObject.setHintTextColor(Color.parseColor("#88FF0000"))
            }
        }
    }

}