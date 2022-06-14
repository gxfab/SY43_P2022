package com.example.agedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.agedor.data.StorageCategories;
import com.example.agedor.data.StorageDepenses;
import com.example.agedor.view.ConsulterDepensesActivity;
import com.example.agedor.view.EditerBudgetActivity;
import com.example.agedor.view.NouvelleDepenseActivity;
import com.example.agedor.view.SettingsActivity;

import com.example.agedor.data.DBHandler;

import java.util.ArrayList;


// Page d'accueil de l'application
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consulterMesDepenses(View view){
        Intent intent = new Intent(this, ConsulterDepensesActivity.class);
        startActivity(intent);
    }

    public void nouvelleDepense(View view){
        Intent intent = new Intent(this, NouvelleDepenseActivity.class);
        startActivity(intent);
    }

    public void editerLeBudget(View view){
        Intent intent = new Intent(this, EditerBudgetActivity.class);
        startActivity(intent);
    }

    public void parametres(View view){

        DBHandler db = new DBHandler(getApplicationContext());

        ArrayList<StorageDepenses> tab = db.getDepenses();

        for(int i = 0; i <=tab.size();i++) {
            System.out.println(tab.get(i).id_cat);

        }



        //Intent intent = new Intent(this, SettingsActivity.class);
        //startActivity(intent);
    }







}