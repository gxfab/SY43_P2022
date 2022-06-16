package com.example.gestimali.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.AddBudgetActivity
import com.example.gestimali.MainActivity
import com.example.gestimali.R
import com.example.gestimali.income.Income

const val MONTH_NAME = "com.example.gestimali.MONTH_NAME"
const val MONTH_VALUE = "com.example.gestimali.MONTH_VALUE"

class MonthAdapter(var context: Context) : RecyclerView.Adapter<MonthAdapter.ViewHolder>() {


    private var incomeList = emptyList<Income>()
    private var monthList = listOf("January", "February", "March", "April","May","June","July","August","September","October","November","December")
    private var containerTitle = listOf("Income", "Expense", "Wish/Saving", "Envelope","Result")

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val monthModel = itemView.findViewById<TextView>(R.id.month_name)
        val incomeContainer = itemView.findViewById<View>(R.id.income)
        val expenseContainer = itemView.findViewById<View>(R.id.expense)
        val wishContainer = itemView.findViewById<View>(R.id.wish)
        val envelopeContainer = itemView.findViewById<View>(R.id.envelope)
        val resultContainer = itemView.findViewById<View>(R.id.result)
        val containers = listOf(incomeContainer,expenseContainer,wishContainer,envelopeContainer,resultContainer)
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

            holder.planButton.setOnClickListener {
                var strName : String? =null
                context.startActivity(Intent(context,AddBudgetActivity::class.java).apply {
                    putExtra(MONTH_NAME,monthList[position])
                    putExtra(MONTH_VALUE,position)
                })
            }
        }
        else{
            holder.planButton.visibility = View.INVISIBLE
            for (i in 0 until holder.containers.size) {
                holder.containers[i].findViewById<TextView>(R.id.name_flow).text = containerTitle[i]
                holder.containers[i].findViewById<TextView>(R.id.money_flow_tags).text = ""
                holder.containers[i].findViewById<TextView>(R.id.money_flow_date).text = ""
            }

        }
    }

    override fun getItemCount(): Int = incomeList.size + 2
}