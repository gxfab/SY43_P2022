package com.example.fluz.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentHomeBinding
import com.example.fluz.ui.CreateBudgetActivity
import com.example.fluz.ui.viewmodels.HomeViewModel
import com.example.fluz.ui.viewmodels.HomeViewModelFactory
import com.example.fluz.ui.viewmodels.LoginViewModel
import com.example.fluz.ui.viewmodels.LoginViewModelFactory

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        /*homeViewModel.budgets.observe(this) {budgets ->
            if (budgets.isEmpty()) {
                val intent = Intent(this@Home.context, CreateBudgetActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }*/

        homeViewModel.getUserBudgetList(connectedUserId.toInt())

        return binding.root
    }

}