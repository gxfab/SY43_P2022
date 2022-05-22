package com.example.fluz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fluz.databinding.ActivityCreateBudgetBinding

class CreateBudgetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBudgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBudgetBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}