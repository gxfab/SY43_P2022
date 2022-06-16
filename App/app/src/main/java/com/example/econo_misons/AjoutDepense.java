package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.views.DepenseAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AjoutDepense extends AppCompatActivity {

    Button ajoutCat, date, valider, annuler;
    EditText dateText, title, cout;
    Spinner spinner;
    CheckBox check;

    private DBViewModel dbViewModel;

    private DepenseAdapter adapter;
    private Category cat;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_depense);

        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);
        this.adapter = new DepenseAdapter();
        this.getCategories();

        ajoutCat = findViewById(R.id.ajout_cat);
        date = findViewById(R.id.pick_date);
        valider = findViewById(R.id.valider);
        annuler = findViewById(R.id.annuler);
        dateText = findViewById(R.id.date);
        title = findViewById(R.id.title);
        cout = findViewById(R.id.cout);
        spinner = findViewById(R.id.categorie);
        check = findViewById(R.id.depense);

        ajoutCat.setOnClickListener(v -> changeActivity());

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        setDate(lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        date.setOnClickListener(v -> selectDate());

        valider.setOnClickListener(v -> addToBase());

        annuler.setOnClickListener(v -> finish());

        this.makeBottomBar();
    }

    private void addToBase(){
        if(!dateText.getText().toString().isEmpty() && !title.getText().toString().isEmpty() && !cout.getText().toString().isEmpty()) {
            String dateMonthYear = dateText.getText().toString().substring(6, 10) + "-" + dateText.getText().toString().substring(3, 5);
            if (dateMonthYear.equals(CurrentData.getPrevBudget().yearMonth)) {
                Transaction trans = new Transaction(CurrentData.getBudget().id, CurrentData.getPrevBudget().yearMonth, CurrentData.getUser().id, cat.id, title.getText().toString(), dateText.getText().toString(), Float.parseFloat(cout.getText().toString()), check.isChecked());
                this.dbViewModel.addTransaction(trans);
                finish();
            } else {
                Toast.makeText(this,"Pour ajouter un achat au budget, il doit dater de ce mois.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSpinner(){
        //Setup spinner
        ArrayList<String> categories = new ArrayList<>();
        for (Category cat : adapter.categories) {
            categories.add(cat.categoryName);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        this.cat = adapter.categories.get(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = adapter.categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getCategories(){
        this.dbViewModel.getAllCategories().observe(this, this::catObserver);
    }

    private void catObserver(List<Category> categoryList){
        this.adapter.updateCategories(categoryList);
        setupSpinner();
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

            setDate(year, monthOfYear, dayOfMonth);

            lastSelectedYear = year;
            lastSelectedMonth = monthOfYear;
            lastSelectedDayOfMonth = dayOfMonth;
        };

        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    private void setDate(int year, int monthOfYear, int dayOfMonth){
        String day,month;
        if (dayOfMonth < 10){
            day = "0"+dayOfMonth;
        } else {
            day = Integer.toString(dayOfMonth);
        }
        if (monthOfYear+1 < 10){
            month = "0"+(monthOfYear+1);
        } else {
            month = Integer.toString(monthOfYear+1);
        }
        dateText.setText(day + "-" + month + "-" + year);
    }
}