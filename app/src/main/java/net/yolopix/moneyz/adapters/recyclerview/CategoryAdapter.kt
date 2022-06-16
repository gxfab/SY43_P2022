package net.yolopix.moneyz.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import net.yolopix.moneyz.R
import net.yolopix.moneyz.SwipeToDeleteExpense
import net.yolopix.moneyz.fragments.dialog.AddCategoryBottomSheet
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category

/**
 * Adapter for the category recyclerview
 * @param categoryList List of categories to display
 * @param parentContext Activity context
 * @param expenseMode If true, display only categories with expenses and nest them under each item. If false, display all categories in prevision mode.
 * @param db The database to let each category build its list of expenses
 * @param maxAmountWhenEditing The maximum amount the category can handle if it's edited
 */
class CategoryAdapter(
    private val categoryList: List<Category>,
    private val parentContext: AppCompatActivity,
    private val expenseMode: Boolean,
    private val db: AppDatabase,
    private val monthNumber: Int,
    private val yearNumber: Int,
    private val accountUid: Int = -1,
    private val maxAmountWhenEditing: Float = Float.MAX_VALUE
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
        // Common to both prevision/expenses view
        viewHolder.categoryNameTextView.text = categoryList[position].name
        viewHolder.categoryPriceTextView.text = parentContext.getString(
            R.string.money_format,
            String.format("%.2f", categoryList[position].predictedAmount)
        )

        // For expense view
        if (expenseMode) {
            viewHolder.expensesRecyclerView.layoutManager = LinearLayoutManager(parentContext)
            parentContext.lifecycleScope.launch {
                loadCategoriesAndExpenses(viewHolder)
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

            // Swipe to delete expense
            val swipeToDelete = object : SwipeToDeleteExpense() {
                override fun onSwiped(childViewHolder: RecyclerView.ViewHolder, direction: Int) {
                    parentContext.lifecycleScope.launch {
                        val expenseList =
                            db.expenseDao()
                                .getExpenseForCategory(categoryList[viewHolder.adapterPosition].uid)
                        val adapterPosition = childViewHolder.adapterPosition
                        db.expenseDao().deleteExpense(expenseList[adapterPosition])
                        loadCategoriesAndExpenses(viewHolder)
                        Snackbar.make(
                            viewHolder.expensesRecyclerView,
                            R.string.info_deleted_expense,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDelete)
            itemTouchHelper.attachToRecyclerView(viewHolder.expensesRecyclerView)
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
                    maxAmountWhenEditing + categoryList[position].predictedAmount,
                    categoryList[position].expenseType,
                    categoryList[position]
                ).apply {
                    show(parentContext.supportFragmentManager, tag)
                }
            }
        }

    }

    private suspend fun loadCategoriesAndExpenses(viewHolder: CategoryViewHolder) {
        val position = viewHolder.adapterPosition
        viewHolder.expensesRecyclerView.adapter = ExpensesAdapter(
            db.expenseDao().getExpenseForCategory(categoryList[position].uid),
            monthNumber,
            yearNumber,
            db,
            parentContext
        )
        // Show an alert if the actual expense for the whole category
        // is higher than the prevision
        val totalExpensesForCategory =
            db.expenseDao().getExpenseAmountForOneCategory(categoryList[position].uid)
        if (categoryList[position].predictedAmount >= totalExpensesForCategory) {
            viewHolder.categoryPriceTextView.text = parentContext.getString(
                R.string.money_no_overflow_format,
                String.format("%.2f", categoryList[position].predictedAmount),
                String.format("%.2f", totalExpensesForCategory)
            )
            viewHolder.categoryPriceTextView.setTextColor(viewHolder.categoryNameTextView.currentTextColor)
        } else {
            viewHolder.categoryPriceTextView.text = parentContext.getString(
                R.string.money_overflow_format,
                String.format("%.2f", categoryList[position].predictedAmount),
                String.format(
                    "%.2f",
                    totalExpensesForCategory - categoryList[position].predictedAmount
                )
            )
            viewHolder.categoryPriceTextView.setTextColor(
                ContextCompat.getColor(parentContext, R.color.red)
            )
        }
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

