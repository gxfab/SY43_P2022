package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.econo_misons.database.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AjoutDepense extends AppCompatActivity {

    Button ajoutCat, date, valider, annuler;
    EditText dateText;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_depense);

        ajoutCat = findViewById(R.id.ajout_cat);
        date = findViewById(R.id.pick_date);
        valider = findViewById(R.id.valider);
        annuler = findViewById(R.id.annuler);
        dateText = findViewById(R.id.date);


        ajoutCat.setOnClickListener(v -> changeActivity());

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(v -> selectDate());

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add categorie to database
                finish();
            }
        });

        annuler.setOnClickListener(v -> finish());

        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.BudgetPrev);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.MainMenu:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.BudgetPrev:
                        startActivity(new Intent(getApplicationContext(),BudgetPrev.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ChangeBudget:
                        startActivity(new Intent(getApplicationContext(),ChangerBudget.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void changeActivity(){
        Intent intent = new Intent(this, AjoutCategorie.class);
        startActivity(intent);
    }

    private void selectDate(){
        final boolean isSpinnerMode = false;

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                dateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;

        if(isSpinnerMode)  {
            // Create DatePickerDialog:
            datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }
        // Calendar Mode (Default):
        else {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }
}