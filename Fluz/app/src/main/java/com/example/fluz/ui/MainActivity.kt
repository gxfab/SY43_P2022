package com.example.fluz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import com.example.fluz.R
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.dao.CategoryDao
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.UserWithBudgets
import com.example.fluz.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPref = this.getSharedPreferences("shared-pref", Context.MODE_PRIVATE)
        if (sharedPref.getLong("connectedUserId", -1) != -1L) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val categoryDao: CategoryDao = AppDatabase(this).CategoryDao()
        val transactionsDao = AppDatabase(this).TransactionDao()


        runBlocking {
            launch {
                categoryDao.deleteAll()

                categoryDao.insert(Category(id = 1, title = "Food", type = "Global"))
                categoryDao.insert(Category(id = 2, title = "Housing", type = "Global"))
                categoryDao.insert(Category(id = 3, title = "Transportation", type = "Global"))
                categoryDao.insert(Category(id = 4, title = "Clothing", type = "Global"))
                categoryDao.insert(Category(id = 5, title = "Savings", type = "Global"))
                categoryDao.insert(Category(id = 6, title = "rent", type = "Global"))
                categoryDao.insert(Category(id = 7, title = "Property taxes", type = "Global"))
                categoryDao.insert(Category(id = 8, title = "Household repairs", type = "Global"))
                categoryDao.insert(Category(id = 9, title = "Car payment", type = "Global"))
                categoryDao.insert(Category(id = 10, title = "Car warranty", type = "Global"))
                categoryDao.insert(Category(id = 11, title = "Gas", type = "Global"))
                categoryDao.insert(Category(id = 12, title = "Tires", type = "Global"))
                categoryDao.insert(Category(id = 13, title = "Maintenance and oil changes", type = "Global"))
                categoryDao.insert(Category(id = 14, title = "Groceries", type = "Global"))
                categoryDao.insert(Category(id = 15, title = "Restaurants", type = "Global"))
                categoryDao.insert(Category(id = 16, title = "Electricity", type = "Global"))
                categoryDao.insert(Category(id = 17, title = "Water", type = "Global"))
                categoryDao.insert(Category(id = 18, title = "Garbage", type = "Global"))
                categoryDao.insert(Category(id = 19, title = "Phones", type = "Global"))
                categoryDao.insert(Category(id = 20, title = "Internet", type = "Global"))
                categoryDao.insert(Category(id = 21, title = "Primary care", type = "Global"))
                categoryDao.insert(Category(id = 22, title = "Urgent care", type = "Global"))
                categoryDao.insert(Category(id = 23, title = "Movies", type = "Global"))
                categoryDao.insert(Category(id = 24, title = "Concerts", type = "Global"))
                categoryDao.insert(Category(id = 25, title = "Vacations", type = "Global"))
                categoryDao.insert(Category(id = 26, title = "Work", type = "Global"))

            }
        }


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