package com.example.lafo_cheuse

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.fragment.view.BudgetSetterFragment
import com.example.lafo_cheuse.material.IncomeSetterAdapter
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel


class BudgetSetterActivity : AppCompatActivity() {
    private var adapter : IncomeSetterAdapter? = null
    private var resultLauncher : ActivityResultLauncher<Intent>? = null
    private val incomeViewModel : IncomeViewModel by viewModels()
    private val categoryViewModel : CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_setter)

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.income_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)

        adapter = IncomeSetterAdapter(this,incomeViewModel)
        recyclerView.adapter = adapter

        categoryViewModel.getDefaultCategory()?.observe(this) { list ->
            adapter!!.setDefCategory(list[0])
        }

        incomeViewModel.getIncomes()?.observe(this) { list ->
            adapter!!.setIncomes(list)
        }


        val addRegularExpenseButton : ImageButton = findViewById(R.id.addRegularExpense)

        addRegularExpenseButton.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BudgetSetterFragment>(R.id.regularExpenseView)
            }
        }


    }

    fun startIntent(income : Income) {

    }
}