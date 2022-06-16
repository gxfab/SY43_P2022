package com.example.agedor;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //getActionBar().setTitle("test");



        setContentView(R.layout.activity_main);
    }

    public void consulterMesDepenses(View view){
        Intent intent = new Intent(this, ConsulterDepensesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void nouvelleDepense(View view){
        Intent intent = new Intent(this, NouvelleDepenseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void editerLeBudget(View view){
        Intent intent = new Intent(this, EditerBudgetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void parametres(View view){

        Toast.makeText(this,"Implémentation à venir!",Toast.LENGTH_LONG).show();
        /*

        DBHandler db = new DBHandler(getApplicationContext());

        ArrayList<StorageDepenses> tab = db.getDepenses();

        for(int i = 0; i <=tab.size();i++) {
            System.out.println(tab.get(i).id_cat);

        }

        */

        //Intent intent = new Intent(this, SettingsActivity.class);
        //startActivity(intent);
    }







}