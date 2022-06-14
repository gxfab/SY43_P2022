package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AjoutCategorie extends AppCompatActivity {

    Button valider, annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_categorie);

        valider = findViewById(R.id.valider);
        annuler = findViewById(R.id.annuler);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add categorie to database
                finish();
            }
        });

        annuler.setOnClickListener(v -> finish());

        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.BudgetPrev);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.MainMenu:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.BudgetPrev:
                        startActivity(new Intent(getApplicationContext(),BudgetPrev.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ChangeBudget:
                        startActivity(new Intent(getApplicationContext(),ChangerBudget.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

}