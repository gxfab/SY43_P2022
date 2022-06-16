package com.example.fluz.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Category
import com.example.fluz.data.repositories.*
import com.example.fluz.databinding.FragmentVariableExpensesBinding
import com.example.fluz.ui.HomeActivity
import com.example.fluz.ui.adapters.FixedTransactionListAdapter
import com.example.fluz.ui.adapters.VariableExpensesListAdapter
import com.example.fluz.ui.viewmodels.FixedTransactionViewModel
import com.example.fluz.ui.viewmodels.FixedTransactionViewModelFactory
import com.example.fluz.ui.viewmodels.VariableExpensesViewModel
import com.example.fluz.ui.viewmodels.VariableExpensesViewModelFactory

class VariableExpenses : Fragment() {

    private lateinit var binding: FragmentVariableExpensesBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }
    private val budgetItemRepository by lazy { BudgetItemRepository(database.BudgetItemDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val variableExpensesViewModel: VariableExpensesViewModel by viewModels {
        VariableExpensesViewModelFactory(
            userRepository,
            categoryRepository,
            budgetItemRepository,
            budgetRepository,
            transactionRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVariableExpensesBinding.inflate(layoutInflater)

        val sharedPref: SharedPreferences = this.context!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        val recyclerView = binding.variableExpensesRv
        val adapter = VariableExpensesListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val baseTxtTotalIncome: String = "Total Income = "
        val baseTxtTotalExpenses: String = "Total Expenses = "

        variableExpensesViewModel.income.observe(this) {income->
            binding.txtTotalIncome.text = baseTxtTotalIncome + income.toString() + " €"
        }

        variableExpensesViewModel.budgetItems.observe(this) {budgetItems ->
            budgetItems.let { adapter.submitList(it) }
            variableExpensesViewModel.getTotalExpenses(connectedUserId.toInt())
        }


        variableExpensesViewModel.expenses.observe(this) {expenses ->
            if (expenses == variableExpensesViewModel.income.value && variableExpensesViewModel.income.value != 0) {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#78e08f"))
                binding.btnContinueVariableExpense.isEnabled = true
            } else {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#DA6B6B"))
                binding.btnContinueVariableExpense.isEnabled = false
            }
            binding.txtTotalExpenses.text = baseTxtTotalExpenses + expenses.toString() + " €"
        }

        variableExpensesViewModel.allCategories.observe(this) {categories ->
            val spinnerCategories = binding.spinnerCategoryVe
            val adapterSpinnerCategories =
                ArrayAdapter<Category>(this.context!!, android.R.layout.simple_spinner_dropdown_item, categories)
            spinnerCategories.adapter = adapterSpinnerCategories
        }

        variableExpensesViewModel.errorMessage.observe(this) {errorMessage ->
            binding.errorTxtVariableExpenses.text = errorMessage
        }

        variableExpensesViewModel.getTotalIncome(connectedUserId.toInt())
        variableExpensesViewModel.getTotalExpenses(connectedUserId.toInt())

        binding.backArrowVariableExpenses.setOnClickListener {
            findNavController().navigate(R.id.action_variableExpenses_to_fixedExpenses)
        }

        binding.btnAddVariableExpense.setOnClickListener {
            val amount = binding.editTextAmount.text.toString()
            val category: Category = binding.spinnerCategoryVe.selectedItem as Category

            val errorText = binding.errorTxtVariableExpenses

            if (TextUtils.isEmpty(amount)) {
                errorText.text = "Please fill all fields"
            } else {
                variableExpensesViewModel.addBudgetItem(amount.toInt(), category)

                binding.editTextAmount.text.clear()
            }
        }

        binding.btnContinueVariableExpense.setOnClickListener {
            variableExpensesViewModel.createBudget(connectedUserId.toInt())


        }

        variableExpensesViewModel.budgets.observe(this) {budgets ->
            val intent = Intent(this@VariableExpenses.context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return binding.root
    }
}