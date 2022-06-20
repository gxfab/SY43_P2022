package com.example.zeroday.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.zeroday.R

//Activité permettant d'afficher
class StartTotalIncomesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_total_incomes)

        //Affectation du lancement de l'activité suivante au bouton "continue"
        findViewById<Button>(R.id.total_incomes_continue_button).setOnClickListener {
            val intent = Intent(this,ExpenseActivity::class.java).apply {
                putExtra("inputType",true)
                putExtra("outputType",true)
            }
            startActivity(intent)
        }
    }
}