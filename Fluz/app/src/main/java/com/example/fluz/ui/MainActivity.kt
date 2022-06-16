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