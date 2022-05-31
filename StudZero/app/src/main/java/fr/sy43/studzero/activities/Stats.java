package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.CategoryType;

public class Stats extends AppCompatActivity {

    /**
     * Corresponds to the category type chosen  with the spinner
     */
    private String selectedString;

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
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        List<CategoryType> categoryTypes = db.getAllCategoriesTypes();
        db.closeDB();
        String[] categoryNames = new String[categoryTypes.size()];
        for(int i = 0; i < categoryTypes.size(); ++i) {
            categoryNames[i] = categoryTypes.get(i).getNameCategory();
        }

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
        adapter.setDropDownViewResource(R.layout.liste_deroulante);

        spinner.setAdapter(adapter);

        // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Update the selected type of category and reset the text input of the layout
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedString = categoryNames[position];
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
    }
}