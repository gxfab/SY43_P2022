package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.views.BudgetPrevAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BudgetPrev extends AppCompatActivity implements BudgetPrevAdapter.Listener {

    Button valider, ajoutCat;
    TextView name;
    RecyclerView recyclerView;

    private DBViewModel dbViewModel;
    private BudgetPrevAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_prev);

        // initializing the view model
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        // finding and initializing all the widgets
        valider = findViewById(R.id.valider);
        ajoutCat = findViewById(R.id.ajout_cat);
        name = findViewById(R.id.budget_name);
        recyclerView = findViewById(R.id.envelope_list);

        name.setText(CurrentData.getBudget().budgetName + " - " + CurrentData.getPrevBudget().yearMonth);
        valider.setOnClickListener(v -> finish());
        ajoutCat.setOnClickListener(this::onButtonShowPopupWindowClick);

        // getting all the date needed
        this.getCurrentPrevBudgets();

        // making the bottom bar and preparing the recycle view
        this.configureRecyclerView();
        this.makeBottomBar();
    }

    //Pop up for add category
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_ajout_cat_prev, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Assigning widgets
        Button valider = popupView.findViewById(R.id.valider);
        Button annuler = popupView.findViewById(R.id.annuler);
        Button ajoutCat = popupView.findViewById(R.id.ajout_cat);
        EditText montant = popupView.findViewById(R.id.montant);

        //working on spinner
        Spinner spinner = popupView.findViewById(R.id.categorie);

        ArrayList<String> categories = new ArrayList<>();
        for (Category cat : adapter.categoryList) {
            categories.add(cat.categoryName);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        final Category[] cat = {adapter.categoryList.get(0)};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat[0] = adapter.categoryList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Setting the button listeners
        valider.setOnClickListener(v -> addCategory(popupWindow, montant.getText().toString(), cat[0]));

        annuler.setOnClickListener(v -> popupWindow.dismiss());

        ajoutCat.setOnClickListener(v -> changeActivity());
    }

    //Pop up for modify category
    public void onButtonShowPopupWindowClickModify(View view, Envelope envelope) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_modify_cat_prev, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // assigning widgets
        Button valider = popupView.findViewById(R.id.valider);
        Button annuler = popupView.findViewById(R.id.annuler);
        TextView categorie = popupView.findViewById(R.id.categorie);
        EditText montant = popupView.findViewById(R.id.montant);

        categorie.setText(adapter.categoryList.get(adapter.getIndexCategory(adapter.categoryList,envelope)).categoryName);
        montant.setText(Float.toString(envelope.sumEnv));

        // setting the buttons listeners
        valider.setOnClickListener(v -> modifyEnvelope(popupWindow, montant.getText().toString(), envelope));

        annuler.setOnClickListener(v -> popupWindow.dismiss());
    }

    // Function that changes the activity to the ajout categorie
    private void changeActivity() {
        Intent intent = new Intent(this, AjoutCategorie.class);
        startActivity(intent);
    }

    // Function to create and initialize the bottom bar
    private void makeBottomBar() {
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.BudgetPrev);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.MainMenu:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.ChangeBudget:
                    startActivity(new Intent(getApplicationContext(), ChangerBudget.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.BudgetPrev:
                    return true;
            }
            return false;
        });
    }

    // Function that creates calls the create modifying popup window for the corresponding envelope
    @Override
    public void onClickModifyButton(int position) {
        Log.e("BP","Clicked on "+position);
        this.onButtonShowPopupWindowClickModify(findViewById(R.id.content).getRootView(), adapter.envelopes.get(position));
    }

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // Create adapter passing the list of users
        this.adapter = new BudgetPrevAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Setting the necessary observers
    private void getCurrentPrevBudgets() {
        this.dbViewModel.getPrevBudgetEnvelopes(CurrentData.getPrevBudget()).observe(this, this::updateList);
        this.dbViewModel.getAllCategories().observe(this, this::updateCategoryList);
    }

    // Functions that update the envelope and category lists
    private void updateList(List<Envelope> envelopes) {
        this.adapter.updateData(envelopes);
    }

    private void updateCategoryList(List<Category> categories) {
        this.adapter.updateCategories(categories);
    }

    // Function that adds the category to the data base
    private void addCategory(PopupWindow popupWindow, @NonNull String value, Category cat) {

        // Verifying that the data is not already in the budget prev
        boolean exists = false;
        for (Envelope envelope : adapter.envelopes) {
            if (cat.id == envelope.categoryID) {
                exists = true;
                break;
            }
        }

        // adding the category if all the informations were given and it doesn't already exists
        Log.e("BP", Boolean.toString(value.isEmpty() && exists));
        if (!value.isEmpty() && !exists) {
            dbViewModel.addEnvelope(new Envelope(CurrentData.getPrevBudget(), cat.id, Float.parseFloat(value)));
            popupWindow.dismiss();
        } else if (value.isEmpty()) {
            Toast.makeText(this, "Vous devez entrer des valeurs !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cette categorie est deja dans le budget previsionnel !", Toast.LENGTH_SHORT).show();
        }
    }

    // Function that updates the envelope with the new informations
    private void modifyEnvelope(PopupWindow popupWindow, @NonNull String value, Envelope envelope) {
        dbViewModel.updateEnvelope(new Envelope(envelope.budgetID, envelope.dateEnv, envelope.categoryID, Float.parseFloat(value)));
        popupWindow.dismiss();
    }
}