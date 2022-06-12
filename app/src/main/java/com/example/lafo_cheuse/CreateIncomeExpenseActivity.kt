package com.example.lafo_cheuse

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.lafo_cheuse.fragment.view.BudgetSetterFragment
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Frequency
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel

class CreateIncomeExpenseActivity : AppCompatActivity() {
    val expenseViewModel : ExpenseViewModel by viewModels()
    val incomeViewModel : IncomeViewModel by viewModels()
    private var resultLauncher : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_income_expense)

        var categoryChooserButton : Button = findViewById<Button>(R.id.emojiButton)
        val confirmButton: Button = findViewById(R.id.buttonConfirmCreation4)
        val toggleButton : ToggleButton = findViewById(R.id.toggleButton2)
        val ie_name : TextView = findViewById(R.id.ie_name)
        val ie_value : TextView = findViewById(R.id.ie_value)

        categoryChooserButton.setOnClickListener {
            val intent = Intent(this, CategoryChooserActivity::class.java)
            resultLauncher!!.launch(intent)
        }

        toggleButton.setOnClickListener {
            if(toggleButton.isChecked) {
                confirmButton.setBackgroundColor(Color.parseColor("#F91A1A"))
            } else {
                confirmButton.setBackgroundColor(Color.parseColor("#32F91A"))
            }
        }

        confirmButton.setOnClickListener{
            if(toggleButton.isChecked) {
                expenseViewModel.insertExpense(Expense(Frequency.OUNCE_A_MONTH, ie_name.text.toString(),
                    Category(categoryChooserButton.text.toString(),categoryChooserButton.text.toString()), ie_value.text.toString().toDouble()))
                finish()
            } else {
                incomeViewModel.insertIncome(
                    Income(Frequency.OUNCE_A_MONTH, ie_name.text.toString(),
                    Category(categoryChooserButton.text.toString(),categoryChooserButton.text.toString()), ie_value.text.toString().toDouble())
                )
                finish()
            }
        }

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val emoji = data?.getStringExtra("categoryEmoji")
                categoryChooserButton.setText(emoji)
            }
        }
    }
}