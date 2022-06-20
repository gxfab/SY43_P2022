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
import com.example.fluz.data.repositories.BudgetRepository
import com.example.fluz.data.repositories.UserRepository
import com.example.fluz.databinding.FragmentHomeBinding
import com.example.fluz.ui.CreateBudgetActivity
import com.example.fluz.ui.viewmodels.HomeViewModel
import com.example.fluz.ui.viewmodels.HomeViewModelFactory
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.days

/**
 * Home fragment
 *
 */
class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val database by lazy { AppDatabase(this.context!!) }
    private val userRepository by lazy { UserRepository(database.UserDao()) }
    private val budgetRepository by lazy { BudgetRepository(database.BudgetDao()) }

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(userRepository, budgetRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val sharedPref = this.activity!!.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        val connectedUserId = sharedPref.getLong("connectedUserId", -1)

        homeViewModel.budgets.observe(this) { budgets ->
            if (budgets.isEmpty()) {
                val intent = Intent(this@Home.context, CreateBudgetActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            val currentBudget = budgets.last()
            binding.txtTotalAmount.text = currentBudget.total_amount.toString() + " €"
            binding.txtBudgetDate.text = currentBudget.start_date + " - " + currentBudget.end_date

            val dateElements = currentBudget.end_date.toString().split(" ").toMutableList()
            if (dateElements[0].toInt() >= 1 && dateElements[0].toInt() <= 9) {
                dateElements[0] = "0" + dateElements[0]
            }
            val dateStr = dateElements[0] + "/" + dateElements[1] + "/" + dateElements[2];

            println(dateStr)

            val sdf = SimpleDateFormat("dd/MMM/yyyy")
            val date = sdf.parse(dateStr)

            println(date)

            val currentLocalDateTime = LocalDateTime.now()
            val currentDateStr =
                currentLocalDateTime.format(DateTimeFormatter.ofPattern("dd/MMM/YYYY"))
            println(currentDateStr)
            val sdf2 = SimpleDateFormat("dd/MMM/yyyy")
            val currentDate = sdf2.parse(currentDateStr)

            println(currentDate)

            val daysRemaining =
                TimeUnit.DAYS.convert(date.time - currentDate.time, TimeUnit.MILLISECONDS) - 1;
            binding.txtDaysRemaining.text = daysRemaining.toString() + " days remaining"

            homeViewModel.budgets.value?.last()
                ?.let { homeViewModel.getTransactions(connectedUserId.toInt(), it.id) }
        }

        homeViewModel.totalTransactions.observe(this) { totalTransactions ->
            binding.txtSpentAmount.text = totalTransactions.toString() + " €"

            binding.txtLeftForMonthAmount.text =
                ((homeViewModel.budgets.value?.last()?.total_amount)?.minus(
                    totalTransactions
                )).toString() + " €"

            binding.gauge.setHighValue(((totalTransactions.toFloat() / homeViewModel.budgets.value?.last()?.total_amount!!) * 100).toFloat())
        }

        binding.gauge.setHighValue(60.0f)
        println(binding.gauge.highValue)

        homeViewModel.getUserBudgetList(connectedUserId.toInt())

        return binding.root
    }

}