package com.example.gestimali

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.adapter.CategoryAdapter
import com.example.gestimali.adapter.MONTH_NAME
import com.example.gestimali.adapter.MONTH_VALUE

class AddBudgetActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_budget)


        val categoryRecyclerView = this.findViewById<RecyclerView>(R.id.category_recycler)
        categoryRecyclerView.adapter = CategoryAdapter(intent.getIntExtra(MONTH_VALUE,0),this)

        var planTitle = findViewById<TextView>(R.id.plan_budget_title)
        planTitle.text = planTitle.text.toString()+" "+intent.getStringExtra(MONTH_NAME)

    }
}
