package com.example.zeroday.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.zeroday.R

class NameValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_validation)
        val name = getIntent().getStringExtra("userName")

        //On affect le lancement de l'activité ExpenseActivity au bouton "-->"
        val nextButton = findViewById<ImageButton>(R.id.name_validation_button).setOnClickListener(){

            val intent = Intent(this,ExpenseActivity::class.java).apply {
                putExtra("inputType",false) // "false" indique le mode "income"
                putExtra("outputType", true) // "true" Indique que la sortie est l'activité de définition du cycle


            }
            startActivity(intent)
        }
    }
}