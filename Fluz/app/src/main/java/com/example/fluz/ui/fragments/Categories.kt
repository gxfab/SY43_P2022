package com.example.fluz.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fluz.R
import com.example.fluz.databinding.FragmentCategoriesBinding
import android.content.DialogInterface

import android.text.Editable

import dagger.hilt.android.qualifiers.ActivityContext

import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.*
import com.example.fluz.ui.adapters.CategoriesAdapter
import com.example.fluz.ui.adapters.ExpensesAdapter
import com.example.fluz.ui.viewmodels.CategoriesViewModel
import com.example.fluz.ui.viewmodels.CategoriesViewModelFactory
import com.example.fluz.ui.viewmodels.ExpensesViewModel
import com.example.fluz.ui.viewmodels.ExpensesViewModelFactory

/**
 * Categories fragment
 *
 */
class Categories : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }

    private val categoriesViewModel: CategoriesViewModel by viewModels {
        CategoriesViewModelFactory(userRepository, budgetRepository, categoryRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        val recyclerView = binding.rvCategories
        val adapter = CategoriesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.backArrow2.setOnClickListener {
            findNavController().navigate(R.id.action_categories_to_nav_transaction)
        }

        categoriesViewModel.categories.observe(this) {categories ->
            categories.let { adapter.submitList(it) }
        }

        categoriesViewModel.errorMessage.observe(this) {errorMessage ->
            binding.errorText.text = errorMessage
        }

        categoriesViewModel.getAllCategories(connectedUserId.toInt())


        return binding.root
    }

}