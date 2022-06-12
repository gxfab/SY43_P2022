package com.example.lafo_cheuse

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ToggleButton
import com.example.lafo_cheuse.fragment.view.BudgetSetterFragment

class CreateIncomeExpenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_income_expense)

        val categoryChooserButton : Button = findViewById<Button>(R.id.emojiButton)
        val confirmButton: Button = findViewById(R.id.buttonConfirmCreation4)
        val toggleButton : ToggleButton = findViewById(R.id.toggleButton2)

        categoryChooserButton.setOnClickListener {
            val intent = Intent(this, CategoryChooserActivity::class.java)
            startActivity(intent)
        }

        toggleButton.setOnClickListener {
            if(toggleButton.isChecked) {
                confirmButton.setBackgroundColor(Color.parseColor("#F91A1A"))
            } else {
                confirmButton.setBackgroundColor(Color.parseColor("#32F91A"))
            }
        }

        confirmButton.setOnClickListener{
            val intent = Intent(this, BudgetSetterFragment::class.java)
            startActivity(intent)
        }
    }
}