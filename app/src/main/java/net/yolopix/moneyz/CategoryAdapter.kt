package net.yolopix.moneyz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.yolopix.moneyz.model.entities.Category

/**
 * Adapter for the category recyclerview
 * @param categoryList List of categories to display
 * @param context Activity context
 * @param expenseMode If true, display only categories with expenses and nest them under each item. If false, display all categories in prevision mode.
 */
class CategoryAdapter(
    private val categoryList: List<Category>,
    private val context: Context,
    private val expenseMode: Boolean
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPriceTextView: TextView = itemView.findViewById(R.id.categoryPrice)
        val buttonDelete: Button = itemView.findViewById(R.id.button_delete_category)
        val expandButton: View = itemView.findViewById(R.id.button_expand)
        val expensesRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerview_expenses)
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        if (expenseMode) {
            viewHolder.categoryPriceTextView.visibility = View.GONE
            viewHolder.expensesRecyclerView.layoutManager = LinearLayoutManager(context)
            //viewHolder.expensesRecyclerView.adapter = ExpensesAdapter()
        } else {
            viewHolder.expandButton.visibility = View.GONE
            viewHolder.expensesRecyclerView.visibility = View.GONE
            viewHolder.categoryPriceTextView.text = context.getString(
                R.string.money_format,
                String.format("%.2f", categoryList[position].predictedAmount)
            )
        }
        viewHolder.categoryNameTextView.text = categoryList[position].name
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

}

