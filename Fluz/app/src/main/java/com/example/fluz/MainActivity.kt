package com.example.fluz

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.UserWithBudgets
import com.example.fluz.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val db = AppDatabase(this)
        val userDao = db.UserDao()
        val budgetDao = db.BudgetDao()

        var users: List<User> = emptyList()
        var budgets: List<Budget> = emptyList()

        var userWithBudgets: UserWithBudgets? = null

        runBlocking {
            launch {
                /**
                try {
                    userDao.insert(
                        User(
                            username = "Robert",
                            email_address = "robert@gmail.com",
                            hash_password = "krfzkfjzjekfk",
                            currency = "euro",
                            created_at = System.currentTimeMillis().toString()
                        )
                    )
                } catch (e: RuntimeException) {
                    println("Cet utilisateur existe déja dans la base !")
                }

                // userDao.deleteAll()
                users = userDao.getAll()

                budgetDao.insert(
                    Budget(
                        start_date = System.currentTimeMillis().toString(),
                        end_date = System.currentTimeMillis().toString(),
                        total_amount = 2000,
                        userId = 5
                    )
                )

                budgets = budgetDao.getAll()
                */

                // userWithBudgets = userDao.getWithBudgets(5)
            }
        }

        for (user in users)
            println(user)

        for (budget in budgets)
            println(budget)

        // println(userWithBudgets?.budgets)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}