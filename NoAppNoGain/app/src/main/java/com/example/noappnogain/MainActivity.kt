package com.example.noappnogain;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.noappnogain.fragments.AccueilFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        // Charger notre MouvementFinancierRepo
        val mouvRepo = MouvementFinancierRepo()

        // Mise à jour des données
        mouvRepo.updateData{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,AccueilFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }

}
