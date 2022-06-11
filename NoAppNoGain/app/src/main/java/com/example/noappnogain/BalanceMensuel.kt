package com.example.noappnogain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.noappnogain.databinding.ActivityBalanceMensuelBinding

class BalanceMensuel : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBalanceMensuelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBalanceMensuelBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}