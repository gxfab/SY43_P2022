package fr.sy43.studzero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class New_Budget_3 extends AppCompatActivity {

    private Spinner spinner;



    //faire la gestion de l'input du cahmp de texte et les msg d'erreurs






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget3);

        getSupportActionBar().setTitle("Setup New Budget");
        //avoir la flèche retour dans le status bar que si on viens de l'écran param
        String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
        if(caller.equals("Settings")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        //test de la liste déroulante
        // https://devstory.net/12617/android-spinner

        this.spinner = (Spinner) findViewById(R.id.NewBudgetListeDeroulante);
        String[] category = {"cat1","cat2","cat3"}; //a modif avec les datas de la data base
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.liste_deroulante_selected_item, category);

        adapter.setDropDownViewResource(R.layout.liste_deroulante);

        this.spinner.setAdapter(adapter);

        // When user select a List-Item.
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String category = (String) adapter.getItem(position);

        ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selected_white));
        ((TextView) adapterView.getChildAt(0)).setTextSize(16);

        Toast.makeText(getApplicationContext(), "Selected Employee: " + category ,Toast.LENGTH_SHORT).show(); //print une notif
    }

    //permet le retour vers la page settings sur l'appuis de la back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    startActivity(new Intent(getApplicationContext(), New_Budget_2.class)); //renvoye vers la page précédente
                }
                else{
                    startActivity(new Intent(getApplicationContext(), New_Budget_3.class)); //renvoye vers la page précédente
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}