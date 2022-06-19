package com.example.alovemony_ip.connexionactivities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.repository.IncomeRepository
import com.example.alovemony_ip.repository.IncomeRepository.Singleton.incomeList
import java.text.SimpleDateFormat
import java.util.*

class ConnexionIncomeActivity() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.connexion_income)
        val income = findViewById<EditText>(R.id.account_income)
        var calendar = Calendar.getInstance()
        val dateIncome = findViewById<EditText>(R.id.account_date_income)

        /*
            On code le calendrier, qui permettra à l'utilisateur de selectionner une date de manière simplifié
         */
        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                val myFormat = "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat(myFormat, Locale.UK)

                dateIncome.setText(dateFormat.format(calendar.time))
            }
        }

        dateIncome.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View)
            {
                DatePickerDialog(this@ConnexionIncomeActivity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        })


        // on assigne le changement d'activité au bouton "Next"
        val button_income_to_money_gained = findViewById<Button>(R.id.account_next_button_income)
        button_income_to_money_gained.setOnClickListener {

            //on vérifie que l'utilisateur à bien remplir l'EditText
            if(income.text.toString() == "") {
                income.error = "Veuillez entrer une réponse "
                System.out.println(" tesst :  " + income.text)

            }else{
                sendForm()
                val intent: Intent = Intent(this, ConnexionMoneyGained::class.java)
                startActivity(intent)
            }
        }
        //on assigne le changement d'activité au bouton "Back
        val button_income_to_name = findViewById<Button>(R.id.account_back_button_income)
        button_income_to_name.setOnClickListener {
            val intent : Intent = Intent(this, ConnexionActivity::class.java)
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

        val incomeName = "salary"
        val incomeMontant = findViewById<EditText>(R.id.account_income).text.toString()
        val incomeMontantValue = Integer.parseInt(incomeMontant)

        for(i in incomeList)
        {
            // on affecte le montant du salaire indiqué par l'utilisateur
            if(i.name == "salary"){
                i.montant = incomeMontantValue
                incomeRepository.updateIncome(i)
            }
        }
    }
}