package com.example.lafo_cheuse

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lafo_cheuse.fragment.view.ChartFragment
import com.example.lafo_cheuse.fragment.view.HomeFragment
import com.example.lafo_cheuse.fragment.view.SetIncomesExpensesFragment
import com.example.lafo_cheuse.fragment.view.SettingsFragment
import com.example.lafo_cheuse.material.ViewPagerAdapter
import com.example.lafo_cheuse.viewmodels.OptionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import kotlin.collections.ArrayList


const val EXTRA_MESSAGE = "com.example.test.MESSAGE"

/**
 * Main activity of our application. This activity owns all the main menu fragments.
 *
 * @property optionViewModel - An [OptionViewModel] instance to get the theme of the application
 * @property navigationView - A [BottomNavigationView] that let the user going through the fragments by clicking on it
 * @property chartFragment - A [Fragment] that dispose of charts for specific budget
 * @property homeFragment - A [Fragment] that dispose of charts for the general budget
 * @property setIncomesExpensesFragment - A [Fragment] that let the user put incomes & expenses in the budget
 * @property settingsFragment - A [Fragment] that let the user choose some options
 * @property fragmentsList - A list of all the [Fragment] in the [MainActivity] to switch easily with the [ViewPager2]
 * @property fragmentsIdList - A list of all the [Fragment] IN in the [MainActivity] to switch easily with the [navigationView]
 *
 */
class MainActivity : AppCompatActivity() {
    private val optionViewModel : OptionViewModel by viewModels()

    var navigationView: BottomNavigationView? = null

    var chartFragment: ChartFragment? = ChartFragment()
    var homeFragment: HomeFragment? = HomeFragment()
    var setIncomesExpensesFragment: SetIncomesExpensesFragment? = SetIncomesExpensesFragment()
    var settingsFragment: SettingsFragment? = SettingsFragment()
    var fragmentsList : ArrayList<Fragment>? = null
    var fragmentsIdList : ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTheme()

        fragmentsList = arrayListOf<Fragment>(homeFragment!!, chartFragment!!, setIncomesExpensesFragment!!, settingsFragment!!)
        fragmentsIdList = arrayListOf<Int>(R.id.homeFragment, R.id.chartFragment, R.id.setIncomesExpensesFragment, R.id.settingsFragment)

        navigationView = findViewById(R.id.bottom_navigation)

        val adapter = ViewPagerAdapter(
            fragmentsList!!,
            supportFragmentManager,
            lifecycle,
        )

        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = adapter

        navigationView!!.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    pager.currentItem = 0
                }
                R.id.chartFragment -> {
                    pager.currentItem = 1
                }
                R.id.setIncomesExpensesFragment->  {
                    pager.currentItem = 2
                }
                R.id.settingsFragment -> {
                    pager.currentItem = 3
                }
            }
            true
        }

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navigationView!!.selectedItemId = fragmentsIdList!![position]
            }
        })

    }

    /**
     * Initialize the theme of the application. It is set on [MODE_NIGHT_FOLLOW_SYSTEM] by default
     *
     */
    private fun initTheme() {
        optionViewModel.getOption("option_theme")?.observe(this) { themeOption ->
            if(themeOption != null) {
                optionViewModel.getOptionFields(themeOption)?.observe(this) { themes ->
                    for(field in themes) {
                        when(field.fieldValue) {
                            "light_theme" -> {
                                if(field.chosen) {
                                    changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_NO)
                                }
                            }
                            "dark_theme" -> {
                                if(field.chosen) {
                                    changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_YES)
                                }
                            }
                            "system_theme" -> {
                                if(field.chosen) {
                                    changeApplicationTheme(MODE_NIGHT_FOLLOW_SYSTEM)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * function to change the current [Fragment] in the [navigationView] and [ViewPager2]
     *
     * @param fragment - the [Fragment] to set on highlight
     * @return [Unit] nothing
     */
    fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }

    /**
     * Function to change the application theme
     *
     * @param mode - [Int], can have 3 values :
     *  - [AppCompatDelegate.MODE_NIGHT_YES]
     *  - [AppCompatDelegate.MODE_NIGHT_NO]
     *  - [AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM]
     */
    private fun changeApplicationTheme(mode : Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
        delegate.localNightMode = mode
    }



}