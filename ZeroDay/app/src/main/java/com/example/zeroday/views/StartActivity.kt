package com.example.zeroday.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.zeroday.R
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import com.example.zeroday.seeders.ExpenseCategorySeeder
import com.example.zeroday.seeders.IncomeCategorySeeder
import com.example.zeroday.services.ExpenseCategoryService
import com.example.zeroday.services.ExpenseService

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        // Si la base de donnée n'a pas encore été initialisée ( par exemple liste des catégories vide ), on l'initialise
        if (ExpenseCategoryService(this).all == null)
        {
            IncomeCategorySeeder(this).run();
            ExpenseCategorySeeder(this).run();
        }


        //On récupère le bouton "next" et on lui affacte le lancement de l'activité de validation
        findViewById<Button>(R.id.start_next_button).setOnClickListener(){
            val nameObject = findViewById<EditText>(R.id.start_name_edit)
            val name = nameObject.text.toString()
            //L'action ne se réalise que si l'utilisateur à bien rentré son nom
            if(name.isNotEmpty())
            {
                val intent = Intent(this, NameValidationActivity::class.java).apply {
                    putExtra("userName", name)
                }
                startActivity(intent)
            }
            else
            //Sinon on met le hint du edittext à jour pour indiquer à l'utilisateur qu'il doit rentrer son nom avant de poursuivre
            {
                nameObject.setHint("Enter your name")
                nameObject.setHintTextColor(Color.parseColor("#88FF0000"))
            }
        }
    }

}