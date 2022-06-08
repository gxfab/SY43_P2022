package net.yolopix.moneyz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.model.entities.Expense
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExpensesAdapter(
    private val expenseList: List<Expense>,
    private val monthNumber: Int,
    private val yearNumber: Int
) :
    RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {

    // Inner class for the view holder
    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExpenseName: TextView = itemView.findViewById(R.id.text_view_expense_name)
        val textViewDate: TextView = itemView.findViewById(R.id.text_view_date)
        val textViewAmount: TextView = itemView.findViewById(R.id.text_view_amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_expense, parent, false)
        return ExpensesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        val moneyFormat = NumberFormat.getCurrencyInstance()
        moneyFormat.maximumFractionDigits = 2
        val expense = expenseList[position]
        holder.textViewExpenseName.text = expense.name
        holder.textViewAmount.text = moneyFormat.format(expense.amount)


        // Date format
        val expenseLocalDate: LocalDate =
            LocalDate.of(yearNumber, monthNumber, expense.dayOfMonth)
        val expenseFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        holder.textViewDate.text = expenseLocalDate.format(expenseFormat)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

}