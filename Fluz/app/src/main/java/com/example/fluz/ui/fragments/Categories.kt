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

class Categories : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }
    private val userCategoryRepository by lazy { UserCategoryRepository(database.UserCategoryDao()) }

    private val categoriesViewModel: CategoriesViewModel by viewModels {
        CategoriesViewModelFactory(userRepository, categoryRepository, userCategoryRepository)
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

        binding.floatingActionButton.setOnClickListener {
            val alert: AlertDialog.Builder = AlertDialog.Builder(this.context!!)

            val edittext = EditText(this.context!!)
            alert.setMessage("Add a category")

            alert.setView(edittext)

            alert.setPositiveButton("Add",
                DialogInterface.OnClickListener { dialog, whichButton -> //What ever you want to do with the value
                    val categoryTitle = edittext.text.toString()
                    categoriesViewModel.addCategory(categoryTitle, connectedUserId.toInt())
                })

            alert.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    // what ever you want to do with No option.
                })

            alert.show()
        }

        categoriesViewModel.categories.observe(this) {categories ->
            categories.let { adapter.submitList(it) }
        }

        categoriesViewModel.allCategories.observe(this) {allCategories ->
            if (allCategories.last().type == "User") {
                categoriesViewModel.addUserCategory(connectedUserId.toInt(), allCategories.last().id)
            }
        }

        categoriesViewModel.errorMessage.observe(this) {errorMessage ->
            binding.errorText.text = errorMessage
        }

        categoriesViewModel.getAllCategories(connectedUserId.toInt())


        return binding.root
    }

}