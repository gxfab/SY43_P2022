package com.example.fluz.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.BudgetItemRepository
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentExpensesBinding
import com.example.fluz.ui.adapters.ExpensesAdapter
import com.example.fluz.ui.adapters.VariableExpensesListAdapter
import com.example.fluz.ui.viewmodels.ExpensesViewModel
import com.example.fluz.ui.viewmodels.ExpensesViewModelFactory
import com.example.fluz.ui.viewmodels.HomeViewModel
import com.example.fluz.ui.viewmodels.HomeViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Expenses : Fragment() {
    private lateinit var binding: FragmentExpensesBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val expensesViewModel: ExpensesViewModel by viewModels {
        ExpensesViewModelFactory(userRepository, budgetRepository, budgetItemRepository, transactionRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExpensesBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        val recyclerView = binding.rvListCategories
        val adapter = ExpensesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        expensesViewModel.getLastBudget(connectedUserId.toInt())

        expensesViewModel.totalTransactions.observe(this) {totalTransactions ->
            val leftForMonth = (expensesViewModel.lastBudget.value?.total_amount)?.minus(
                totalTransactions
            )
            binding.txtLeftForMonthAmount.text = leftForMonth.toString() + " €"
            binding.txtMoneySpentAmount.text = totalTransactions.toString() + "/" + expensesViewModel.lastBudget.value?.total_amount.toString() + " €"
            binding.gaugeTotalMoneySpent.setHighValue(((totalTransactions.toFloat() / expensesViewModel.lastBudget.value?.total_amount!!) * 100).toFloat())
        }

        expensesViewModel.lastBudget.observe(this) {lastBudget ->
            expensesViewModel.getTransactions(connectedUserId.toInt(), lastBudget.id)
            expensesViewModel.lastBudget.value?.let { expensesViewModel.getAllBudgetItems(it.id) }
        }

        expensesViewModel.budgetItems.observe(this) {budgetItems ->
            println(budgetItems)
            budgetItems.let { adapter.submitList(it) }
        }

        return binding.root
    }
}