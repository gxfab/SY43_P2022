
package com.example.alovemony_ip.connexionactivities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.MainActivity
import com.example.alovemony_ip.R
import com.example.alovemony_ip.model.IncomeModel
import com.example.alovemony_ip.repository.CategorieRepository
import com.example.alovemony_ip.repository.IncomeRepository
import com.example.alovemony_ip.repository.UserRepository
import com.example.alovemony_ip.repository.UserRepository.Singleton.userList
import java.util.*

class ConnexionPrevAutoActivity() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.connexion_prev_auto)

        val repo_categorie = CategorieRepository()
        val repo_income = IncomeRepository()
        val repo_user = UserRepository()

        //on assigne le changement d'activité au bouton "Next"
        val button_levy_to_categorie = findViewById<Button>(R.id.account_next_button_levy)
        button_levy_to_categorie.setOnClickListener {
            var incomeTotale = 0

            // on actualise le montant final de notre Income ( en se basant sur les précédentes  données
                repo_income.updateData {
                    for(i in IncomeRepository.Singleton.incomeList)
                    {
                        if(i.type)
                        {
                            incomeTotale += i.montant
                        }else{
                            incomeTotale -=  i.montant
                        }
                    }
                    // on les assigne à l'utilisateur
                    userList[0].incomeTotale = incomeTotale
                    userList[0].currentMoney = incomeTotale
                    repo_user.updateUser(userList[0])
                    // on actualise la base de donnée
                    repo_categorie.updateData {
                        val intent: Intent = Intent(this, ConnexionCategorieActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
        //on assigne le changement d'activité au bouton "Add"
        val button_ajout_prev_auto = findViewById<Button>(R.id.account_prev_auto_add)
        button_ajout_prev_auto.setOnClickListener {
                sendForm()
        }

        //on assigne le changement d'activité au bouton "Back"
        val button_prevauto_to_money_gained = findViewById<Button>(R.id.account_back_button_levy)
        button_prevauto_to_money_gained.setOnClickListener {
            val intent : Intent = Intent(this, ConnexionMoneyGained::class.java)
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

        val incomeName = findViewById<EditText>(R.id.connexion_prev_name_edit).text.toString()
        val incomeMontant = findViewById<EditText>(R.id.connexion_prev_montant_edit).text.toString()
        val incomeMontantValue = Integer.parseInt(incomeMontant)
        // On ajoute une "income" à notre base de donnée en utilisant l'IncomeModel, à partir de ce qu'à rentrer l'utilisateur
        // on met "false" à type, afin que ce dernier soit déduit de l'income totale
        val income = IncomeModel(
            UUID.randomUUID().toString(),
            incomeName,
            incomeMontantValue,
            false
        )
        incomeRepository.insertIncome(income)


    }
}