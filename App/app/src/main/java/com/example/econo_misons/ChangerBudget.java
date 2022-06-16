package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.utils.ItemClickSupport;
import com.example.econo_misons.views.BudgetAdapter;
import com.example.econo_misons.views.BudgetViewHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.econo_misons.database.models.Budget;

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

        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        createBudget = findViewById(R.id.button);
        recyclerView = findViewById(R.id.budget_list);
        this.configureRecyclerView();
        this.getCurrentBudgets();
        this.configureOnClickRecyclerView();

        createBudget.setOnClickListener(this::onButtonShowPopupWindowClick);
        //dbViewModel.setCurrentUser(2);

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

    private void addBudgetToBase(EditText nameBudget, PopupWindow popupWindow){
        String name = nameBudget.getText().toString();
        if (!name.isEmpty()) {
            dbViewModel.addBudget(new Budget(name), CurrentData.getUser());
            popupWindow.dismiss();
        } else {
            Toast.makeText(this,"Le budget doit avoir un nom !",Toast.LENGTH_SHORT);
        }
    }

    private void makeBottomBar(){
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.ChangeBudget);
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
                    case R.id.ChangeBudget:
                        return true;
                    case R.id.BudgetPrev:
                        startActivity(new Intent(getApplicationContext(),BudgetPrev.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
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

    private void getCurrentBudgets(){
        this.dbViewModel.getAllBudgets().observe(this, this::updateList);
    }

    private void updateList(List<Budget> budgets){
        this.adapter.updateData(budgets);
    }

    // making buttons work
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.item_budget)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Budget budget = adapter.getBudget(position);
                        setBudget(budget);
                    }
                });
    }

    private void setBudget(Budget budget){
        CurrentData.setBudget(budget);
        showCurrentBudget(CurrentData.getBudget());
    }

    private void showCurrentBudget(Budget budget){
        Toast toast = Toast.makeText(this, budget.toString(), Toast.LENGTH_SHORT);
        toast.show();
    }

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
}