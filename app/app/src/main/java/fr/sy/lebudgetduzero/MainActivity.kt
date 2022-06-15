package fr.sy.lebudgetduzero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.sy.lebudgetduzero.database.AppDatabase
import fr.sy.lebudgetduzero.fragments.IncomeFragment
import fr.sy.lebudgetduzero.fragments.SpentFragment
import fr.sy.lebudgetduzero.item.IncomeItem
import fr.sy.lebudgetduzero.item.SpentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *
 * Manage the main activity content.
 *
 * @constructor Set the content of the activity to Home View (overview layout)
 */
class MainActivity : AppCompatActivity() {
    //Data nécessaire en test
    private val tx : Int = 60
    private val startMonthTimestamp:Int
        get() = ( (SimpleDateFormat("dd-MM-yyyy").parse("01-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy")) ) ).time/1000).toInt()
    private val currentTimestamp:Int
        get() = ( (SimpleDateFormat("dd-MM-yyyy").parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) ) ).time/1000).toInt()


    /**
     * On create of the main activty to show overview
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Traitement base de données
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {

            //Db first settings
            val db=AppDatabase.getDatabase(applicationContext)
            try{
                db.balanceDao().insertTypes()
            }catch (e: Exception) {}
        }

        //Affichage de l'overview
        setHomeView(null)
    }

    /**
     * Set the content view as the home view using the overview layout.
     *
     * @param [view] Actual view of the app.
     */
    fun setHomeView(view: View?){

        setContentView(R.layout.view_overview)

        //Traitement base de données
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            val db=AppDatabase.getDatabase(applicationContext)

            //Entrées du mois
            val incomeMonth=db.incomeDao().getIncomeValue(startMonthTimestamp)
            val textViewIncome : TextView = findViewById(R.id.I_ValueMonth) as TextView

            //Depenses du mois
            val spentMonth=db.spentDao().getSpentValue(startMonthTimestamp)
            val textViewSpent: TextView = findViewById(R.id.S_ValueMonth) as TextView

            //Argent disponible
            val money=db.incomeDao().getIncomeValue(0)-db.spentDao().getSpentValue(0)
            val textViewArgent : TextView = findViewById(R.id.B_MoneyAvailable) as TextView

            this@MainActivity.runOnUiThread{
                textViewIncome.text=incomeMonth.toString() + "€ en entrée ce mois."
                textViewSpent.text=spentMonth.toString() + "€ dépensés ce mois."
                textViewArgent.text= money.toString() + "€ disponible"
            }
        }


        //Mise à jour de l'élément du taux restants dans le mois
        val textViewTaux : TextView = findViewById(R.id.B_DepenseTaux) as TextView
        textViewTaux.text= "Vous avez dépensé " + this.tx + "% de votre budget mensuel"

    }

    /**
     * Set the content view as the balance view using the overview layout.
     *
     * @param [view] Acual view of the app.
     */
    fun setBalanceView(view: View){
        setContentView(R.layout.view_balance)

        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            //Ouverture DB
            val db = AppDatabase.getDatabase(applicationContext)

            //Mise en place de chaque type
            for (idType in 1..11) {
                var taux=0F
                var restant=0F

                //Requetes
                val requestInfo = db.balanceDao().selectTypeInfo(idType)
                val prevision = db.balanceDao().selectBudgetValueType(idType)
                val real = db.spentDao().getSpentValueBtwByType(startMonthTimestamp,currentTimestamp,idType)

                //Id des text view à remplir
                val idTitle = resources.getIdentifier(("typeTitle$idType"), "id", packageName)
                val idTaux = resources.getIdentifier(("typeTaux$idType"), "id", packageName)
                val idDetails = resources.getIdentifier(("typeRemaining$idType"), "id", packageName)

                //Construction du taux
                if(prevision!=0 && real!=0F){
                    taux=real/prevision*100
                }
                if(prevision.toFloat()>real){
                    restant=prevision.toFloat()-real
                }

                this@MainActivity.runOnUiThread {
                    (findViewById<TextView>(idTitle)).text=requestInfo.name
                    (findViewById<TextView>(idTaux)).text= "$taux %"
                    (findViewById<TextView>(idDetails)).text= "$real€ / $prevision€ \nIl vous reste $restant € à dépenser"
                }

            }
        }
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

        //Injection du fragment dans la boite
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, SpentFragment(this))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadAddActivity(view: View){
        val intent= Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun loadBudgetActivity(view: View){
        val intent= Intent(this, BudgetActivity::class.java)
        startActivity(intent)
    }

}