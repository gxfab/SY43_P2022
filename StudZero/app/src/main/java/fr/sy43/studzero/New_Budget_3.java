package fr.sy43.studzero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fr.sy43.studzero.activities.Settings;


//faire envoie vers la BD qd on confirm
//modif le string allocated : $val et Available : $val
//setup la liste déroulante avec les noms des catégories
//link le button Review ALL et Add new category



public class New_Budget_3 extends AppCompatActivity {

    private Spinner spinner;
    final float[] val = {0.0f};
    String Cat_Selected;

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

        Button button = (Button) findViewById(R.id.ConfirmButtonExpenses);
        button.setEnabled(false);
        this.setupFloatingLabelError();

        Button buttonAddCategory = (Button) findViewById(R.id.NewBudgetAddCategoryButton);
        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //lance new budget 4
                finish();
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    //renvoye vers la page précédente
                    Intent intent = new Intent(getApplicationContext(), New_Budget_4.class);
                    intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                else{
                    //renvoye vers la page précédente
                    Intent intent = new Intent(getApplicationContext(), New_Budget_4.class);
                    intent.putExtra("caller", "MainActivity"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
            }
        });

        //click listener on background
        setupForKeyboardDismiss((View) findViewById(R.id.New_Budget_BG_View_P3), (Activity) this);//enlève le keyboard si on click ailleurs


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
                final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
                final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.TextInputIncomeExpenses);
                onItemSelectedHandler(parent, view, position, id);
                Cat_Selected = category[position];
                TextInputIncomeExpenses.setText("");
                textInputLayoutUserName.setErrorEnabled(false);
                TextInputIncomeExpenses.clearFocus();
                textInputLayoutUserName.clearFocus();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Cat_Selected = "";
            }
        });


    }

    public  void setupForKeyboardDismiss(View view, final Activity activity) {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.TextInputIncomeExpenses);

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    if(TextInputIncomeExpenses.getText().length() == 0){//champ vide reset le text field
                        textInputLayoutUserName.setErrorEnabled(false);
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






    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String category = (String) adapter.getItem(position);

        ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selected_white));
        ((TextView) adapterView.getChildAt(0)).setTextSize(16);

        Toast.makeText(getApplicationContext(), "Selected Category : " + category ,Toast.LENGTH_SHORT).show(); //print une notif
    }

    //permet le retour vers la page settings sur l'appuis de la back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    //renvoye vers la page précédente
                    Intent intent = new Intent(this, New_Budget_2.class);
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

    //message d'erreur du champ de texte et récupération de la valeur
    private void setupFloatingLabelError() {
        Button button = (Button) findViewById(R.id.ConfirmButtonExpenses);
        final TextInputLayout textInputLayoutExpenses = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Ajouter val[0] au budget contenant le montant estimé de la dépenses de la catégorie selected (Cat_Selected)

            }
        });

        textInputLayoutExpenses.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                textInputLayoutExpenses.setHint("focus");
            }
        });

        textInputLayoutExpenses.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                button.setEnabled(false);
                if (text.length() == 0 ) {
                    textInputLayoutExpenses.setError("required*");
                    textInputLayoutExpenses.setErrorEnabled(true);
                    button.setEnabled(false);
                } else if (text.length() > 0) {
                    String s = String.valueOf(text);

                    try
                    {
                        float f = Float.valueOf(s.trim()).floatValue();
                        textInputLayoutExpenses.setErrorEnabled(false);
                        button.setEnabled(true);
                        val[0] = f;
                    }
                    catch (NumberFormatException nfe) {
                        textInputLayoutExpenses.setError("Invalid number (expected : 123456789 . )");
                        textInputLayoutExpenses.setErrorEnabled(true);
                        button.setEnabled(false);
                    }
                } else {
                    textInputLayoutExpenses.setErrorEnabled(false);
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