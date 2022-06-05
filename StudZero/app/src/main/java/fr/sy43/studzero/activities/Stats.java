package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.CategoryType;
import fr.sy43.studzero.vue.view.CategoryGraph;

/**
 * Activity that show the stats of the current budget
 */
public class Stats extends AppCompatActivity {

    /**
     * Corresponds to the category type chosen  with the spinner
     */
    private String selectedString;

    /**
     * onCreate is called when the activity is created.
     * This method displays the view of the stats
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.history);

        //set status bar name
        getSupportActionBar().setTitle("Statistics");

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.stats);

        Spinner spinner = (Spinner) findViewById(R.id.StatsSpinner);
        // Retrieve the category types of the current budget
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        List<CategoryType> categoryTypes = db.getAllCategoriesTypesOfBudget(db.getCurrentBudget().getIdBudget());
        String[] categoryNames = new String[categoryTypes.size()];
        for(int i = 0; i < categoryTypes.size(); ++i) {
            categoryNames[i] = categoryTypes.get(i).getNameCategory();
        }
        selectedString = categoryNames[0];

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.liste_deroulante_selected_item, categoryNames);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /**
                 * This function is used by the nav bar the bottom of the screen.
                 * If calls an activity depending on the item selected by the user.
                 * @param item
                 * @return
                 */
                switch(item.getItemId())
                {
                    case R.id.add_payements:
                        startActivity(new Intent(getApplicationContext(), AddPayments.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.stats:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), History.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        // Add a category graph to the view
        CategoryGraph categoryGraph = new CategoryGraph(getApplicationContext(), db.getCategoryOfCategoryType(db.getCurrentBudget().getIdBudget(), selectedString));
        categoryGraph.setBackgroundColor(getColor(R.color.BG_Objet));
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayoutStats);
        layout.addView(categoryGraph);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) categoryGraph.getLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = convertDpToPx(getApplicationContext(), 400);
        params.leftMargin = convertDpToPx(getApplicationContext(), 20);
        params.rightMargin = convertDpToPx(getApplicationContext(), 20);
        params.bottomMargin = convertDpToPx(getApplicationContext(), 30);
        categoryGraph.setLayoutParams(params);

        adapter.setDropDownViewResource(R.layout.liste_deroulante);

        spinner.setAdapter(adapter);

        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Update the selected type of category and show the category of the selected category
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                selectedString = categoryNames[position];
                categoryGraph.setNewCategory(db.getCategoryOfCategoryType(db.getCurrentBudget().getIdBudget(), selectedString));
                db.closeDB();
            }
            /**
             * If no category is selected : set the selected type of category variable to ""
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedString = "";
            }
        });

        db.closeDB();
    }

    /**
     * Converts dp to pixels
     * @param context
     * @param dp
     * @return pixels
     */
    private int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Converts sp to pixels
     * @param context
     * @param sp
     * @return pixels
     */
    private int convertSpToPx(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}