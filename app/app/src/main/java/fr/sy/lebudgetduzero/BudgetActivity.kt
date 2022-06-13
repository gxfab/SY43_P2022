package fr.sy.lebudgetduzero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import fr.sy.lebudgetduzero.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BudgetActivity : AppCompatActivity() {

    private val startMonthTimestamp:Int
        get() = ( (SimpleDateFormat("dd-MM-yyyy").parse("01-"+ LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("MM-yyyy")) ) ).time/1000).toInt()
    private val startLastMonthTimestamp:Int
        get() = ( (SimpleDateFormat("dd-MM-yyyy").parse("01-"+ LocalDateTime.now().minusMonths(1).format(
            DateTimeFormatter.ofPattern("MM-yyyy")) ) ).time/1000).toInt()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        //Calcul des timestamp


        //Traitement base de données
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            val db= AppDatabase.getDatabase(applicationContext)

            //Settings budget of spent
            for (idType in 1..11){

                //Database Request
                val requestInfo=db.balanceDao().selectTypeInfo(idType)
                val requestSpent=db.spentDao().getSpentValueBtwByType(this@BudgetActivity.startLastMonthTimestamp,this@BudgetActivity.startMonthTimestamp,idType)

                this@BudgetActivity.runOnUiThread{
                    //Setting category name
                    val idTitle = resources.getIdentifier(("titleType$idType"), "id", packageName)
                    (findViewById<TextView>(idTitle)).text=requestInfo.name

                    //Setting the current Type budget
                    val idInput = resources.getIdentifier(("valueType$idType"), "id", packageName)
                    (findViewById<EditText>(idInput)).text= Editable.Factory.getInstance().newEditable(requestInfo.value_for_month.toString())

                    //Setting LastMonth spend value
                    val idValue = resources.getIdentifier(("lastMonthType$idType"), "id", packageName)
                    (findViewById<TextView>(idValue)).text=requestSpent.toString() + "€ dépensés dans cette catégorie le mois passé."
                }



            }
            //Setting income
            val incomeLastMonth=db.incomeDao().getIncomeValueBtw(this@BudgetActivity.startLastMonthTimestamp,this@BudgetActivity.startMonthTimestamp)
            findViewById<TextView>(R.id.editIncome).text=Editable.Factory.getInstance().newEditable(incomeLastMonth.toString())
        }
    }
}