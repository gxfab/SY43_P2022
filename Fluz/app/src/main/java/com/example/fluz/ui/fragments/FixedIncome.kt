package com.example.fluz.ui.fragments

import android.R
import android.content.Context
import android.content.SharedPreferences
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
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.User
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentFixedIncomeBinding
import com.example.fluz.ui.adapters.FixedTransactionListAdapter
import com.example.fluz.ui.viewmodels.*
import java.util.*

class FixedIncome : Fragment() {

    private lateinit var binding: FragmentFixedIncomeBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }

    private val fixedTransactionViewModel: FixedTransactionViewModel by viewModels {
        FixedTransactionViewModelFactory(userRepository, transactionRepository, categoryRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFixedIncomeBinding.inflate(layoutInflater)

        val sharedPref: SharedPreferences = this.context!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        val recyclerView = binding.fixedIncomeRv
        val adapter = FixedTransactionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        fixedTransactionViewModel.connectedUserId = connectedUserId.toInt()

        fixedTransactionViewModel.incomeWithCategory.observe(this) { transactions ->
            transactions.let { adapter.submitList(it) }
        }

        fixedTransactionViewModel.allCategories.observe(this) {categories ->
            val spinnerCategories = binding.spinnerCategoryFi
            val adapterSpinnerCategories =
                ArrayAdapter<Category>(this.context!!, R.layout.simple_spinner_dropdown_item, categories)
            spinnerCategories.adapter = adapterSpinnerCategories
        }

        fixedTransactionViewModel.getTransactionsWithCategory("income")

        binding.btnAddFixedIncome.setOnClickListener {
            val tag = binding.editTextIncomeTag.text.toString()
            val amount = binding.editTextAmount.text.toString()
            val category: Category = binding.spinnerCategoryFi.selectedItem as Category

            val errorText = binding.errorTxtFixedIncome

            if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(amount)) {
                errorText.text = "Please fill all fields"
            } else if (amount == "0") {
                errorText.text = "Amount must me positive"
            } else {
                fixedTransactionViewModel.insert(
                    amount.toInt(),
                    tag,
                    "income",
                    category.id,
                    connectedUserId.toInt()
                )

                binding.editTextIncomeTag.text.clear()
                binding.editTextAmount.text.clear()
            }
        }

        binding.btnContinueFixedIncome.setOnClickListener {
            val errorText = binding.errorTxtFixedIncome
            if (fixedTransactionViewModel.incomeWithCategory.value!!.isEmpty()) {
                errorText.text = "Add at least one income"
            } else {
                findNavController().navigate(com.example.fluz.R.id.action_fixedIncome_to_fixedExpenses)
            }
        }

        return binding.root
    }
}