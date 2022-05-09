package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;

import fr.sy43.studzero.Add_Payements;
import fr.sy43.studzero.History;
import fr.sy43.studzero.Home;
import fr.sy43.studzero.R;
import fr.sy43.studzero.Settings;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.stats;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Budget budget = new Budget(-1, new Date(), new Date(), 1700);
        db.addBudget(budget);
        //User user = db.getUser();
        //Log.i("DB", "id "+user.getIdUser()+" budget "+user.getCurrentBudget() + " Date "+user.getDateNextBudget());

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        startActivity(new Intent(getApplicationContext(), Home.class)); //lance la page d'acceuil
        //overridePendingTransition(0,0);


    }
}