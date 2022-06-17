package com.example.agedor.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agedor.R;
import com.example.agedor.view.dettes.EditDettesActivity;
import com.example.agedor.view.enveloppes.categories.EditCategoriesActivity;
import com.example.agedor.view.factures.EditFacturesActivity;
import com.example.agedor.view.projets.EditProjetsActivity;
import com.example.agedor.view.revenus.EditRevenusActivity;

public class EditerBudgetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_budget);
        getSupportActionBar().setTitle("Planifier mon budget");
    }

    public void editCategories(View view){
        Intent intent = new Intent(this, EditCategoriesActivity.class);
        startActivity(intent);
    }

    // Editer les projets
    public void editProjets(View view){
        Intent intent = new Intent(this, EditProjetsActivity.class);
        startActivity(intent);
    }

    // Editer les dettes
    public void editDettes(View view){
        Intent intent = new Intent(this, EditDettesActivity.class);
        startActivity(intent);
    }
    
    // Editer les factures
    public void editFactures(View view){
        Intent intent = new Intent(this, EditFacturesActivity.class);
        startActivity(intent);
    }

    // Editer les revenus
    public void editRevenus(View view){
        Intent intent = new Intent(this, EditRevenusActivity.class);
        startActivity(intent);
    }

    public void addCategorie() {
        Log.i("EditCategoriesActivity", "addCategorie");
    }

}
