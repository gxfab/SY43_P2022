package com.example.fluz.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentParametersBinding
import com.example.fluz.ui.CreateBudgetActivity
import com.example.fluz.ui.HomeActivity
import com.example.fluz.ui.MainActivity
import com.example.fluz.ui.viewmodels.ParametersViewModel
import com.example.fluz.ui.viewmodels.ParametersViewModelFactory
import com.example.fluz.ui.viewmodels.RegisterViewModel
import com.example.fluz.ui.viewmodels.RegisterViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_parameters.view.*

/**
 * Parameters fragment
 *
 */
class Parameters : Fragment() {
    private lateinit var binding: FragmentParametersBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }

    private val parametersViewModel: ParametersViewModel by viewModels {
        ParametersViewModelFactory(userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentParametersBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        parametersViewModel.currentUser.observe(this) { currentUser ->
            binding.txtUsername.text = currentUser.username
            binding.txtEmail.text = currentUser.email_address
            binding.txtCurrency.text = currentUser.currency
            binding.txtStartDay.text = currentUser.budget_start_day.toString()
        }

        parametersViewModel.getCurrentUser(connectedUserId.toInt())

        binding.btnDisconnect.setOnClickListener {
            with(sharedPref.edit()) {
                putLong("connectedUserId", -1)
                apply()

                val intent = Intent(this@Parameters.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnResetBudget.setOnClickListener {
            val intent = Intent(this@Parameters.context, CreateBudgetActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return binding.root
    }
}