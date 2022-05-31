package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.Payment;
import fr.sy43.studzero.vue.layout.HistoryLayout;
import fr.sy43.studzero.vue.layout.ListPaymentLayout;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //set status bar name
        getSupportActionBar().setTitle("History");

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.history);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            /**
             * This function is used by the nav bar the bottom of the screen.
             * If calls an activity depending on the item selected by the user.
             * @param item
             * @return
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.add_payements:
                        startActivity(new Intent(getApplicationContext(), AddPayments.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        return true;
                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(), Stats.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Add a layout to the scroll view that shows the payments of the month
        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollViewHistory);
        HistoryLayout historyLayout = new HistoryLayout(this);
        scrollView.addView(historyLayout);
        // Get the payments of the current budget from the database
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        List<Budget> budgets = db.getAllBudgets();
        Budget currentBudget = db.getCurrentBudget();

        // Add the payments to the layout
        for(int i = 0; i < budgets.size(); ++i) {
            if(currentBudget.getIdBudget() != budgets.get(i).getIdBudget()) {
                historyLayout.addBudget(budgets.get(i));
            }
        }
        db.closeDB();
    }
}