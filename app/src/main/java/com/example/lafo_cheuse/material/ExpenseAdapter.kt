package com.example.lafo_cheuse.material

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Expense
import com.example.lafo_cheuse.models.Income
import java.util.*
import kotlin.math.exp

class ExpenseAdapter (var context : Activity, val itemClickListener: DeleteButtonClickListener) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
    private var mExpense: List<Expense> = ArrayList<Expense>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ieName: EditText = itemView.findViewById(R.id.ie_name)
        val categoryEmojiButton: Button = itemView.findViewById(R.id.emojiButton)
        val ieValue: EditText = itemView.findViewById(R.id.ie_value)
        val ieDate: EditText = itemView.findViewById(R.id.ie_date)
        val deleteButton: FloatingActionButton = itemView.findViewById(R.id.deleteButton2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val incomeExpenseItemView = inflater.inflate(R.layout.income_expense_item, parent, false)
        return ViewHolder(incomeExpenseItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense: Expense = mExpense[position]
        holder.ieName.setText(expense.name)
        holder.categoryEmojiButton.text = expense.category?.categoryEmoji
        holder.ieValue.setText(expense.amount.toString())
        holder.ieDate.setText(expense.dateDay.toString()+"/"+expense.dateMonth.toString()+"/"+expense.dateYear.toString())

        holder.deleteButton.setOnClickListener {
            itemClickListener.onDeleteButtonClick(position)
            notifyDataSetChanged()
        }
    }

    interface DeleteButtonClickListener {
        fun onDeleteButtonClick(position: Int)
    }

    override fun getItemCount(): Int {
        return mExpense.size
    }

    fun setExpenses(mExpenses: List<Expense>) {
        this.mExpense = mExpenses
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Expense = mExpense[position]
}