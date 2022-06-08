package com.example.lafo_cheuse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.material.CategoryAdapter
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Income
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.example.lafo_cheuse.viewmodels.ExpenseViewModel
import com.example.lafo_cheuse.viewmodels.IncomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoryChooserActivity : AppCompatActivity() {
    private val incomeViewModel : IncomeViewModel by viewModels()
    private val expenseViewModel : ExpenseViewModel by viewModels()
    var income : Income? = null
    var expense : Expense? = null
    var type : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chooser)
        val b = intent.extras
        val moneyChangeId : Long

        if (b != null){
            moneyChangeId = b.getLong("moneyChangeId")
            type = b.getString("type")!!
            if(type == "income") {
                incomeViewModel.getIncome(moneyChangeId).observe(this) { list ->
                    income = list[0]
                }
            } else if(type == "expense") {
                expenseViewModel.getExpense(moneyChangeId).observe(this) { list ->
                    expense = list[0]
                }
            }
        }

        val adapter : CategoryAdapter?

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.catgoryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter

        val categoryViewModel : CategoryViewModel by viewModels()
        categoryViewModel.getCategories()?.observe(this) { list ->
            adapter.setCategories(list)
        }

        val addCategoryButton : View = findViewById<FloatingActionButton>(R.id.addCategoryButton)
        addCategoryButton.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }


    }

    fun chooseCategory(categoryChosen : Category) {
        if(type == "income") {
            income?.category = categoryChosen
            incomeViewModel.updateIncome(income!!)
        } else if(type == "expense") {
            expense?.category = categoryChosen
            expenseViewModel.updateExpense(expense!!)
        }
        this.finish()
    }
}