package net.yolopix.moneyz.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.fragments.dialog.AddExpenseBottomSheet
import net.yolopix.moneyz.R
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category
import net.yolopix.moneyz.model.entities.Expense
import net.yolopix.moneyz.model.entities.Month
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * An adapter to manage display of expenses elements in a RecyclerView
 *
 * @param expenseList the list of expenses to display
 * @param monthNumber the month to display on the date view
 * @param yearNumber the year to display on the date view
 * @param db The main database of the app
 * @param parentContext The parent activity of the RecyclerView
 */
class ExpensesAdapter(
    private val expenseList: List<Expense>,
    private val monthNumber: Int,
    private val yearNumber: Int,
    private val db: AppDatabase,
    private val parentContext: AppCompatActivity
) :
    RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>() {

    /**
     * A nested class for the view holder
     */
    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExpenseName: TextView = itemView.findViewById(R.id.text_view_expense_name)
        val textViewDate: TextView = itemView.findViewById(R.id.text_view_date)
        val textViewAmount: TextView = itemView.findViewById(R.id.text_view_amount)
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_expense, parent, false)
        return ExpensesViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder.itemView to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

        // Edit expense when clicking on an item
        (holder.itemView as LinearLayout).setOnClickListener {
            parentContext.lifecycleScope.launch {
                val parentCategory: Category =
                    db.categoryDao().getCategory(expenseList[position].categoryUid)
                val month = Month( //TODO dummy month
                    parentCategory.monthNumber,
                    parentCategory.yearNumber,
                    -1.0,
                    -1,
                    parentCategory.accountUid
                )
                AddExpenseBottomSheet(db, month, expense).apply {
                    show(parentContext.supportFragmentManager, tag)
                }
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return expenseList.size
    }

}