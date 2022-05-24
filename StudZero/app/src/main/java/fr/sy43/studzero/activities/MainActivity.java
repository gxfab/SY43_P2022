package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;

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
        Budget budget = new Budget(new Date(), new Date(), 1700);
        db.addBudget(budget);
        //User user = db.getUser();
        //Log.i("DB", "id "+user.getIdUser()+" budget "+user.getCurrentBudget() + " Date "+user.getDateNextBudget());

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //lance la page d'acceuil
//        startActivity(new Intent(getApplicationContext(), Home.class));

        Intent intent = new Intent(this, New_Budget_1.class);
        intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
        startActivity(intent);

        overridePendingTransition(0,0);


    }
}