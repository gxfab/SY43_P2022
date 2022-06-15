package com.example.econo_misons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.econo_misons.database.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.econo_misons.database.models.Budget;

import java.util.List;

public class ChangerBudget extends AppCompatActivity {

    Button createBudget;
    private DBViewModel dbViewModel;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_budget);

        createBudget = findViewById(R.id.button);

        createBudget.setOnClickListener(v -> onButtonShowPopupWindowClick(v));

        this.dbViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(DBViewModel.class);
        //dbViewModel.setCurrentUser(2);



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

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameBudget.getText().toString();
                Log.d("CB", "currentUser: " + CurrentData.getUser());
                dbViewModel.addBudget(new Budget(name), CurrentData.getUser());
                popupWindow.dismiss();
            }
        });

        annuler.setOnClickListener(v -> popupWindow.dismiss());
    }

    private void updateCurrentUser(User user){
        this.currentUser = user;
    }
}