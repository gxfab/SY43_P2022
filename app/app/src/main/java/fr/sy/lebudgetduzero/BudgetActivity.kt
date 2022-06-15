package fr.sy.lebudgetduzero

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.sy.lebudgetduzero.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BudgetActivity : AppCompatActivity() {

    private val startMonthTimestamp: Int
        get() = ((SimpleDateFormat("dd-MM-yyyy").parse(
            "01-" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("MM-yyyy")
            )
        )).time / 1000).toInt()
    private val startLastMonthTimestamp: Int
        get() = ((SimpleDateFormat("dd-MM-yyyy").parse(
            "01-" + LocalDateTime.now().minusMonths(1).format(
                DateTimeFormatter.ofPattern("MM-yyyy")
            )
        )).time / 1000).toInt()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        //Calcul des timestamp


        //Traitement base de données
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            //Settings budget of spent
            for (idType in 1..11) {

                //Database Request
                val requestInfo = db.balanceDao().selectTypeInfo(idType)
                val requestSpent = db.spentDao().getSpentValueBtwByType(
                    this@BudgetActivity.startLastMonthTimestamp,
                    this@BudgetActivity.startMonthTimestamp,
                    idType
                )

                this@BudgetActivity.runOnUiThread {
                    //Setting category name
                    val idTitle = resources.getIdentifier(("titleType$idType"), "id", packageName)
                    (findViewById<TextView>(idTitle)).text = requestInfo.name

                    //Setting the current Type budget
                    val idInput = resources.getIdentifier(("valueType$idType"), "id", packageName)
                    (findViewById<EditText>(idInput)).text = Editable.Factory.getInstance()
                        .newEditable(requestInfo.value_for_month.toString())

                    //Setting listener on change
                    (findViewById<EditText>(idInput)).addTextChangedListener(object : TextWatcher{
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {    }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }


                        override fun afterTextChanged(p0: Editable?) {
                            updateRemaining(null)
                        }
                    })

                    //Setting LastMonth spend value
                    val idValue =resources.getIdentifier(("lastMonthType$idType"), "id", packageName)
                    (findViewById<TextView>(idValue)).text =
                        requestSpent.toString() + "€ dépensés dans cette catégorie le mois passé."

                    updateRemaining(null)
                }
            }
            //Setting income
            val incomeLastMonth = db.incomeDao().getIncomeValueBtw(
                this@BudgetActivity.startLastMonthTimestamp,
                this@BudgetActivity.startMonthTimestamp
            )
            findViewById<EditText>(R.id.editIncome).text = Editable.Factory.getInstance().newEditable(incomeLastMonth.toString())
        }

    }

    fun updateRemaining(view:View?){

        //Count of estimated spend global value
        var spend=0
        var income=0F
        var color="FFFFFF"
        for (idType in 1..11) {
            val idInput = resources.getIdentifier(("valueType$idType"), "id", packageName)
            try {
                spend+=findViewById<EditText>(idInput).text.toString().toInt()
            }catch (e: Exception) {}

        }

        //Input estimated
        income=findViewById<EditText>(R.id.editIncome).text.toString().toFloat()

        val budget=income-spend

        val textView=findViewById<TextView>(R.id.remainingMoney)
        val buttonSave=findViewById<Button>(R.id.buttonSave)


        if(budget==0F){
            textView.setTextColor(Color.parseColor("#00FF00"))
            textView.text="Vous n'avez plus d'argent à placer dans vos catégories bravo"
            buttonSave.isEnabled=true
        }else if(budget<0F){
            textView.setTextColor(Color.parseColor("#FF0000"))
            textView.text="Vous avez alloué "+ Math.abs(budget).toString() + "€ en trop."
            buttonSave.isEnabled=false
        }else{
            textView.setTextColor(Color.parseColor("#EE2222"))
            textView.text="Il vous reste $budget€ à allouer."
            buttonSave.isEnabled=false
        }



    }


    fun setBudget(view: View?) {
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            for (idType in 1..11) {
                val idInput = resources.getIdentifier(("valueType$idType"), "id", packageName)
                db.balanceDao().updateBudgetType(
                    findViewById<EditText>(idInput).text.toString().toInt(),
                    idType
                )
            }
            this@BudgetActivity.runOnUiThread {
                val intent= Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}