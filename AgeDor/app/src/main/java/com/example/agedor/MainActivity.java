package com.example.agedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agedor.view.enveloppes.depenses.ConsulterDepensesActivity;
import com.example.agedor.view.EditerBudgetActivity;
import com.example.agedor.view.enveloppes.depenses.NouvelleDepenseActivity;

import com.example.agedor.view.introduction.Fragment1;
import com.example.agedor.view.introduction.IntroActivity;
import com.example.agedor.CalculateurBudget;

// Page d'accueil de l'application
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static Context context;
    public static String nom;
    TextView textKO;
    TextView textOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        String prenom = Fragment1.readTextFile("nom.txt");
        if (prenom.equals("")) {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        } else {
            MainActivity.nom = prenom;
            setContentView(R.layout.activity_main);
            showStatus();

        }
    }

    public void showStatus() {
        textKO = (TextView) findViewById(R.id.phraseStatus);
        textOK = (TextView) findViewById(R.id.phraseOK);
        String phrase = CalculateurBudget.calculerBudget();
        if (phrase.startsWith("Vous êtes")) {
            Log.i("MainActivity", "phrase : " + phrase);
            textKO.setText("Bonjour " + nom + ",\n" + phrase);
        } else {
            Log.i("MainActivity", "phrase : " + phrase);
            textOK.setText("Bonjour " + nom + ",\n" + phrase);
        }
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