package com.example.zeroday.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.zeroday.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navigationView.setSelectedItemId(R.id.add)
        loadFragment(AddFragment())

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.chart -> {
                    loadFragment(ChartFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.add -> {
                    loadFragment(AddFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.piggy -> {
                    loadFragment(PiggyFragment())
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }
    //Fonction de chargement de fragment
    private fun loadFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}