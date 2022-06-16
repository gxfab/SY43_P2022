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

/**
 * Activity to manage the month previsional budget.
 *
 * @constructor Create empty Budget activity
 */
class BudgetActivity : AppCompatActivity() {

    /**
     * Start month timestamp
     */
    private val startMonthTimestamp: Int
        get() = ((SimpleDateFormat("dd-MM-yyyy").parse(
            "01-" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("MM-yyyy")
            )
        )).time / 1000).toInt()

    /**
     * Start last month timestamp
     */
    private val startLastMonthTimestamp: Int
        get() = ((SimpleDateFormat("dd-MM-yyyy").parse(
            "01-" + LocalDateTime.now().minusMonths(1).format(
                DateTimeFormatter.ofPattern("MM-yyyy")
            )
        )).time / 1000).toInt()


    /**
     * Create all the activity settings right values to help user to make his budget. It lso add event listener to update budget information when user change datas.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        //Adding listener on change to income
        findViewById<EditText>(R.id.editIncome).addTextChangedListener(object : TextWatcher{
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
            this@BudgetActivity.runOnUiThread {
                findViewById<EditText>(R.id.editIncome).text = Editable.Factory.getInstance().newEditable(incomeLastMonth.toString())
            }
        }
    }

    /**
     * Permite to update the remaining budget depending of previsional incomes and spend
     *
     * @param view
     */
    fun updateRemaining(view:View?){

        //Count of estimated spend global value
        var spend=0
        var income=0F
        for (idType in 1..11) {
            val idInput = resources.getIdentifier(("valueType$idType"), "id", packageName)
            try {
                spend+=findViewById<EditText>(idInput).text.toString().toInt()
            }catch (e: Exception) {}

        }

        //Input estimated
        if(findViewById<EditText>(R.id.editIncome).text.toString()!=""){
            income=findViewById<EditText>(R.id.editIncome).text.toString().toFloat()
        }


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


    /**
     * Set the budget as official budget in the database and redirect user to the Main Activity
     *
     * @param view
     */
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