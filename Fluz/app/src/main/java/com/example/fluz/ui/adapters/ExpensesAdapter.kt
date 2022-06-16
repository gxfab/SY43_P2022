package com.example.fluz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.*
import com.example.fluz.databinding.BudgetItemBinding
import com.example.fluz.databinding.CreateBudgetItemBinding
import com.example.fluz.ui.fragments.BudgetItemTransactionsDirections
import com.example.fluz.ui.fragments.ExpensesDirections
import com.example.fluz.ui.viewmodels.ExpensesViewModel
import com.example.fluz.ui.viewmodels.ExpensesViewModelFactory
import com.example.fluz.ui.viewmodels.FixedTransactionViewModel
import com.example.fluz.ui.viewmodels.FixedTransactionViewModelFactory
import kotlinx.android.synthetic.main.fragment_expenses.view.*

class ExpensesAdapter(private val fragment: Fragment) : ListAdapter<BudgetItemAndCategory, ExpensesAdapter.ViewHolder>(BudgetItemComparator()) {

    private val database by lazy { AppDatabase(fragment.requireContext()) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val expensesViewModel: ExpensesViewModel by fragment.viewModels {
        ExpensesViewModelFactory(userRepository, budgetRepository, budgetItemRepository, transactionRepository)
    }

    inner class ViewHolder(val binding: BudgetItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class BudgetItemComparator : DiffUtil.ItemCallback<BudgetItemAndCategory>() {
        override fun areItemsTheSame(oldItem: BudgetItemAndCategory, newItem: BudgetItemAndCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BudgetItemAndCategory, newItem: BudgetItemAndCategory): Boolean {
            return oldItem.budgetItem.id == newItem.budgetItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BudgetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val current = getItem(position)
            txtCategoryTitle.text = current.category.title
            txtCategorySubtitle.text = current.category.title

            var totalTransactionsCategory = 0
            for (transaction in expensesViewModel.allTransactions.value!!) {
                if (transaction.category.title == current.category.title) {
                    totalTransactionsCategory += transaction.transaction.amount;
                }
            }

            val moneyLeft = current.budgetItem.amount - totalTransactionsCategory
            txtMoneyLeft.text = "Left " + moneyLeft.toString() + "€"

            txtMoneySpent.text = totalTransactionsCategory.toString() + "/" + current.budgetItem.amount.toString() + " €"

            gaugeTotalMoneySpent.setHighValue((totalTransactionsCategory.toFloat() / current.budgetItem.amount) * 100)

            budgetItem.setOnClickListener {
                val categoryId = current.budgetItem.id

                val action = ExpensesDirections.actionNavExpensesToBudgetItemTransactions(categoryId)

                fragment.findNavController().navigate(action)
            }
        }
    }

}