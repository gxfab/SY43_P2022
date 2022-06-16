package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AjoutDepense extends AppCompatActivity {

    Button ajoutCat, date, valider, annuler;
    EditText dateText, title, cout;
    Spinner spinner;

    private DBViewModel dbViewModel;

    private List<Category> categoryList;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_depense);

        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        ajoutCat = findViewById(R.id.ajout_cat);
        date = findViewById(R.id.pick_date);
        valider = findViewById(R.id.valider);
        annuler = findViewById(R.id.annuler);
        dateText = findViewById(R.id.date);
        title = findViewById(R.id.title);
        cout = findViewById(R.id.cout);
        spinner = findViewById(R.id.categorie);

        ajoutCat.setOnClickListener(v -> changeActivity());

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(v -> selectDate());

        valider.setOnClickListener(v -> changeActivity());

        annuler.setOnClickListener(v -> finish());

        this.getCategories();
        //Setup spinner
        ArrayList<String> categories = new ArrayList<>();
        for (Category cat : this.categoryList) {
            categories.add(cat.categoryName);
            Log.e("AD",cat.categoryName);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        final Category[] cat = {this.categoryList.get(0)};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat[0] = categoryList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        this.makeBottomBar();
    }

    private void getCategories(){
        this.dbViewModel.getAllCategories().observe(this, this::catObserver);
    }

    private void catObserver(List<Category> categoryList){
        Log.e("GC",this.categoryList.toString());
        this.categoryList = categoryList;
    }

    private void makeBottomBar(){
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelected(false);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

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
        });
    }

    private void changeActivity(){
        Intent intent = new Intent(this, AjoutCategorie.class);
        startActivity(intent);
    }

    private void selectDate(){

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {

            dateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

            lastSelectedYear = year;
            lastSelectedMonth = monthOfYear;
            lastSelectedDayOfMonth = dayOfMonth;
        };

        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }
}