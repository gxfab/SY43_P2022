package com.example.fluz.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.fluz.R
import com.example.fluz.databinding.ActivityHomeBinding
import com.example.fluz.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment_content_home_main)
        bottomNavigationView.setupWithNavController(navController)

        val bar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        val fab = findViewById<FloatingActionButton>(R.id.btn_add)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.nav_transaction || destination.id == R.id.categories) {

                bottomNavigationView.visibility = View.GONE
                bar.visibility = View.GONE
                fab.hide()
            } else {

                bottomNavigationView.visibility = View.VISIBLE
                bar.visibility = View.VISIBLE
                fab.show()
            }
        }

        binding.btnAdd.setOnClickListener {
            navController.navigate(R.id.nav_transaction)
        }
    }


}