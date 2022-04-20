package com.example.sy43_p2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sy43_p2022.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //On injecte le HomeFragment dans notre fragment_container de activity_main
        val transaction = supportFragmentManager.beginTransaction() //necessaire pour manipuler les fragments
        transaction.replace(R.id.fragment_container, HomeFragment()) //remplacement du fragment (en attente)
        transaction.addToBackStack(null) //pas de retour
        transaction.commit() //on effectue le remplacement

    }
}