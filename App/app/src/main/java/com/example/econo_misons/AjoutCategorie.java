package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AjoutCategorie extends AppCompatActivity {

    Button valider, annuler;
    EditText color, name;

    private DBViewModel dbViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_categorie);

        // setting up the widget, bottom bar menu and data
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        valider = findViewById(R.id.valider);
        annuler = findViewById(R.id.annuler);

        color = findViewById(R.id.color);
        name = findViewById(R.id.cat_nom);

        valider.setOnClickListener(v -> addCategory());

        annuler.setOnClickListener(v -> finish());

        this.makeBottomBar();
    }

    // Function that adds a category to the DB
    private void addCategory(){
        if (!(name.getText().toString().isEmpty() && color.getText().toString().isEmpty())) {
            dbViewModel.addCategory(new Category(name.getText().toString(),color.getText().toString()));

            finish();
        }
    }

    // Functions that makes and initializes the bottom menu
    private void makeBottomBar(){
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelected(false);
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