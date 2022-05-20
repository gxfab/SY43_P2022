package com.example.fluz.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.example.fluz.R
import com.example.fluz.databinding.ActivityHomeBinding
import com.example.fluz.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}