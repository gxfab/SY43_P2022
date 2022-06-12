package com.example.lafo_cheuse.material

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.models.Expense

class ExpenseAdapter (var context : Activity) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
    private var mExpense: List<Expense> = ArrayList<Expense>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ie_name: EditText = itemView.findViewById(R.id.ie_name)
        val categoryEmojiButton: Button = itemView.findViewById<Button>(R.id.emojiButton)
        val ie_value: EditText = itemView.findViewById(R.id.ie_value)
        val deleteButton: Button = itemView.findViewById<Button>(R.id.deleteButton2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val incomeExpenseItemView = inflater.inflate(R.layout.income_expense_item, parent, false)
        return ViewHolder(incomeExpenseItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense: Expense = mExpense[position]
        holder.ie_name.setText(expense.category?.categoryName)
        holder.categoryEmojiButton.text = expense.category?.categoryEmoji
        holder.ie_value.setText(expense.amount.toString())

        holder.categoryEmojiButton.setOnClickListener {
             //chooseCategory(category)
        }
    }

    override fun getItemCount(): Int {
        return mExpense.size
    }

}