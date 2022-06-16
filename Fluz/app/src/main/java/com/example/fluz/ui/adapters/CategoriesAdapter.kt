package com.example.fluz.ui.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.repositories.CategoryRepository
import com.example.fluz.data.repositories.UserCategoryRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.BudgetItemBinding
import com.example.fluz.databinding.CategoryItemBinding
import com.example.fluz.ui.fragments.CategoriesDirections
import com.example.fluz.ui.fragments.ExpensesDirections
import com.example.fluz.ui.viewmodels.CategoriesViewModel
import com.example.fluz.ui.viewmodels.CategoriesViewModelFactory

class CategoriesAdapter(private val fragment: Fragment): ListAdapter<Category, CategoriesAdapter.ViewHolder>(CategoryComparator()) {
    private val database by lazy { AppDatabase(fragment.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val categoryRepository by lazy { CategoryRepository(database.CategoryDao()) }
    private val userCategoryRepository by lazy { UserCategoryRepository(database.UserCategoryDao()) }

    private val categoriesViewModel: CategoriesViewModel by fragment.viewModels {
        CategoriesViewModelFactory(userRepository, categoryRepository, userCategoryRepository)
    }


    inner class ViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CategoryComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val current = getItem(position)

            txtCategoryTitle.text = current.title

            if (current.type == "Global") {
                deleteBtn.visibility = View.GONE
            }

            deleteBtn.setOnClickListener {
                categoriesViewModel.deleteOne(current.id)
            }

            button.setOnClickListener {
                val categoryId = current.id

                val action = CategoriesDirections.actionCategoriesToNavTransaction(categoryId)

                fragment.findNavController().navigate(action)
            }
        }
    }
}