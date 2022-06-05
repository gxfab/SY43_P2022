package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ScrollView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.vue.layout.PreviousBudgetStatsLayout;

/**
 * Activity used to display stats about a previous budget
 */
public class PreviousBudgetStats extends AppCompatActivity {
    public static final String KEY_DATES_BUDGET = "Dates budget";
    public static final String KEY_ID_BUDGET = "id budget";

    /**
     * onCreate is called when the activity is created.
     * This method adds a PreviousBudgetStatsLayout to the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve send data from parent activity
        int idBudget = getIntent().getIntExtra(PreviousBudgetStats.KEY_ID_BUDGET, -1);
        String datesBudget = getIntent().getStringExtra(PreviousBudgetStats.KEY_DATES_BUDGET);
        // If the data can't be retrieved, return to History activity
        if(idBudget == -1 || datesBudget == null) {
            finish();
            Intent intent = new Intent(this, History.class);
            startActivity(intent);
        }
        getSupportActionBar().setTitle(datesBudget);
        setContentView(R.layout.activity_previous_budget);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Add a layout to the scroll view that shows stats about a previous budget
        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollViewHistoryStats);
        PreviousBudgetStatsLayout previousBudgetStatsLayout = new PreviousBudgetStatsLayout(this, idBudget);
        scrollView.addView(previousBudgetStatsLayout);
    }

    /**
     * handle the top bar inputs : if the user press the return button, we go back to the previous screen
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                finish();
                Intent intent = new Intent(this, History.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}