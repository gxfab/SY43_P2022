package com.example.alovemony_ip.connexionactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.model.IncomeModel
import com.example.alovemony_ip.repository.IncomeRepository
import java.util.*

class ConnexionMoneyGained() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.connexion_money_gained)

        //on assigne le changement d'activité au bouton "Add"
        val button_add_source_money = findViewById<Button>(R.id.account_add_source_money_button)
        button_add_source_money.setOnClickListener {
            sendForm()
        }
        //on assigne le changement d'activité au bouton "Next"
        val button_money_gained_to_levy = findViewById<Button>(R.id.account_next_button_money_gained)
        button_money_gained_to_levy.setOnClickListener {
            val intent: Intent = Intent(this, ConnexionPrevAutoActivity::class.java)
            startActivity(intent)
        }

        //on assigne le changement d'activité au bouton "Back"
        val button_money_gained_to_income = findViewById<Button>(R.id.account_back_button_money_gained)
        button_money_gained_to_income.setOnClickListener {
            val intent : Intent = Intent(this, ConnexionIncomeActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)

    }

    /*
      Fonction permettant d'actualiser les données de IncomList en fonction de ce que rentre l'utlisateur
     */
    fun sendForm()
    {

        val incomeRepository = IncomeRepository()

        val incomeNameSpinner = findViewById<Spinner>(R.id.account_source_money)
        val incomeName = incomeNameSpinner.selectedItem.toString()

        val incomeMontantEdit = findViewById<EditText>(R.id.account_source_money_montant)
        val incomeMontant = incomeMontantEdit.text.toString()
        val incomeMontantValue = Integer.parseInt(incomeMontant)

        // On ajoute une "income" à notre base de donnée en utilisant l'IncomeModel, à partir de ce qu'à rentrer l'utilisateur
        // on met "true" à type, afin que ce dernier soit ajouté de l'income totale
        val income = IncomeModel(
            UUID.randomUUID().toString(),
            incomeName,
            incomeMontantValue,
            true
        )
        incomeRepository.insertIncome(income)
        incomeMontantEdit.setText("")


    }
}