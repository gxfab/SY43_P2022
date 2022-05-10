package fr.sy43.studzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import fr.sy43.studzero.activities.Settings;

public class New_Budget_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget1);

        getSupportActionBar().setTitle("Setup New Budget");
        //avoir la flèche retour dans le status bar que si on viens de l'écran param
        String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
        if(caller.equals("Settings")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        Button button = (Button) findViewById(R.id.NewBudgetIncomeConfirmButton);
        button.setEnabled(false);
        this.setupFloatingLabelError();
    }




    //permet le retour vers la page settings sur l'appuis de la back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                startActivity(new Intent(getApplicationContext(), Settings.class)); //renvoye vers la page settings sur appuis de la back arrow
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



    //message d'erreur du champ de texte et récupération de la valeur
    private void setupFloatingLabelError() {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        final float[] val = {0.0f};
        Button button = (Button) findViewById(R.id.NewBudgetIncomeConfirmButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Ajouter val[0] au budget contenant la valeur du revenu



                // a faire




                //changer d'écran : sauter la resélection des anciennes catégories si premier budget (parent != Settings)
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    Intent intent = new Intent(new Intent(getApplicationContext(), New_Budget_2.class));
                    intent.putExtra("caller", "Settings"); //ecran choix des anciennes catégories + option retour
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(new Intent(getApplicationContext(), New_Budget_3.class));
                    intent.putExtra("caller", "MainActivity"); //ecran creation catégorie + non retour
                    startActivity(intent);
                }
            }
        });

        textInputLayoutUserName.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                button.setEnabled(false);
                if (text.length() == 0 ) {
                    textInputLayoutUserName.setError("required*");
                    textInputLayoutUserName.setErrorEnabled(true);
                    button.setEnabled(false);
                } else if (text.length() > 0) {
                    String s = String.valueOf(text);

                    try
                    {
                        float f = Float.valueOf(s.trim()).floatValue();
                        textInputLayoutUserName.setErrorEnabled(false);
                        button.setEnabled(true);
                        val[0] = f;
                    }
                    catch (NumberFormatException nfe) {
                        textInputLayoutUserName.setError("Invalid number (expected : 123456789 . )");
                        textInputLayoutUserName.setErrorEnabled(true);
                        button.setEnabled(false);
                    }
                } else {
                    textInputLayoutUserName.setErrorEnabled(false);
                    button.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}