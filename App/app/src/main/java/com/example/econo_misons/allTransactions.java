package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.views.TransactionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class allTransactions extends AppCompatActivity implements TransactionAdapter.Listener{

    TextView budgetName;
    RecyclerView recyclerView;

    private DBViewModel dbViewModel;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);

        // initializing the date, bottom menu bar and the widgets

        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        budgetName = findViewById(R.id.budget_name);
        recyclerView = findViewById(R.id.trans_list);

        this.getData();
        this.configureRecyclerView();

        budgetName.setText("Budget : "+ CurrentData.getBudget().budgetName);

        this.makeBottomBar();
    }

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // Create adapter passing the list of users
        this.adapter = new TransactionAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Function that puts the observers on the database
    private void getData(){
        this.dbViewModel.getAllCategories().observe(this, this::categoriesObserver);
        this.dbViewModel.getBudgetTransactions(CurrentData.getBudget().id).observe(this,this::transactionObserver);
    }

    // Function called when the categories are modified in the database
    private void categoriesObserver(List<Category> categories){
        adapter.getCategories(categories);
    }

    // Function called when the transactions are modified in the database
    private void transactionObserver(List<Transaction> transactionList){
        adapter.getAllTransactions(transactionList);
    }

    // Function that creates and initializes the bottom menu bar
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

    // Function called when modify is clicked on an item
    @Override
    public void onClickModifyButton(int position) {
        this.onButtonShowPopupWindowClick(findViewById(R.id.content).getRootView(), adapter.transactions.get(position));
    }

    // Function called when delete is clicked on an item
    @Override
    public void onClickDeleteButton(int position) {
        Transaction trans = adapter.getTransaction(position);
        Toast.makeText(this, "You deleted the transaction : " + trans.transactionName, Toast.LENGTH_SHORT).show();
        dbViewModel.deleteTransaction(trans);
    }

    //Pop up for modifying transactions
    public void onButtonShowPopupWindowClick(View view, Transaction trans) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_modify_trans, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // make buttons work
        Button valider = popupView.findViewById(R.id.valider);
        Button annuler = popupView.findViewById(R.id.annuler);
        Button ajoutCat = popupView.findViewById(R.id.ajout_cat);
        Button pickDate = popupView.findViewById(R.id.pick_date);
        EditText montant = popupView.findViewById(R.id.montant);
        EditText nom = popupView.findViewById(R.id.title);
        EditText date = popupView.findViewById(R.id.date);
        CheckBox check = popupView.findViewById(R.id.depense);

        montant.setText(Float.toString(trans.amountTransaction));
        nom.setText(trans.transactionName);
        check.setChecked(trans.expense);

        //working on spinner
        Spinner spinner = popupView.findViewById(R.id.categorie);
        ArrayList<String> categories = new ArrayList<>();
        for (Category cat : adapter.categories) {
            categories.add(cat.categoryName);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        final Category[] cat = {adapter.categories.get(0)};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat[0] = adapter.categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setSelection(categories.indexOf(trans.categoryID));

        // setting the date picker
        int lastSelectedYear = Integer.parseInt(trans.date.substring(6,10));
        int lastSelectedMonth = Integer.parseInt(trans.date.substring(3,5))-1;
        int lastSelectedDayOfMonth = Integer.parseInt(trans.date.substring(0,2));
        setDate(lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth, date);

        pickDate.setOnClickListener(v -> selectDate(lastSelectedYear,lastSelectedMonth,lastSelectedDayOfMonth,date));

        valider.setOnClickListener(v -> modifyTransaction(popupWindow,date,nom,montant,check,trans.id,cat[0].id));

        annuler.setOnClickListener(v -> popupWindow.dismiss());

        ajoutCat.setOnClickListener(v -> changeActivity());
    }

    // Function that changes the activity
    private void changeActivity() {
        Intent intent = new Intent(this, AjoutCategorie.class);
        startActivity(intent);
    }

    // Date management

    private void selectDate(int lastSelectedYear, int lastSelectedMonth, int lastSelectedDayOfMonth, EditText date){


        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {

            setDate(year, monthOfYear, dayOfMonth, date);

        };

        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    // Setting the date in an edit text
    private void setDate(int year, int monthOfYear, int dayOfMonth, EditText dateText){
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

    // Function that updates a transaction in the database
    private void modifyTransaction(PopupWindow popupWindow, EditText dateText, EditText title, EditText cout, CheckBox check, int id, int cat){
        if(!dateText.getText().toString().isEmpty() && !title.getText().toString().isEmpty() && !cout.getText().toString().isEmpty()) {
            String dateMonthYear = dateText.getText().toString().substring(6, 10) + "-" + dateText.getText().toString().substring(3, 5);
            if (dateMonthYear.equals(CurrentData.getPrevBudget().yearMonth)) {
                Transaction trans = new Transaction(id, CurrentData.getBudget().id, CurrentData.getPrevBudget().yearMonth, CurrentData.getUser().id, cat, title.getText().toString(), dateText.getText().toString(), Float.parseFloat(cout.getText().toString()), check.isChecked());
                this.dbViewModel.updateTransaction(trans);
                this.dbViewModel.getBudgetTransactions(CurrentData.getBudget().id).removeObserver(this::transactionObserver);
                this.dbViewModel.getBudgetTransactions(CurrentData.getBudget().id).observe(this,this::transactionObserver);
                popupWindow.dismiss();
            } else {
                Toast.makeText(this,"Pour modifier un achat au budget, il doit dater de ce mois.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}