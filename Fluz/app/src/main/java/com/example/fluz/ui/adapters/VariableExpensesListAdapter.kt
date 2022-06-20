package com.example.fluz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.repositories.*
import com.example.fluz.databinding.CreateBudgetItemBinding
import com.example.fluz.databinding.VariableExpenseItemBinding
import com.example.fluz.ui.viewmodels.VariableExpensesViewModel
import com.example.fluz.ui.viewmodels.VariableExpensesViewModelFactory

/**
 * Custom adapter of the variable expenses list recyclerview
 *
 * @param fragment fragment that holds the recyclerview
 */
class VariableExpensesListAdapter(fragment: Fragment) :
    ListAdapter<BudgetItemAndCategory, VariableExpensesListAdapter.ViewHolder>(
        TransactionComparatorBudgetItemAndCategory()
    ) {
    private val database by lazy { AppDatabase(fragment.requireContext()) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val variableExpensesViewModel: VariableExpensesViewModel by fragment.viewModels {
        VariableExpensesViewModelFactory(
            userRepository,
            categoryRepository,
            budgetItemRepository,
            budgetRepository,
            transactionRepository
        )
    }

    inner class ViewHolder(val binding: VariableExpenseItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class TransactionComparatorBudgetItemAndCategory :
        DiffUtil.ItemCallback<BudgetItemAndCategory>() {
        override fun areItemsTheSame(
            oldItem: BudgetItemAndCategory,
            newItem: BudgetItemAndCategory
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: BudgetItemAndCategory,
            newItem: BudgetItemAndCategory
        ): Boolean {
            return false
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            VariableExpenseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val current = getItem(position)
            categoryTag.text = current.category.title
            amount.text = current.budgetItem.amount.toString()

            deleteBtn.setOnClickListener {
                variableExpensesViewModel.deleteBudgetItem(current.category)
            }
        }
    }
}