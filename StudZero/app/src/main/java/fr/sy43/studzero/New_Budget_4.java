package fr.sy43.studzero;

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

public class New_Budget_4 extends AppCompatActivity {
    String category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget4);

        getSupportActionBar().setTitle("Create New Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //avoir la flèche retour dans le status bar que si on viens de l'écran param

        Button button = (Button) findViewById(R.id.NewBudget4CreateButton);
        button.setEnabled(false);
        this.setupFloatingLabelError();

        setupForKeyboardDismiss((View) findViewById(R.id.New_Budget_bg_view_4), (Activity) this);//enlève le keyboard si on click ailleurs
    }

    //back arrow status bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    //renvoye vers la page précédente
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                else{
                    //renvoye vers la page précédente
                    Intent intent = new Intent(this, New_Budget_3.class);
                    intent.putExtra("caller", "MainActivity"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }




    //view listener
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




    //input text
    //message d'erreur du champ de texte et récupération de la valeur
    private void setupFloatingLabelError() {
        final TextInputLayout textInputLayoutCategoryName = (TextInputLayout) findViewById(R.id.TextInputLayoutCategory);
        Button button = (Button) findViewById(R.id.NewBudget4CreateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //créer les catégory avec de nom String category_name

                // a faire




                //changer d'écran : revenir a l'étape 3
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    Intent intent = new Intent(new Intent(getApplicationContext(), New_Budget_3.class));
                    intent.putExtra("caller", "Settings");
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(new Intent(getApplicationContext(), New_Budget_3.class));
                    intent.putExtra("caller", "MainActivity");
                    startActivity(intent);
                }
            }
        });

        textInputLayoutCategoryName.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                button.setEnabled(false);
                if (text.length() == 0 ) {
                    textInputLayoutCategoryName.setError("required*");
                    textInputLayoutCategoryName.setErrorEnabled(true);
                    button.setEnabled(false);
                } else if (text.length() > 0) {
                    String s = String.valueOf(text);
                    
                    category_name = s;
                    textInputLayoutCategoryName.setErrorEnabled(false);
                    button.setEnabled(true);
                } else {
                    textInputLayoutCategoryName.setErrorEnabled(false);
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

    @Override
    public void onBackPressed() {
    }
}