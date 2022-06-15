package com.example.lafo_cheuse.material

import android.app.Activity
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Income
import java.util.ArrayList

/**
 * TODO : Complete documentation
 *
 * @property context
 * @property itemClickListener
 * @property resources
 */
class IncomeAdapter (
    var context : Activity,
    val itemClickListener: DeleteButtonClickListener,
    val resources : Resources
    ) : RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {
    private var mIncome: List<Income> = ArrayList<Income>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ieName: TextView = itemView.findViewById(R.id.ie_name)
        val categoryEmojiButton: Button = itemView.findViewById(R.id.emojiButton)
        val ieValue: TextView = itemView.findViewById(R.id.ie_value)
        val ieDate: TextView = itemView.findViewById(R.id.ie_date)
        val deleteButton: FloatingActionButton = itemView.findViewById(R.id.deleteButton2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val incomeIncomeItemView = inflater.inflate(R.layout.income_expense_item, parent, false)
        return ViewHolder(incomeIncomeItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val income: Income = mIncome[position]
        holder.ieName.text = income.name
        holder.categoryEmojiButton.text = income.category?.categoryEmoji
        holder.ieValue.text = resources.getString(R.string.sum,income.amount)
        holder.ieDate.text = resources.getString(R.string.date,income.dateDay,income.dateMonth,income.dateYear)
        holder.deleteButton.setOnClickListener {
            itemClickListener.onDeleteButtonClick(position)
            notifyDataSetChanged()
        }
    }

    interface DeleteButtonClickListener {
        fun onDeleteButtonClick(position: Int)
    }

    override fun getItemCount(): Int {
        return mIncome.size
    }

    fun setIncomes(mIncomes: List<Income>) {
        this.mIncome = mIncomes
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Income = mIncome[position]
}

