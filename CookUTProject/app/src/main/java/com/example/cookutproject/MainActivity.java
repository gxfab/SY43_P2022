package com.example.cookutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    /**
     *Fonction se déclenchant lors de l'arrivée sur l'activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *Renvoie sur l'activité "saisie"
     * @param view ici saisie
     */
    public void changeActivitySaisie(View view){
        Intent intent = new Intent(this, Saisie.class);
        startActivity(intent);
    }

    /**
     *Renvoie sur l'activité "recap"
     * @param view ici recap
     */
    public void changeActivityRecap(View view){
        Intent intent = new Intent(this, Recap.class);
        startActivity(intent);
    }

    /**
     *Renvoie sur l'activité "event"
     * @param view ici event
     */
    public void changeActivityEvent(View view){
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }
}