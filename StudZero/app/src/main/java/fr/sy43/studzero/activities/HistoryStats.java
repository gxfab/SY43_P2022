package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ScrollView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.vue.layout.HistoryStatsLayout;
import fr.sy43.studzero.vue.layout.ListPaymentLayout;

public class HistoryStats extends AppCompatActivity {
    public static final String KEY_DATES_BUDGET = "Dates budget";
    public static final String KEY_ID_BUDGET = "id budget";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int idBudget = getIntent().getIntExtra(HistoryStats.KEY_ID_BUDGET, -1);
        String datesBudget = getIntent().getStringExtra(HistoryStats.KEY_DATES_BUDGET);
        if(idBudget == -1 || datesBudget == null) {
            finish();
            Intent intent = new Intent(this, History.class);
            startActivity(intent);
        }
        Log.i("Extra", datesBudget + " " + idBudget);
        getSupportActionBar().setTitle("History");
        setContentView(R.layout.activity_history_stats);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Add a layout to the scroll view that shows stats about a budget
        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollViewHistoryStats);
        HistoryStatsLayout historyStatsLayout = new HistoryStatsLayout(this, idBudget);
        scrollView.addView(historyStatsLayout);
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