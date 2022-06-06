package fr.sy.lebudgetduzero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import fr.sy.lebudgetduzero.fragments.IncomeFragment

/**
 *
 * Manage the main activity content.
 *
 * @constructor Set the content of the activity to Home View (overview layout)
 */
class MainActivity : AppCompatActivity() {
    //Data nécessaire en test
    private val tx : Int = 60
    private val money : Float= 1566.85F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHomeView(null)
    }

    /**
     * Set the content view as the home view using the overview layout.
     *
     * @param [view] Actual view of the app.
     */
    fun setHomeView(view: View?){
        setContentView(R.layout.view_overview)

        //Mise à jour de l'élément du taux restants dans le mois
        val textViewTaux : TextView = findViewById(R.id.B_DepenseTaux) as TextView
        textViewTaux.text= "Vous avez dépensé " + this.tx + "% de votre budget mensuel"

        //Mise à jour de l'argent restant
        val textViewArgent : TextView = findViewById(R.id.B_MoneyAvailable) as TextView
        textViewArgent.text= this.money.toString() + "€ disponible"

    }

    /**
     * Set the content view as the balance view using the overview layout.
     *
     * @param [view] Acual view of the app.
     */
    fun setBalanceView(view: View){
        setContentView(R.layout.view_balance)
    }

    /**
     * Set the content view as the input view using the overview layout.
     *
     * @param [view] Actual view of the app.
     */
    fun setEntreeView(view: View){
        setContentView(R.layout.view_entrees)

        //Injection du fragment dans la boite
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, IncomeFragment(this))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * Set the content view as the output view using the overview layout.
     *
     * @param [view] Actual view of the app.
     */
    fun setDepenseView(view: View){
        setContentView(R.layout.view_depense)
    }

}