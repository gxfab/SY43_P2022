package com.example.sy43_p2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sy43_p2022.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // We inject the HomeFragment into the fragment container
        val transaction = supportFragmentManager.beginTransaction() // mandatory to manipulate fragments
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null) // no returns
        transaction.commit()
    }
}