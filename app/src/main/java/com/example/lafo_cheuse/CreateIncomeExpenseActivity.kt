package com.example.lafo_cheuse

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.example.lafo_cheuse.material.DatabaseDate
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import java.util.*

class CreateIncomeExpenseActivity : AppCompatActivity() {
    val expenseViewModel : ExpenseViewModel by viewModels()
    val incomeViewModel : IncomeViewModel by viewModels()
    val categoryViewModel : CategoryViewModel by viewModels()
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
        initConfirmButton(confirmButton,toggleButton,ieName,ieValue, categoryChooserButton)

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

    private fun initToggleButton(toggleButton : ToggleButton, confirmButton : Button) {
        toggleButton.setOnClickListener {
            if(toggleButton.isChecked) {
                confirmButton.setBackgroundColor(Color.parseColor("#F91A1A"))
                toggleButton.setTextColor(Color.parseColor("#F91A1A"))
            } else {
                confirmButton.setBackgroundColor(Color.parseColor("#32F91A"))
                toggleButton.setTextColor(Color.parseColor("#32F91A"))
            }
        }
    }

    private fun initConfirmButton(
        confirmButton: Button,
        toggleButton: ToggleButton,
        ieName : TextView,
        ieValue : TextView,
        categoryChooserButton : Button
    ) {
        confirmButton.setOnClickListener{
            val today : DatabaseDate = convertDateInDatabaseDate(Calendar.getInstance())
            if (ieName.text.toString().trim().isEmpty())
                Toast.makeText(this,
                    "Nom manquant !", Toast.LENGTH_SHORT).show()
            else if (ieValue.text.toString().trim().isEmpty())
                Toast.makeText(this,
                    "Somme manquante !", Toast.LENGTH_SHORT).show()
                val calendar: DatabaseDate = convertDateInDatabaseDate(Calendar.getInstance())
                if (toggleButton.isChecked) {
                    expenseViewModel.getMonthlyExpensesSum().observe(this) { _globalExpensesSum ->
                        expenseViewModel.getOneTimeExpensesSumByDate(calendar.year,calendar.month).observe(this) { _partialExpensesSum ->
                            var globalExpensesSum : Double =
                                if(_globalExpensesSum == null) {
                                    0.0
                                } else {
                                    _globalExpensesSum
                                }
                            var partialExpensesSum : Double =
                                if(_partialExpensesSum == null) {
                                    0.0
                                } else {
                                    _partialExpensesSum
                                }

                            val newExpenseSum : Double = partialExpensesSum - ieValue.text.toString().toDouble()
                            if (globalExpensesSum > newExpenseSum) {
                                Toast.makeText(applicationContext,
                                    "❌ Vous allez dépasser votre budget", Toast.LENGTH_SHORT).show()
                            } else {
                                expenseViewModel.insertExpense(
                                    Expense(
                                        Frequency.OUNCE_A_DAY,
                                        ieName.text.toString(),
                                        Category(
                                            categoryChooserButton.text.toString(),
                                            categoryChooserButton.text.toString()
                                        ),
                                        -ieValue.text.toString().toDouble(),
                                        today.year,
                                        today.month,
                                        today.day
                                    )
                                )
                                finish()
                            }
                         }
                    }
                } else {
                    incomeViewModel.insertIncome(
                        Income(
                            Frequency.OUNCE_A_DAY,
                            ieName.text.toString(),
                            Category(
                                categoryChooserButton.text.toString(),
                                categoryChooserButton.text.toString()
                            ),
                            ieValue.text.toString().toDouble(),
                            today.year,
                            today.month,                            today.day

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
}