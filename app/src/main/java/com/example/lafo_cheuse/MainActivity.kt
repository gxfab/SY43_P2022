package com.example.lafo_cheuse

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.fragment.view.ChartFragment
import com.example.lafo_cheuse.fragment.view.HomeFragment
import com.example.lafo_cheuse.fragment.view.SetIncomesExpensesFragment
import com.example.lafo_cheuse.fragment.view.SettingsFragment
import com.example.lafo_cheuse.models.Category
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.*


const val EXTRA_MESSAGE = "com.exemple.test.MESSAGE"

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    var navigationView: BottomNavigationView? = null;

    var chartFragment: ChartFragment = ChartFragment();
    var homeFragment: HomeFragment = HomeFragment();
    var setIncomesExpensesFragment: SetIncomesExpensesFragment = SetIncomesExpensesFragment();
    var settingsFragment: SettingsFragment = SettingsFragment();

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.bottom_navigation);

        makeCurrentFragment(homeFragment)

        navigationView!!.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.chart -> makeCurrentFragment(chartFragment)
                R.id.income_expense -> makeCurrentFragment(setIncomesExpensesFragment)
                R.id.settings -> makeCurrentFragment(settingsFragment)
            }
            true
        }

        GlobalScope.launch(Dispatchers.Main) {

            val db = Room.databaseBuilder(
                applicationContext,
                LafoCheuseDatabase::class.java, "BDD Lafo-Cheuse"
            ).build()
            launch(Dispatchers.IO) {
                val catDao = db.categoryDao()
                catDao!!.createCategory(Category("Courses", "\uD83D\uDED2"))
                catDao.createCategory(Category("Bourses", "\uD83D\uDCB0"))
            }



        }
    }

    fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }

    fun sendMessage(view: View) {

    }

}