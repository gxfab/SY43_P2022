package com.example.fluz.ui.fragments

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
import com.example.fluz.data.repositories.TransactionRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentFixedExpensesBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFixedExpensesBinding.inflate(layoutInflater)

        binding.txtTotalIncome.text = binding.txtTotalIncome.text.toString() + args.totalIncome.toString() + " €"

        binding.backArrowFixedExpenses.setOnClickListener {
            findNavController().navigate(R.id.action_fixedExpenses_to_fixedIncome)
        }

        return binding.root
    }
}