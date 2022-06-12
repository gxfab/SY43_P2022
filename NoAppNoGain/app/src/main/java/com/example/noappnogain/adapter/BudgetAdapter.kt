package com.example.noappnogain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.model.Budget
import com.example.noappnogain.R

class BudgetAdapter(private val budgetList: ArrayList<Budget>) :
    RecyclerView.Adapter<BudgetAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.budget_recycler_view,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = budgetList[position]

        holder.type.text = currentitem.category
        holder.amount.text = currentitem.montant.toString()

    }

    override fun getItemCount(): Int {
        return budgetList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val type: TextView = itemView.findViewById(R.id.type_txt_budget)
        val amount: TextView = itemView.findViewById(R.id.amount_txt_budget)


    }

}