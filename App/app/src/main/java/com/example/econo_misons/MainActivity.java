package com.example.econo_misons;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button add, view, depense;
    private DBViewModel dbViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MA","debut");


        username = findViewById(R.id.username);
        add = findViewById(R.id.addbutton);
        view = findViewById(R.id.viewbutton);
        depense = findViewById(R.id.ajout_dep);

        configureViewModel();
        CurrentData.init();



        depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String usernameTXT = username.getText().toString();
                newUser(usernameTXT);*/
 /*               dbViewModel.setCurrentUser(1, MainActivity.this);
                dbViewModel.setCurrentBudget(1, MainActivity.this);
                dbViewModel.setCurrentPrevBudget(new PrevisionalBudget(1,"2022-06"));*/
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MA","view_click");
                getAllUsers();
                Log.d("MA","end_view_click");
            }

        });

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

    private void configureViewModel(){
        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);
    }

    private void getAllUsers() {
        PrevisionalBudget prevBud = new PrevisionalBudget(1,"2022-06");
        Log.d("MA","entered");
        if (this.dbViewModel.getCurrentPrevBudget() != null){
            this.dbViewModel.getCurrentPrevBudget().observe(this, this::showUserList);
        }

        Log.d("MA","exit");


    }

    private void newUser(String username) {
        this.dbViewModel.newUser(new User(username));
    }

    private void showUserList(PrevisionalBudget list){
        Log.d("MA","toast");
        Toast toast = Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    private void changeActivity(){
        Intent intent = new Intent(this, AjoutDepense.class);
        startActivity(intent);
    }


}