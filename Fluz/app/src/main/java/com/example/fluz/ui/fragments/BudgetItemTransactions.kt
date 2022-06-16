package com.example.fluz.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.BudgetItemRepository
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentBudgetItemTransactionsBinding
import com.example.fluz.ui.viewmodels.BudgetItemTransactionsViewModel
import com.example.fluz.ui.viewmodels.BudgetItemTransactionsViewModelFactory
import com.example.fluz.ui.viewmodels.ExpensesViewModel
import com.example.fluz.ui.viewmodels.ExpensesViewModelFactory

class BudgetItemTransactions : Fragment() {

    private lateinit var binding: FragmentBudgetItemTransactionsBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val budgetItemTransactionsViewModel: BudgetItemTransactionsViewModel by viewModels {
        BudgetItemTransactionsViewModelFactory(
            userRepository,
            budgetRepository,
            budgetItemRepository,
            transactionRepository
        )
    }

    private val args: BudgetItemTransactionsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBudgetItemTransactionsBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        budgetItemTransactionsViewModel.lastBudget.observe(this) { lastBudget ->
            budgetItemTransactionsViewModel.getAllTransactionsForBudgetItem(
                budgetId = lastBudget.id,
                budgetItemId = args.budgetItemId
            )
        }

        budgetItemTransactionsViewModel.budgetItemAndCategory.observe(this) { budgetItem ->
            binding.txtCategoryTitle.text = budgetItem.category.title.uppercase()
        }

        budgetItemTransactionsViewModel.totalTransactions.observe(this) { totalTransactions ->
            val leftForMonth =
                budgetItemTransactionsViewModel.budgetItemAndCategory.value?.budgetItem?.amount?.minus(
                    totalTransactions
                )

            binding.txtLeftForMonthAmount.text = leftForMonth.toString() + " €"
            binding.gaugeTotalMoneySpent.setHighValue((totalTransactions.toFloat() / budgetItemTransactionsViewModel.budgetItemAndCategory.value?.budgetItem?.amount!!) * 100)
            binding.txtMoneySpent.text = "You have spent " + totalTransactions.toString() + "€ on your budget of " + budgetItemTransactionsViewModel.budgetItemAndCategory.value?.budgetItem?.amount!!.toString() + " €"
        }

        budgetItemTransactionsViewModel.getLastBudget(connectedUserId.toInt())

        binding.backArrowBudgetItemTransactions.setOnClickListener {
            findNavController().navigate(R.id.action_budgetItemTransactions_to_nav_expenses)
        }

        return binding.root
    }
}