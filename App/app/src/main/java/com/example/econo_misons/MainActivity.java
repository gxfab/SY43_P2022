package com.example.econo_misons;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.CustomTreeDataEntry;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.TreemapEnv;
import com.example.econo_misons.database.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.TreeDataEntry;
import com.anychart.charts.TreeMap;
import com.anychart.core.ui.Title;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Orientation;
import com.anychart.enums.SelectionMode;
import com.anychart.enums.TreeFillingMethod;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button transVoir, depense, catVoir;
    TextView budgetName;
    private String st = new String();
    private DBViewModel dbViewModel;
    AnyChartView anyChartView;
    ProgressBar progessBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up the interface, the data and the bottom menu bar
        catVoir = findViewById(R.id.voir_cat);
        budgetName = findViewById(R.id.budget_name);
        transVoir = findViewById(R.id.voir_trans);
        depense = findViewById(R.id.ajout_dep);
        progessBar = findViewById(R.id.progressBar);
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(progessBar);
        anyChartView.setBackgroundColor("#208383");

        configureViewModel();
        CurrentData.init();
        this.dbViewModel.getAllPrevBudgets().observe(this,this::seeList);


        this.dbViewModel.getTreemapList().observe(this, this::updateTreeMap);

        catVoir.setOnClickListener(v -> changeActivity(CategoryTransactions.class));
        transVoir.setOnClickListener(v -> changeActivity(allTransactions.class));
        depense.setOnClickListener(v -> changeActivity(AjoutDepense.class));


        this.makeBottomBar();
    }

    // getting the list of the previsional budget
    private void seeList(List<PrevisionalBudget> list){
        String st = new String();

        for (PrevisionalBudget prev: list) {
            st += prev.toString();
        }
        this.st = st;
        
        budgetName.setText("Budget : "+CurrentData.getBudget().budgetName);
    }

    // Function that creates and initializes the bottom bar menu
    private void makeBottomBar(){
        //  Bottom Bar controller
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // Set selected
        bottomNavigationView.setSelectedItemId(R.id.MainMenu);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.ChangeBudget:
                        startActivity(new Intent(getApplicationContext(),ChangerBudget.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.MainMenu:
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

    // Function that configures the view model
    private void configureViewModel(){
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);
    }

    private void getAllUsers() {
        showUserList(CurrentData.getUser());
    }

    private void newUser(String username) {
        this.dbViewModel.newUser(new User(username));
    }

    // function that shows the user list on a toast
    private void showUserList(User list){
        Log.d("MA","toast");
        Toast toast = Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    // function that changes the current activity to the activity cl
    private void changeActivity(Class cl){
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }

    // Function that updates the treemap
    private void updateTreeMap(List<TreemapEnv> envList){
        dbViewModel.getTreemap(envList).observe(this,this::drawTreemap);

    }
    // Function that draws the treemap
    private void drawTreemap(TreeMap treeMap){

        anyChartView.setChart(treeMap);
        Toast.makeText(this, "finished", Toast.LENGTH_SHORT).show();
    }



}


