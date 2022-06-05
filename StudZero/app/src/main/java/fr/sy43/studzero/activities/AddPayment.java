package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.CategoryType;
import fr.sy43.studzero.sqlite.model.Payment;

/**
 * Activity that is used to create a new payment
 */
public class AddPayment extends AppCompatActivity {
    /**
     * Corresponds to the category type chosen with the spinner
     */
    private String selectedString;

    /**
     * Amount payed
     */
    private float paymentAmount = 0.0f;

    /**
     * onCreate is called when the activity is created.
     * This method displays the view to add a new payment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        //set status bar name
        getSupportActionBar().setTitle("Add Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.AddPaymentSpinner);
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        List<CategoryType> categoryTypes = db.getAllCategoriesTypesOfBudget(db.getCurrentBudget().getIdBudget());
        db.closeDB();
        String[] categoryNames = new String[categoryTypes.size()];
        for(int i = 0; i < categoryTypes.size(); ++i) {
            categoryNames[i] = categoryTypes.get(i).getNameCategory();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.liste_deroulante_selected_item, categoryNames);

        adapter.setDropDownViewResource(R.layout.liste_deroulante);

        spinner.setAdapter(adapter);

        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Update the selected type of category and reset the text input of the layout
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.AddPaymentTextInputLayout);
                final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.AddPaymentTextInputEditText);
                selectedString = categoryNames[position];
                TextInputIncomeExpenses.setText("");
                textInputLayoutUserName.setErrorEnabled(false);
                TextInputIncomeExpenses.clearFocus();
                textInputLayoutUserName.clearFocus();

            }
            /**
             * If no category is selected : set the selected type of category variable to ""
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedString = "";
            }
        });

        setupForKeyboardDismiss((View) findViewById(R.id.AddPaymentBGView), (Activity) this);//enlève le keyboard si on click ailleurs
        this.setupFloatingLabelError();
    }

    /**
     * Dismisses keyboard when user taps outside the textbox
     * @param view
     * @param activity
     */
    public  void setupForKeyboardDismiss(View view, final Activity activity) {
        final TextInputLayout textInputLayoutUserName = (TextInputLayout) findViewById(R.id.AddPaymentTextInputLayout);
        final TextInputEditText TextInputIncomeExpenses = (TextInputEditText) findViewById(R.id.AddPaymentTextInputEditText);

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
     * Shows error and sets up the Confirm button of the view
     */
    private void setupFloatingLabelError() {
        Button button = (Button) findViewById(R.id.AddPaymentConfirmButton);
        final TextInputLayout textInputLayoutExpenses = (TextInputLayout) findViewById(R.id.AddPaymentTextInputLayout);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Confirm button clicked :
             * set the value of the text field to the DB for the category Cat_Selected
             * compute the new allocated amount and update the display of this value
             * @param v
             */
            @SuppressLint("DefaultLocale")
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Spinner spinner = (Spinner) findViewById(R.id.AddPaymentSpinner);
                db.addPayment(new Payment(Float.parseFloat(String.format("%.2f", paymentAmount).replace(",", ".")), new Date(), db.getCurrentCategoryOfCategoryType(spinner.getSelectedItem().toString()).getIdCategory()));
                db.closeDB();
                Intent intent = new Intent(getApplicationContext(), AddPayments.class);
                intent.putExtra("caller", "AddPayment");
                startActivity(intent);
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
                        paymentAmount = f;
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
     * Returns to list of payments activity if the back arrow of the support action bar is tapped
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}