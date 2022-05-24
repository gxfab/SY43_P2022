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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fr.sy43.studzero.R;

//comparaison avec les categories déjà existantes : ctrl + F : "compare cat here"
//créer la catégory dans la BD sur appuis du button : ctrl + F : "appuis button"
//changer le string NewBudget4_NbCatTextViewTXT avec la vrai valeur de $val au OnCreate ctrl + F : "here"

/**
 * 4th screen of a new budget creation : add a new category
 */
public class New_Budget_4 extends AppCompatActivity {
    String category_name;

    /**
     * setup the text field with the correct values
     * setup the top bar
     * setup the keyboarddismiss
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget4);

        // here
        int nb_cat = 13; //db.nbcat


        TextView txtV = (TextView) (findViewById(R.id.NewBudget4_NbCatTextView));
        txtV.setText(getString(R.string.NewBudget4_NbCatTextViewTXT) + " " + String.valueOf(nb_cat));

        getSupportActionBar().setTitle("Create New Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //avoir la flèche retour dans le status bar que si on viens de l'écran param

        Button button = (Button) findViewById(R.id.NewBudget4CreateButton);
        button.setEnabled(true);
        this.setupFloatingLabelError();

        setupForKeyboardDismiss((View) findViewById(R.id.New_Budget_bg_view_4), (Activity) this);//enlève le keyboard si on click ailleurs
    }

    //back arrow status bar

    /**
     * return to the previous screen of the return arrow of the top bar is pressed
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

    /**
     * return true if the top bar is created successfully
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }




    //view listener

    /**
     * setup listeners on the view to hide the keyboard when the user click elsewhere
     * @param view
     * @param activity
     */
    public void setupForKeyboardDismiss(View view, final Activity activity) {
        final TextInputLayout TextInputLayoutCategory = (TextInputLayout) findViewById(R.id.TextInputLayoutCategory);
        final TextInputEditText TextInputCategoryName = (TextInputEditText) findViewById(R.id.TextInputCategoryName);
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
                    if(TextInputCategoryName.getText().length() == 0){//champ vide reset le text field
                        TextInputCategoryName.clearFocus();
                        TextInputLayoutCategory.clearFocus();
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




    //input text
    //message d'erreur du champ de texte et récupération de la valeur

    /**
     * Setup the error handler for the input text field
     * Setup the listener for the create button
     */
    private void setupFloatingLabelError() {
        final TextInputLayout textInputLayoutCategoryName = (TextInputLayout) findViewById(R.id.TextInputLayoutCategory);
        final TextInputEditText TextInputCategoryName = (TextInputEditText) findViewById(R.id.TextInputCategoryName);
        Button button = (Button) findViewById(R.id.NewBudget4CreateButton);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * raise an error if the text is incorrect
             * otherwise, and the new category to the DB and launch the new budget 3 screen (setup allocated amount for each category)
             * @param v
             */
            public void onClick(View v) {
                if(TextInputCategoryName.getText().length() == 0){
                    textInputLayoutCategoryName.setError("required*");
                    textInputLayoutCategoryName.setErrorEnabled(true);
                    return;
                }


                //créer les catégory avec de nom String category_name

                // a faire
                // appuis button



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
            /**
             * manage the error label of the input text field
             * @param text
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                button.setEnabled(true);
                if (text.length() > 0) {
                    String s = String.valueOf(text);

                    // compare cat here
                    //comparer le string s au nom de categories deja existante : modif la suite

                    String[] cat_existante = {"cat1", "cat2", "cat3"};
                    for(int i = 0; i < cat_existante.length; i++){
                        if(s.equals(cat_existante[i])){
                            textInputLayoutCategoryName.setError("This category already exists");
                            textInputLayoutCategoryName.setErrorEnabled(true);
                            button.setEnabled(false);
                            return;
                        }
                    }

                    category_name = s;
                    textInputLayoutCategoryName.setErrorEnabled(false);
                    button.setEnabled(true);
                } else {
                    textInputLayoutCategoryName.setErrorEnabled(false);
                    button.setEnabled(true);
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
             * do noting (auto generated)
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