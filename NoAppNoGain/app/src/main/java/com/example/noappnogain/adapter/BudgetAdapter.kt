package com.example.noappnogain.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.noappnogain.R
import com.example.noappnogain.model.Budget

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

        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Vous avez cliqué sur l'élément # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }


        fun setType(type: String) {
            val mType = itemView.findViewById<TextView>(R.id.type_txt_budget)
            mType.text = type
        }

        fun setAmount(amount: Int) {
            val mAmount: TextView = itemView.findViewById<TextView>(R.id.amount_txt_expense)
            val stamount = amount.toString()
            mAmount.text = "-$stamount"
        }

    }

}