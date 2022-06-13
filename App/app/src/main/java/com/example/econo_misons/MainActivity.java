package com.example.econo_misons;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.econo_misons.database.DBViewModel;
import com.example.econo_misons.database.ViewModelFactory;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button add, view;
    private DBViewModel dbViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        add = findViewById(R.id.addbutton);
        view = findViewById(R.id.viewbutton);

        configureViewModel();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();
                newUser(usernameTXT);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUsers();
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
        this.dbViewModel.getCurrentBudgetEnvelope(prevBud).observe(this, this::showUserList);

    }

    private void newUser(String username) {
        dbViewModel.newUser(new User(username));
    }

    private void showUserList(List<Envelope> list){
        Toast toast = Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

}