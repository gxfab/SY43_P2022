package com.example.agedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agedor.view.enveloppes.depenses.ConsulterDepensesActivity;
import com.example.agedor.view.EditerBudgetActivity;
import com.example.agedor.view.enveloppes.depenses.NouvelleDepenseActivity;


// Page d'accueil de l'application
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();



        //getActionBar().setTitle("test");



        setContentView(R.layout.activity_main);
    }

    public static Context getContext() {
        return MainActivity.context;
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

        Toast.makeText(this,"Implémentation à venir!",Toast.LENGTH_LONG).show();
    }
}