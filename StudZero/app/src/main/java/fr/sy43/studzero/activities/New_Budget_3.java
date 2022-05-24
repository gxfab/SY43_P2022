package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import fr.sy43.studzero.R;


//faire envoie vers la BD qd on confirm // CTRL+F : "update DB"
//setup la liste déroulante avec les noms des catégories de la DB
//affecter allocated et available avec les var de la DB : CTRL + F : "here"
//recalcul de allocated lors d'un confirm : CTRL + F : "recalcul"

//Liée la base de donnée pour récupérer la valeur associé a une catégorie à partir de sa position si elle existe CTRL+F " la" et "pour test"

/**
 * 3rd screen of the budget's creation
 * display the allocated amount and the available amount
 * allows to setup the estimated expenses of a category
 * provide a button to add a new category
 * provide a button to go to the review all screen
 */
public class New_Budget_3 extends AppCompatActivity {

    private Spinner spinner;
    private final float[] val = {0.0f};
    private String Cat_Selected;
    private int allocated;
    private int available;
    private float idNewBudget;



    private int GetAllocated(int pos){ //pour test
        if(pos != 0){
            return 1000;
        }
        else{
            return 0;
        }
    }

    /**
     * get the budget value from the DB and display it
     * get the allocated value and display it
     * setup the top bar
     * setup listeners for the buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget3);

        idNewBudget = getIntent().getLongExtra(New_Budget_1.ID_NEW_BUDGET, -1);
        if(idNewBudget == -1) {
            Intent intent = new Intent(this, New_Budget_1.class);
            intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
            intent.putExtra(New_Budget_1.ID_NEW_BUDGET, idNewBudget);
            startActivity(intent);
        }


        // here
        allocated = 1000; //DB.budget.budgetamount

        available = 1200; // somme : DB.category.theoricalamount

        //apply
        TextView txtAllocated = (TextView) findViewById(R.id.TextViewAllocated);
        TextView txtAvailable = (TextView) findViewById(R.id.TextViewAvailable);

        txtAllocated.setText(getString(R.string.NewBudgetTextViewAllocated) + " " + String.valueOf(allocated) + getString(R.string.currency));
        txtAvailable.setText(getString(R.string.NewBudgetTextViewAvailable) + " " + String.valueOf(available) + getString(R.string.currency));
        if(allocated != available){
            txtAllocated.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            txtAllocated.setTextColor(getResources().getColor(R.color.green));
        }


        getSupportActionBar().setTitle("Setup New Budget");
        //avoir la flèche retour dans le status bar que si on viens de l'écran param
        String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
        if(caller.equals("Settings")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        Button button = (Button) findViewById(R.id.ConfirmButton);
        button.setEnabled(false);
        this.setupFloatingLabelError();

        Button buttonAddCategory = (Button) findViewById(R.id.NewBudgetAddCategoryButton);
        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            /**
             * Add category button pressed : go to the add category screen
             * @param v
             */
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

        Button ReviewButton = (Button) findViewById(R.id.NewBudgetIncomeReviewAllButton);
        ReviewButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Review all Button pressed : go to the corresponding screen
             * @param v
             */
            public void onClick(View v) {
                //lance new budget 5
                finish();
                String caller     = getIntent().getStringExtra("caller"); //caller contient le nom de la class appelante
                if(caller.equals("Settings")){
                    //renvoye vers la page précédente
                    Intent intent = new Intent(getApplicationContext(), New_Budget_5.class);
                    intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                    startActivity(intent);
                }
                else{
                    //renvoye vers la page précédente
                    Intent intent = new Intent(getApplicationContext(), New_Budget_5.class);
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
            /**
             * Update the Cat_Selected variable with the one selected
             * Enter the allocated amount of this category in the text field if not null
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
                final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.TextInputIncomeExpenses);
                onItemSelectedHandler(parent, view, position, id);
                Cat_Selected = category[position];
                // la
                // si une somme est deja allouée a cette catégorie, la mettre par defaut dans le champ texte
                if(GetAllocated(position) != 0){
                    TextInputIncomeExpenses.setText(String.valueOf(GetAllocated(position)));
                }
                else {
                    TextInputIncomeExpenses.setText("");
                }
                textInputLayoutUserName.setErrorEnabled(false);
                TextInputIncomeExpenses.clearFocus();
                textInputLayoutUserName.clearFocus();

            }

            /**
             * If no category is selected : set the Cat_Selected variable to ""
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Cat_Selected = "";
            }
        });

        if(getIntent().getStringExtra("subcaller") != null){ //on vient de New_Budget_5 par click sur une catégorie
            int pos = Integer.parseInt(getIntent().getStringExtra("pos"));
            Log.i("pos : ", String.valueOf(pos));
            this.spinner.setSelection(pos);
        }


    }

    /**
     * setup listeners on the view to hide the keyboard when the user click elsewhere
     * @param view
     * @param activity
     */
    public  void setupForKeyboardDismiss(View view, final Activity activity) {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.TextInputIncomeExpenses);

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {
                /**
                 * On touch, hide keyboard and raise text label error if needed
                 * @param v
                 * @param event
                 * @return
                 */
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

    /**
     * hide the keyboard
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * display a message ton confirm the category selected
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String category = (String) adapter.getItem(position);

        ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selected_white));
        ((TextView) adapterView.getChildAt(0)).setTextSize(16);

        Toast.makeText(getApplicationContext(), "Selected Category : " + category ,Toast.LENGTH_SHORT).show(); //print une notif
    }

    //permet le retour vers la page settings sur l'appuis de la back arrow

    /**
     * go back to the previous page when the return button of the top bar is pressed
     * @param item
     * @return
     */
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

    /**
     * Setup the error handler for the input text field
     * Setup the listener for the confirm button
     */
    private void setupFloatingLabelError() {
        Button button = (Button) findViewById(R.id.ConfirmButton);
        final TextInputLayout textInputLayoutExpenses = (TextInputLayout) findViewById(R.id.TextInputLayoutExpenses);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Confirm button clicked :
             * set the value of the text field to the DB for the category Cat_Selected
             * compute the new allocated amount and update the display of this value
             * @param v
             */
            public void onClick(View v) {

                // update DB
                //Ajouter val[0] au budget contenant le montant estimé de la dépenses de la catégorie selected (Cat_Selected)

                // recalcul
                allocated = 1200; // refaire la somme des category.theoricalamount

                TextView txtAllocated = (TextView) findViewById(R.id.TextViewAllocated);
                TextView txtAvailable = (TextView) findViewById(R.id.TextViewAvailable);
                txtAllocated.setText(getString(R.string.NewBudgetTextViewAllocated) + " " + String.valueOf(allocated) + getString(R.string.currency));
                txtAvailable.setText(getString(R.string.NewBudgetTextViewAvailable) + " " + String.valueOf(available) + getString(R.string.currency));
                if(allocated != available){
                    txtAllocated.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    txtAllocated.setTextColor(getResources().getColor(R.color.green));
                }
            }
        });

        textInputLayoutExpenses.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /**
             * disable the focus of the input text field
             * @param view
             * @param b
             */
            @Override
            public void onFocusChange(View view, boolean b) {
                textInputLayoutExpenses.setHint("focus");
            }
        });

        textInputLayoutExpenses.getEditText().addTextChangedListener(new TextWatcher() {
            // ...

            /**
             * manage the error label of the input text field
             * @param text
             * @param start
             * @param count
             * @param after
             */
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

            /**
             * do nothing (auto generated)
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            /**
             * do nothing (auto generated)
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * override the onBackPressed function to "disable" it
     */
    @Override
    public void onBackPressed() {
    }
}