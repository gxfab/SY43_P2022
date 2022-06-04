package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.R
import com.example.bokudarjan.expense.Expense
import kotlinx.android.synthetic.main.expense_row.view.*

class ListAdapterExpense: RecyclerView.Adapter<ListAdapterExpense.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_row, parent, false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.itemView.name.text = currentItem.id.toString()
    }

    fun setData(expense: List<Expense>){
        this.expenseList = expense
        notifyDataSetChanged()
    }
}