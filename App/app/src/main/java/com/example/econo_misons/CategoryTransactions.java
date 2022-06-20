package com.example.econo_misons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.utils.ItemClickSupport;

import com.example.econo_misons.views.CatTransactionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CategoryTransactions extends AppCompatActivity {

    RecyclerView recyclerView;
    private DBViewModel dbViewModel;
    TextView nameBudget;
    //for data
    private CatTransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_transactions);

        // initializing the view model
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);

        // initializing the widgets and getting the date
        nameBudget = findViewById(R.id.budget_name);
        recyclerView = findViewById(R.id.trans_list);

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();
        this.getData();

        nameBudget.setText("Budget : "+ CurrentData.getBudget().budgetName);

        // Making the bottom bar
        this.makeBottomBar();
    }

    // Function that puts an observer for the categories
    public void getData(){
        this.dbViewModel.getAllCategories().observe(this,this::catObserver);
    }

    // Function that calls the update function when it observes a modification of the categories
    public void catObserver(List<Category> categories){
        adapter.updateList(categories);
    }

    // Functions that changes the activity to the list of transactions of a category and giving the category
    public void transCategory(int category){
        Intent intent = new Intent(this, Categorie.class);
        Bundle b = new Bundle();
        b.putInt("key", category); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    // Function that links a change on activity when clicking on an item
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.item_category)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Category cat = adapter.getCat(position);
                    transCategory(cat.id);
                });
    }

    // Function that creates and initializes the bottom bar
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
        this.adapter = new CatTransactionAdapter();
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}