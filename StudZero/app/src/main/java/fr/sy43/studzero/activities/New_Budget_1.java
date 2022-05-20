package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fr.sy43.studzero.R;


// reste a communiquer a la BD lors de l'appuis du boutton confirm la val[0]




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

        setupForKeyboardDismiss((View) findViewById(R.id.New_Budget_BG_View_P1), (Activity) this);//enlève le keyboard si on click ailleurs
    }

    public void setupForKeyboardDismiss(View view, final Activity activity) {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.TextInputIncomeExpenses);
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    if(TextInputIncomeExpenses.getText().length() == 0){//champ vide reset le text field
                        TextInputIncomeExpenses.setText(""); //permet le raise d'erreur
                        TextInputIncomeExpenses.clearFocus();
                        textInputLayoutUserName.clearFocus();
                    }
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupForKeyboardDismiss(innerView, activity);
            }
        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //permet le retour vers la page settings sur l'appuis de la back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //startActivity(new Intent(getApplicationContext(), Settings.class));
                // renvoye vers la page settings sur appuis de la back arrow
                Intent intent = new Intent(this, Settings.class);
                intent.putExtra("caller", "New_Budget_1"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}