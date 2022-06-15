package com.example.gestimali.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.R
import com.example.gestimali.income.Income

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.ViewHolder>(){

    private var incomeList = emptyList<Income>()
    private var monthList = listOf("January", "February", "March", "April","May","June")

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val monthModel = itemView.findViewById<TextView>(R.id.month_name)
        val incomeContainer = itemView.findViewById<View>(R.id.income)
        val expenseContainer = itemView.findViewById<View>(R.id.expense)
        val wishContainer = itemView.findViewById<View>(R.id.wish)
        val envelopeContainer = itemView.findViewById<View>(R.id.envelope)
        val resultContainer = itemView.findViewById<View>(R.id.result)
        val planButton = itemView.findViewById<Button>(R.id.plan_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_month_container,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMonth = monthList[position]
        holder.monthModel.text = currentMonth

        if(position == incomeList.size){
            holder.incomeContainer.visibility = View.INVISIBLE
            holder.expenseContainer.visibility = View.INVISIBLE
            holder.wishContainer.visibility = View.INVISIBLE
            holder.envelopeContainer.visibility = View.INVISIBLE
            holder.resultContainer.visibility = View.INVISIBLE
        }
        else{
            holder.planButton.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int = incomeList.size + 1
}