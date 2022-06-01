package com.example.lafo_cheuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.fragment.view.ChartFragment
import com.example.lafo_cheuse.fragment.view.HomeFragment
import com.example.lafo_cheuse.fragment.view.SetIncomesExpensesFragment
import com.example.lafo_cheuse.fragment.view.SettingsFragment
import com.example.lafo_cheuse.material.ViewPagerAdapter
import com.example.lafo_cheuse.models.Category
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*


const val EXTRA_MESSAGE = "com.example.test.MESSAGE"

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    var navigationView: BottomNavigationView? = null

    var chartFragment: ChartFragment? = ChartFragment();
    var homeFragment: HomeFragment? = HomeFragment();
    var setIncomesExpensesFragment: SetIncomesExpensesFragment? = SetIncomesExpensesFragment();
    var settingsFragment: SettingsFragment? = SettingsFragment();
    var fragmentsList : ArrayList<Fragment>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentsList = arrayListOf<Fragment>(homeFragment!!, chartFragment!!, setIncomesExpensesFragment!!, settingsFragment!!);

        navigationView = findViewById(R.id.bottom_navigation);

        val adapter = ViewPagerAdapter(
            fragmentsList!!,
            supportFragmentManager,
            lifecycle
        )

        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = adapter

        navigationView!!.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    pager.currentItem = 0;
                }
                R.id.chartFragment -> {
                    pager.currentItem = 1;
                }
                R.id.setIncomesExpensesFragment->  {
                    pager.currentItem = 2;
                }
                R.id.settingsFragment -> {
                    pager.currentItem = 3;
                }
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



}