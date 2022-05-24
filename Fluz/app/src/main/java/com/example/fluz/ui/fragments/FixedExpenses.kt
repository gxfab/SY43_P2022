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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentFixedExpensesBinding
import com.example.fluz.ui.HomeActivity
import com.example.fluz.ui.adapters.FixedTransactionListAdapter
import com.example.fluz.ui.viewmodels.FixedTransactionViewModel
import com.example.fluz.ui.viewmodels.FixedTransactionViewModelFactory

class FixedExpenses : Fragment() {

    private lateinit var binding: FragmentFixedExpensesBinding
    private val args: FixedExpensesArgs by navArgs()

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }

    private val fixedTransactionViewModel: FixedTransactionViewModel by viewModels {
        FixedTransactionViewModelFactory(userRepository, transactionRepository)
    }

    private var totalFixedExpenses = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFixedExpensesBinding.inflate(layoutInflater)

        val totalIncome = args.totalIncome

        binding.txtTotalIncome.text = binding.txtTotalIncome.text.toString() + totalIncome.toString() + " €"

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

        fixedTransactionViewModel.expensesWithCategory.observe(this) { transactions ->
            transactions.let { adapter.submitList(it) }
            totalFixedExpenses = 0
            for (transaction in transactions) {
                totalFixedExpenses += transaction.transaction.amount
            }

            binding.txtTotalExpenses.text = baseTxtTotalFixedExpenses + totalFixedExpenses.toString() + " €"
            if (totalFixedExpenses == totalIncome) {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#78e08f"))
            } else {
                binding.txtTotalExpenses.setTextColor(Color.parseColor("#DA6B6B"))
            }
        }

        fixedTransactionViewModel.getTransactionsWithCategory("expense")

        binding.btnAddFixedExpense.setOnClickListener {
            val expenseTag = binding.editTextExpenseTag.text.toString()
            val amount = binding.editTextAmount.text.toString()

            val errorText = binding.errorTxtFixedExpenses

            if (TextUtils.isEmpty(expenseTag) || TextUtils.isEmpty(amount)) {
                errorText.text = "Please fill all fields"
            } else if (amount.toInt() + totalFixedExpenses > totalIncome) {
                errorText.text = "Expenses must be less or equal than total income"
            } else if (amount.toInt() == 0) {
                errorText.text = "Expense must be positive"
            } else {
                fixedTransactionViewModel.insert(
                    amount.toInt(),
                    expenseTag,
                    "expense",
                    1,
                    connectedUserId.toInt()
                )

                binding.editTextExpenseTag.text.clear()
                binding.editTextAmount.text.clear()
            }
        }

        binding.btnContinueFixedExpense.setOnClickListener {
            val intent = Intent(this.context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return binding.root
    }
}