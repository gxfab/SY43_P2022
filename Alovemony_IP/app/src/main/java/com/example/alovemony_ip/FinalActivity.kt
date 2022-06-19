package com.example.alovemony_ip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alovemony_ip.fragments.ProjetFragment

class FinalActivity: AppCompatActivity() {

    /*
            Activité en cours de developpement, qui s'afficherait à la fin de chaque cycle, permettant à l'utilisateur de choisir où mettre l'argent qu'il lui reste
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.frame_final_container,ProjetFragment(this))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}