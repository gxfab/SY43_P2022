package com.example.lafo_cheuse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.lafo_cheuse.fragment.view.BudgetSetterFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BudgetSetterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_setter)

        val  addRegularIncomeButton : ImageButton = findViewById(R.id.addRegularIncome)

        addRegularIncomeButton.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BudgetSetterFragment>(R.id.regularIncomeView)
            }
        }

        val addRegularExpenseButton : ImageButton = findViewById(R.id.addRegularExpense)

        addRegularExpenseButton.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BudgetSetterFragment>(R.id.regularExpenseView)
            }
        }


    }
}