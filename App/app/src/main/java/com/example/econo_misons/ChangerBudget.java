package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.utils.ItemClickSupport;
import com.example.econo_misons.views.BudgetAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.econo_misons.database.models.Budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChangerBudget extends AppCompatActivity implements BudgetAdapter.Listener{

    Button createBudget;
    RecyclerView recyclerView;
    private DBViewModel dbViewModel;

    //for data
    private BudgetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_budget);

        // initializing the interface, the viewmodel and the bottom bar
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        createBudget = findViewById(R.id.button);
        recyclerView = findViewById(R.id.budget_list);
        this.configureRecyclerView();
        this.getCurrentBudgets();
        this.searchBudgetPrev();
        this.configureOnClickRecyclerView();

        createBudget.setOnClickListener(this::onButtonShowPopupWindowClick);

        this.makeBottomBar();
    }

    //Pop up for Make Budget
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_make_budget, null);

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
        EditText nameBudget = popupView.findViewById(R.id.text);

        valider.setOnClickListener(v -> addBudgetToBase(nameBudget, popupWindow));

        annuler.setOnClickListener(v -> popupWindow.dismiss());
    }

    // Function that adds the budget to the database and closes the popup window
    private void addBudgetToBase(EditText nameBudget, PopupWindow popupWindow){
        String name = nameBudget.getText().toString();
        if (!name.isEmpty()) {
            dbViewModel.addBudget(new Budget(name), CurrentData.getUser());
            popupWindow.dismiss();
        } else {
            Toast.makeText(this,"Le budget doit avoir un nom !",Toast.LENGTH_SHORT).show();
        }
    }

    // Function that makes and initializes the bottom bar
    private void makeBottomBar(){
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.ChangeBudget);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.MainMenu:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.ChangeBudget:
                    return true;
                case R.id.BudgetPrev:
                    startActivity(new Intent(getApplicationContext(),BudgetPrev.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }

    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // Create adapter passing the list of users
        this.adapter = new BudgetAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Function that puts an observer to the budget list
    private void getCurrentBudgets(){
        this.dbViewModel.getAllBudgets().observe(this, this::updateList);
    }

    // Function that calls the update list when a modification in the budget list is made
    private void updateList(List<Budget> budgets){
        this.adapter.updateData(budgets);
    }

    // making buttons work
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.item_budget)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Budget budget = adapter.getBudget(position);
                    if (!(budget == CurrentData.getBudget())) {
                        setBudget(budget);
                        updatePrevBudget(budget);
                    }
                });
    }

    // Function that sets the current budget
    private void setBudget(Budget budget){
        CurrentData.setBudget(budget);
        showCurrentBudget(CurrentData.getBudget());
    }

    // Function that calls the update list when a modification in the budget prev list is made
    private void getAllBudgetPrev(List<PrevisionalBudget> previsionalBudgetList){
        this.adapter.updatePrevBudgets(previsionalBudgetList);
    }

    // Function that puts an observer to the budget prev list
    private void searchBudgetPrev(){
        this.dbViewModel.getAllPrevBudgets().observe(this, this::getAllBudgetPrev);
    }

    // Function that shows the current budget in a toast
    private void showCurrentBudget(Budget budget){
        Toast toast = Toast.makeText(this, budget.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }

    // Function called when clicked on the delete button of an item
    @Override
    public void onClickDeleteButton(int position) {
        Budget budget = adapter.getBudget(position);
        if (CurrentData.getBudget() == budget) {
            Toast.makeText(this, "You can't delete the current budget : " + budget.budgetName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You deleted the budget : " + budget.budgetName, Toast.LENGTH_SHORT).show();
            dbViewModel.deleteBudget(budget);
        }
    }

    // Function that updates the current previsional budget depending on the budget
    public void updatePrevBudget(Budget budget){

        // getting all the previsional budgets which correspond to our current budget
        int index = 0;
        List<PrevisionalBudget> toRemove = new ArrayList<>() ;
        for (PrevisionalBudget budgets : adapter.previsionalBudgets) {
            if (budgets.budgetID != budget.id){
                toRemove.add(adapter.previsionalBudgets.get(index));
            }
            index++;
        }
        adapter.previsionalBudgets.removeAll(toRemove);

        // searching the last previsionnal budget
        index = 0;
        int max = -1;
        int greatestYear = 0;
        int greatestMonth = 0;
        int month, year;
        for (PrevisionalBudget budgets : adapter.previsionalBudgets) {
            year = Integer.parseInt(budgets.yearMonth.substring(0,3));
            if (year > greatestYear){
                max = index;
                greatestYear = year;
            } else if (year == greatestYear) {
                month = Integer.parseInt(budgets.yearMonth.substring(5));
                if (month > greatestMonth){
                    greatestMonth = month;
                    max = index;
                }
            }
            index++;
        }

        // making a new previsionnal budget if none was found else taking the most recent one
        if (max == -1){
            String date = LocalDate.now().toString();
            PrevisionalBudget prev = new PrevisionalBudget(CurrentData.getBudget().id, date.substring(0,7));
            dbViewModel.addPrevBudget(prev);
            CurrentData.setPrevBudget(prev);
            this.dbViewModel.getAllPrevBudgets().removeObserver(this::getAllBudgetPrev);
            this.searchBudgetPrev();
        } else {
            CurrentData.setPrevBudget(adapter.previsionalBudgets.get(max));
        }
    }
}