package com.example.fluz.ui.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserCategoryRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentTransactionBinding
import com.example.fluz.ui.viewmodels.CategoriesViewModel
import com.example.fluz.ui.viewmodels.CategoriesViewModelFactory
import com.example.fluz.ui.viewmodels.TransactionViewModel
import com.example.fluz.ui.viewmodels.TransactionViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.util.*

/**
 * Transaction fragment
 *
 */
class Transaction : Fragment() {
    private lateinit var binding: FragmentTransactionBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val transactionRepository by lazy { TransactionRepository(database.TransactionDao()) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }

    private val transactionsViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory(transactionRepository, userRepository, categoryRepository)
    }

    private val args: TransactionArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        binding.edtDateTransaction.setOnClickListener {
            println("Button expenses")
            val calendar = Calendar.getInstance()
            val mYear = calendar.get(Calendar.YEAR)
            val mMonth = calendar.get(Calendar.MONTH)
            val mDay = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this.context!!,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    edt_date_transaction.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)

                },
                mYear,
                mMonth,
                mDay
            )

            dpd.show()
        }

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_nav_transaction_to_nav_expenses)
        }

        binding.edtCategoryTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_nav_transaction_to_categories)
        }

        transactionsViewModel.category.observe(this) { category ->
            binding.edtCategoryTransaction.setText(category.title)
        }

        binding.btnExpense.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val amount = binding.edtAmountTransaction.text.toString()
            val date = binding.edtDateTransaction.text.toString()
            val category = binding.edtCategoryTransaction.text.toString()

            val errorText = binding.errorTxt

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(amount) || TextUtils.isEmpty(date) || TextUtils.isEmpty(
                    category
                )
            ) {
                errorText.text = "Please fill all fields"
            } else {
                transactionsViewModel.lastBudget.value?.let { it1 -> transactionsViewModel.addTransaction(title, amount.toInt(), date, args.categoryId, budgetId = it1.id) }
                findNavController().navigate(R.id.action_nav_transaction_to_nav_expenses)
            }

        }

        transactionsViewModel.getLastBudget(connectedUserId.toInt())

        if (args.categoryId != -1) {
            transactionsViewModel.getCategory(args.categoryId)
        }

        return binding.root
    }
}