package com.example.gestimali.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.AddBudgetActivity
import com.example.gestimali.NewIncomeFragment
import com.example.gestimali.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

internal class CategoryAdapter(var intMonth: Int, var activity: AppCompatActivity ) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() ,View.OnClickListener{

    private var categoryList = listOf("Income", "Expense", "Wish/Saving", "Envelope")

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val categoryTitle = itemView.findViewById<TextView>(R.id.category_name)
        val floatingButton = itemView.findViewById<FloatingActionButton>(R.id.floatingActionButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_plan_category,parent,false)

        return CategoryAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitle.text = categoryList[position]

        if(position==0){
            holder.floatingButton.setOnClickListener(this)
        }


    }

    override fun getItemCount(): Int = 4

    override fun onClick(p0: View?) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.popup_fragment,NewIncomeFragment())
        transaction.commit()
    }

}
