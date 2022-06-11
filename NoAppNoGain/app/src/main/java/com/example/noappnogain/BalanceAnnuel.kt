package com.example.noappnogain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noappnogain.databinding.ActivityBalanceAnnuelBinding

class BalanceAnnuel : AppCompatActivity() {

    private lateinit var binding: ActivityBalanceAnnuelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalanceAnnuelBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}