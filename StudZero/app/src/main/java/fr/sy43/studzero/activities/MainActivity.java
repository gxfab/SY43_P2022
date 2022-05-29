package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.User;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * This is the fist activity that is called when the app is launched
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        User user = db.getUser();
        if(user.getCurrentBudget() > 0 && db.getRemainingDaysOfCurrentBudget() > 0) {
            db.closeDB();
            startActivity(new Intent(getApplicationContext(), Home.class));
        } else {
            db.closeDB();
            Intent intent = new Intent(this, New_Budget_1.class);
            intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
            startActivity(intent);
        }
        overridePendingTransition(0,0);
    }
}