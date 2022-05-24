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
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.vue.layout.HomeLayout;

/**
 * Activity that is used for the home screen of the app
 */
public class Home extends AppCompatActivity {

    /**
     * onCreate is called when the activity is created.
     * This method displays the view for the home page
     * @param savedInstanceState
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.add_payements:
                        startActivity(new Intent(getApplicationContext(), AddPayments.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(), Stats.class));
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

        // Add a scrollView + layout that shows information about the current budget
        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollViewHome);
        HomeLayout homeLayout = new HomeLayout(this);
        DatabaseHelper db = new DatabaseHelper(this);
        List<Category> categories = db.getAllCategories(db.getCurrentBudget().getIdBudget());
        db.closeDB();

        // Add the categories to the layout
        for(int i = 0; i < categories.size(); ++i) {
            homeLayout.addCategory(categories.get(i));
        }
        // Add layout to scrollView
        scrollView.addView(homeLayout);
    }
}