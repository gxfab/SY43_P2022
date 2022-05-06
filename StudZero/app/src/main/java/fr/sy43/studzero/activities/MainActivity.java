package fr.sy43.studzero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Budget budget = new Budget(-1, new Date(), new Date(), 1700);
        db.addBudget(budget);
        User user = db.getUser();
        Log.i("DB", "id "+user.getIdUser()+" budget "+user.getCurrentBudget() + " Date "+user.getDateNextBudget());
    }
}