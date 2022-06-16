package com.example.fluz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.databinding.CategoryItemBinding
import com.example.fluz.databinding.TransactionItemBinding
import com.example.fluz.ui.fragments.Transaction
import kotlinx.android.synthetic.main.create_budget_item.*

class TransactionsAdapter(private val fragment: Fragment) : ListAdapter<TransactionAndCategory, TransactionsAdapter.ViewHolder>(TransactionsComparator()) {
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
            txtTransactionDate.text = current.transaction.amount.toString()
        }
    }


}