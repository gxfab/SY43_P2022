package com.example.agedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


// Page d'accueil de l'application
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Clic sur "Suivre mon budget"
    public void suivreMonBudget(View view){
        Intent intent = new Intent(this, SuivreBudgetActivity.class);
        startActivity(intent);
    }

    // Clic sur "Consulter mes dépenses"
    public void consulterMesDepenses(View view){
        Intent intent = new Intent(this, ConsulterDepensesActivity.class);
        startActivity(intent);
    }

    // Clic sur "Nouvelle dépense"
    public void nouvelleDepense(View view){
        Intent intent = new Intent(this, NouvelleDepenseActivity.class);
        startActivity(intent);
    }

    // Clic sur "Editer le budget"
    public void editerLeBudget(View view){
        Intent intent = new Intent(this, EditerBudgetActivity.class);
        startActivity(intent);
    }

    // Clic sur "Mes projets"
    public void mesProjets(View view){
        Intent intent = new Intent(this, MesProjetsActivity.class);
        startActivity(intent);
    }








}