package com.example.lafo_cheuse

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.example.lafo_cheuse.material.DatabaseDate
import com.example.lafo_cheuse.material.ExpenseAdapter
import com.example.lafo_cheuse.material.IncomeAdapter
import com.example.lafo_cheuse.models.*
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Activity where the user creates one time incomes/expenses
 * Before creation of an expense, the budget is checked to see if the expense is within the budget
 *
 * @property incomeViewModel - an instance of [IncomeViewModel] to manage the DB
 * @property expenseViewModel - an instance of [ExpenseViewModel] to manage the DB
 * @property categoryViewModel - an instance of [CategoryViewModel] to manage the DB
 * @property resultLauncher - an [ActivityResultLauncher] to get the category selected in the CategoryChooserActivity
 * @property selectedCategory - variable to check the selected category
 */
class CreateIncomeExpenseActivity : AppCompatActivity() {
    private val expenseViewModel : ExpenseViewModel by viewModels()
    private val incomeViewModel : IncomeViewModel by viewModels()
    private val categoryViewModel : CategoryViewModel by viewModels()
    private var resultLauncher : ActivityResultLauncher<Intent>? = null
    var selectedCategory : Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_income_expense)

        val categoryChooserButton : Button = findViewById<Button>(R.id.emojiButton)
        val confirmButton: Button = findViewById(R.id.buttonConfirmCreation4)
        val toggleButton : ToggleButton = findViewById(R.id.toggleButton2)
        val ieName : TextView = findViewById(R.id.ie_name)
        val ieValue : TextView = findViewById(R.id.ie_value)

        categoryViewModel.getDefaultCategory()?.observe(this) { listDefault ->
            selectedCategory = listDefault[0]
            categoryChooserButton.text = selectedCategory?.categoryEmoji
        }

        initToggleButton(toggleButton,confirmButton)
        initConfirmButton(confirmButton,toggleButton,ieName,ieValue)

        categoryChooserButton.setOnClickListener {
            val bundle = bundleOf("moneyChangeId" to 0, "type" to "none")
            val intent = Intent(this, CategoryChooserActivity::class.java)
            intent.putExtras(bundle)
            resultLauncher!!.launch(intent)
        }





        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val emoji = data?.getStringExtra("categoryEmoji")
                val name = data?.getStringExtra("categoryName")
                categoryViewModel.getCategory(name!!,emoji!!)?.observe(this) { listCategories ->
                    selectedCategory = listCategories[0]
                    categoryChooserButton.text = selectedCategory?.categoryEmoji
                }
            }
        }
    }

    /**
     * Method to initialize the ToggleButton that switches between creating an expense or an income
     *
     * @param toggleButton - the [ToggleButton] of the activity
     * @param confirmButton - the [Button] of the activity
     */

    private fun initToggleButton(toggleButton : ToggleButton, confirmButton : Button) {
        toggleButton.setOnClickListener {
            if(toggleButton.isChecked) {
                confirmButton.setBackgroundColor(resources.getColor(R.color.green, null))
            } else {
                confirmButton.setBackgroundColor(resources.getColor(R.color.red, null))
            }
        }
    }

    /**
     * Method to initialize the Button that confirms the expense or the income
     *
     * @param confirmButton - the [Button] of the activity
     * @param toggleButton - the [ToggleButton] of the activity
     * @param ieName - the [TextView] of the name of the expense/income
     * @param ieValue - the [TextView] of the value of the expense/income
     */

    private fun initConfirmButton(
        confirmButton: Button,
        toggleButton: ToggleButton,
        ieName : TextView,
        ieValue : TextView
    ) {
        confirmButton.setOnClickListener{
            val today : DatabaseDate = convertDateInDatabaseDate(Calendar.getInstance())
            if (ieName.text.toString().trim().isEmpty())
                Toast.makeText(this,
                    "Nom manquant !", Toast.LENGTH_SHORT).show()
            else if (ieValue.text.toString().trim().isEmpty())
                Toast.makeText(this,
                    "Somme manquante !", Toast.LENGTH_SHORT).show()
                if (!toggleButton.isChecked) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        addExpense(today,ieValue,ieName)
                    }
                } else {
                    incomeViewModel.insertIncome(
                        Income(
                            Frequency.OUNCE_A_DAY,
                            ieName.text.toString(),
                            selectedCategory!!,
                            ieValue.text.toString().toDouble(),
                            today.year,
                            today.month,
                            today.day
                        )
                    )
                    finish()

                }

            }
        }

    /**
     * Small function to convert a calendar date to DatabaseDate object
     *
     * @param calendar - a Calendar object from java.utils which one wants to convert
     * @return a DatabaseDate object with all the [calendar] data
     */
    private fun convertDateInDatabaseDate(calendar: Calendar) : DatabaseDate {
        return DatabaseDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(
            Calendar.DAY_OF_MONTH))
    }

    /**
     * The fonction that checks if an expense can be added
     *
     * @param date - a [DatabaseDate] to add the date to the expense and verify the budget
     * @param ieName - the [TextView] of the name of the expense/income
     * @param ieValue - the [TextView] of the value of the expense/income
     */

    private suspend fun addExpense(
        date : DatabaseDate,
        ieValue : TextView,
        ieName : TextView,
    ) = coroutineScope {
        val globalExpensesSum : Double = if(expenseViewModel.getMonthlyExpensesSumForCategorySync(selectedCategory!!)?.isEmpty() == true) {
            0.0
        } else {
            expenseViewModel.getMonthlyExpensesSumForCategorySync(selectedCategory!!)?.get(0)?.totalAmount!!
        }

        val partialExpensesSumList : List<ExpenseSumContainer> =
            expenseViewModel.getOneTimeExpensesSumForCategoryAndMonthSync(selectedCategory!!,date.year,date.month)
        val partialExpensesSum : Double =
            if (partialExpensesSumList.isEmpty()) {
                0.0
            } else {
                partialExpensesSumList[0].totalAmount
            }

        val newExpenseSum : Double = partialExpensesSum - ieValue.text.toString().toDouble()
        if (globalExpensesSum > newExpenseSum) {
            Toast.makeText(applicationContext,
                "❌ Vous allez dépasser votre budget", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("why?","global : $globalExpensesSum; partial : $partialExpensesSum")
            expenseViewModel.insertExpense(
                Expense(
                    Frequency.OUNCE_A_DAY,
                    ieName.text.toString(),
                    selectedCategory!!,
                    -ieValue.text.toString().toDouble(),
                    date.year,
                    date.month,
                    date.day
                )
            )
            finish()
        }
    }
}