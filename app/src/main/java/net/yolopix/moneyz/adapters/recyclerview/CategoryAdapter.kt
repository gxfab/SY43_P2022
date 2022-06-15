package net.yolopix.moneyz.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.R
import net.yolopix.moneyz.fragments.dialog.AddCategoryBottomSheet
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category

/**
 * Adapter for the category recyclerview
 * @param categoryList List of categories to display
 * @param parentContext Activity context
 * @param expenseMode If true, display only categories with expenses and nest them under each item. If false, display all categories in prevision mode.
 * @param db The database to let each category build its list of expenses
 */
class CategoryAdapter(
    private val categoryList: List<Category>,
    private val parentContext: AppCompatActivity,
    private val expenseMode: Boolean,
    private val db: AppDatabase,
    private val monthNumber: Int,
    private val yearNumber: Int,
    private val accountUid: Int = -1
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    /**
     * A nested class for the view holder
     */
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPriceTextView: TextView = itemView.findViewById(R.id.categoryPrice)
        val expandButton: View = itemView.findViewById(R.id.button_expand)
        val expensesRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerview_expenses)
        val categoryHeaderLayout: View = itemView.findViewById(R.id.layout_category_header)
        val bottomSpace: Space = itemView.findViewById(R.id.space_bottom_category)
        val itemDivider: View = itemView.findViewById(R.id.divider_item_category)
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        // For expense view
        if (expenseMode) {
            viewHolder.expensesRecyclerView.layoutManager = LinearLayoutManager(parentContext)
            parentContext.lifecycleScope.launch {
                viewHolder.expensesRecyclerView.adapter = ExpensesAdapter(
                    db.expenseDao().getExpenseForCategory(categoryList[position].uid),
                    monthNumber,
                    yearNumber,
                    db,
                    parentContext
                )
            }
            // Expand/collapse the expenses nested under the category
            viewHolder.categoryHeaderLayout.setOnClickListener {
                if (viewHolder.expensesRecyclerView.visibility == View.GONE) {
                    viewHolder.expensesRecyclerView.visibility = View.VISIBLE
                    viewHolder.expandButton.rotation = 180f
                } else {
                    viewHolder.expensesRecyclerView.visibility = View.GONE
                    viewHolder.expandButton.rotation = 0f
                }
            }
            // Add spacing at the end of the list
            if (position == categoryList.lastIndex) {
                viewHolder.bottomSpace.visibility = View.VISIBLE
            }
        }

        // For prevision view
        else {
            viewHolder.expandButton.visibility = View.GONE
            viewHolder.expensesRecyclerView.visibility = View.GONE
            viewHolder.itemDivider.visibility = View.GONE
            viewHolder.categoryHeaderLayout.setOnClickListener {
                AddCategoryBottomSheet(
                    db,
                    monthNumber,
                    yearNumber,
                    accountUid,
                    Float.MAX_VALUE, //TODO check real max value
                    categoryList[position].expenseType,
                    categoryList[position]
                ).apply {
                    show(parentContext.supportFragmentManager, tag)
                }
            }
        }

        // Common to both prevision/expenses view
        viewHolder.categoryNameTextView.text = categoryList[position].name
        viewHolder.categoryPriceTextView.text = parentContext.getString(
            R.string.money_format,
            String.format("%.2f", categoryList[position].predictedAmount)
        )
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

