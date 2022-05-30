package fr.sy.lebudgetduzero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //Data nécessaire en test
    private val tx : Int = 60
    private val money : Float= 1566.85F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHomeView(null)
    }

    fun setHomeView(view: View?){
        setContentView(R.layout.view_overview)

        //Mise à jour de l'élément du taux restants dans le mois
        val textViewTaux : TextView = findViewById(R.id.B_DepenseTaux) as TextView
        textViewTaux.text= "Vous avez dépensé " + this.tx + "% de votre budget mensuel"

        //Mise à jour de l'argent restant
        val textViewArgent : TextView = findViewById(R.id.B_MoneyAvailable) as TextView
        textViewArgent.text= "${this.money}€ disponible"

    }

    fun setBalanceView(view: View){
        setContentView(R.layout.view_balance)
    }

    fun setEntreeView(view: View){
        setContentView(R.layout.view_entrees)
    }

    fun setDepenseView(view: View){
        setContentView(R.layout.view_depense)
    }

}