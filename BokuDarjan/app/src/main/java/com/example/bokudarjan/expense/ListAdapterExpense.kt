package com.example.bokudarjan.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import kotlinx.android.synthetic.main.expense_card.view.*

class ListAdapterExpense: RecyclerView.Adapter<ListAdapterExpense.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_card, parent, false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.itemView.nameExpense.text = currentItem.name
        holder.itemView.nameCategoryExpense.text = currentItem.categoryName
        holder.itemView.amoutExpense.text = currentItem.amount.toString()



    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }

}