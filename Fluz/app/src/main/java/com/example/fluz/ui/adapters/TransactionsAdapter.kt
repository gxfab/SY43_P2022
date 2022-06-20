package com.example.fluz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.*
import com.example.fluz.databinding.CategoryItemBinding
import com.example.fluz.databinding.TransactionItemBinding
import com.example.fluz.ui.fragments.Transaction
import com.example.fluz.ui.viewmodels.BudgetItemTransactionsViewModel
import com.example.fluz.ui.viewmodels.BudgetItemTransactionsViewModelFactory
import com.example.fluz.ui.viewmodels.TransactionViewModel
import com.example.fluz.ui.viewmodels.TransactionViewModelFactory
import kotlinx.android.synthetic.main.create_budget_item.*

/**
 * Custom adapter of the transactions list recyclerview
 *
 * @property fragment fragment that holds the recyclerview
 */
class TransactionsAdapter(private val fragment: Fragment) : ListAdapter<TransactionAndCategory, TransactionsAdapter.ViewHolder>(TransactionsComparator()) {
    private val database by lazy { AppDatabase(fragment.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val budgetItemTransactionsViewModel: BudgetItemTransactionsViewModel by fragment.viewModels {
        BudgetItemTransactionsViewModelFactory(
            userRepository,
            budgetRepository,
            budgetItemRepository,
            transactionRepository
        )
    }


    inner class ViewHolder(val binding: TransactionItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    class TransactionsComparator : DiffUtil.ItemCallback<TransactionAndCategory>() {
        override fun areItemsTheSame(oldItem: TransactionAndCategory, newItem: TransactionAndCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TransactionAndCategory, newItem: TransactionAndCategory): Boolean {
            return oldItem.transaction.id == newItem.transaction.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val current = getItem(position)

            txtTransactionTag.text = current.transaction.tag
            txtTransactionAmount.text = current.transaction.amount.toString() + " €"
            txtTransactionDate.text = current.transaction.date

            deleteBtn.setOnClickListener {
                budgetItemTransactionsViewModel.deleteOne(current.transaction.id)
            }

            budgetItemTransactionsViewModel.totalTransactions.observe(fragment) {totalTransactions ->
                budgetItemTransactionsViewModel.lastBudget.value?.let {
                    budgetItemTransactionsViewModel.budgetItemAndCategory.value?.budgetItem?.let { it1 ->
                        budgetItemTransactionsViewModel.getAllTransactionsForBudgetItem(
                            it.id, it1.id)
                    }
                }
            }
        }
    }


}