package com.example.agedor.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agedor.R;

public class EditerBudgetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_budget);
    }

    public void editCategories(View view){
        Intent intent = new Intent(this, EditCategoriesActivity.class);
        startActivity(intent);
    }

    // Editer les dépenses
    public void editDepenses(View view){
        Intent intent = new Intent(this, EditDepensesActivity.class);
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

    // Editer les extra
    public void editExtra(View view){
        Intent intent = new Intent(this, EditExtraActivity.class);
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
