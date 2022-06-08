package com.example.fluz.ui.fragments

import android.content.Context
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Category
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentFixedExpensesBinding
import com.example.fluz.ui.adapters.FixedTransactionListAdapter
import com.example.fluz.ui.viewmodels.FixedTransactionViewModel
import com.example.fluz.ui.viewmodels.FixedTransactionViewModelFactory

class FixedExpenses : Fragment() {

    private lateinit var binding: FragmentFixedExpensesBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }

    private val fixedTransactionViewModel: FixedTransactionViewModel by viewModels {
        FixedTransactionViewModelFactory(userRepository, transactionRepository, categoryRepository)
    }

    private var totalFixedExpenses = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFixedExpensesBinding.inflate(layoutInflater)

        binding.backArrowFixedExpenses.setOnClickListener {
            findNavController().navigate(R.id.action_fixedExpenses_to_fixedIncome)
        }

        val sharedPref: SharedPreferences = this.context!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        fixedTransactionViewModel.connectedUserId = connectedUserId.toInt()

        val baseTxtTotalFixedExpenses = "Total Expenses = "

        val recyclerView = binding.fixedExpensesRv
        val adapter = FixedTransactionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        fixedTransactionViewModel.allCategories.observe(this) {categories ->
            val spinnerCategories = binding.spinnerCategoryFe
            val adapterSpinnerCategories =
                ArrayAdapter<Category>(this.context!!, android.R.layout.simple_spinner_dropdown_item, categories)
            spinnerCategories.adapter = adapterSpinnerCategories
        }

        var totalIncome: Int? = 0

        fixedTransactionViewModel.incomeWithCategory.observe(this) { income ->
            var total = 0;
            for (transaction in income) {
                total += transaction.transaction.amount;
            }

            totalIncome = total;
            binding.txtTotalIncome.text = binding.txtTotalIncome.text.toString() + totalIncome.toString() + " €"
        }

        binding.txtTotalExpenses.setTextColor(Color.parseColor("#DA6B6B"))

        fixedTransactionViewModel.expensesWithCategory.observe(this) { transactions ->
            transactions.let { adapter.submitList(it) }
            totalFixedExpenses = 0
            for (transaction in transactions) {
                totalFixedExpenses += transaction.transaction.amount
            }

            binding.txtTotalExpenses.text = baseTxtTotalFixedExpenses + totalFixedExpenses.toString() + " €"
            if (totalFixedExpenses == totalIncome && totalIncome != 0) {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#78e08f"))
            } else {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#DA6B6B"))
            }
        }

        fixedTransactionViewModel.getTransactionsWithCategory("expense")
        fixedTransactionViewModel.getTransactionsWithCategory("income")

        binding.btnAddFixedExpense.setOnClickListener {
            val expenseTag = binding.editTextExpenseTag.text.toString()
            val amount = binding.editTextAmount.text.toString()
            val category: Category = binding.spinnerCategoryFe.selectedItem as Category

            val errorText = binding.errorTxtFixedExpenses

            if (TextUtils.isEmpty(expenseTag) || TextUtils.isEmpty(amount)) {
                errorText.text = "Please fill all fields"
            } else if (amount.toInt() + totalFixedExpenses > totalIncome!!) {
                errorText.text = "Expenses must be less or equal than total income"
            } else if (amount.toInt() == 0) {
                errorText.text = "Expense must be positive"
            } else {
                fixedTransactionViewModel.insert(
                    amount.toInt(),
                    expenseTag,
                    "expense",
                    category.id,
                    connectedUserId.toInt()
                )

                binding.editTextExpenseTag.text.clear()
                binding.editTextAmount.text.clear()
            }
        }

        binding.btnContinueFixedExpense.setOnClickListener {
            findNavController().navigate(R.id.action_fixedExpenses_to_variableExpenses)
        }

        return binding.root
    }
}