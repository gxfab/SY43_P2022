package com.example.fluz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.CreateBudgetItemBinding
import com.example.fluz.ui.fragments.FixedIncome
import com.example.fluz.ui.viewmodels.FixedTransactionViewModel
import com.example.fluz.ui.viewmodels.FixedTransactionViewModelFactory

class FixedTransactionListAdapter(fragment: Fragment) :
    ListAdapter<TransactionAndCategory, FixedTransactionListAdapter.ViewHolder>(TransactionComparator()) {

    private val database by lazy { AppDatabase(fragment.requireContext()) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }

    private val fixedTransactionViewModel: FixedTransactionViewModel by fragment.viewModels {
        FixedTransactionViewModelFactory(userRepository, transactionRepository, categoryRepository)
    }


    inner class ViewHolder(val binding: CreateBudgetItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class TransactionComparator : DiffUtil.ItemCallback<TransactionAndCategory>() {
        override fun areItemsTheSame(oldItem: TransactionAndCategory, newItem: TransactionAndCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TransactionAndCategory, newItem: TransactionAndCategory): Boolean {
            return oldItem.transaction.id == newItem.transaction.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CreateBudgetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val current = getItem(position)
            incomeTag.text = current.transaction.tag
            categoryTxt.text = current.category.title
            amount.text = current.transaction.amount.toString()

            deleteBtn.setOnClickListener {
                /*val list = currentList.toMutableList()


                list.remove(current)
                submitList(list)*/
                val type = if (current.transaction.type == "income") {
                    "income"
                } else {
                    "expense"
                }
                fixedTransactionViewModel.delete(current.transaction.id, type)
            }
        }
    }
}