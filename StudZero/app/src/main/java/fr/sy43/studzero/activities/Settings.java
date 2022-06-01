package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.sy43.studzero.R;
//CTRL+F "here" settext la date réel de reset de budget (date de fin du budget courant)
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);

        //set status bar name
        getSupportActionBar().setTitle("Settings");

        TextView txtV = findViewById(R.id.TextViewDate);
        txtV.setText("la date"); //here

        Button btn = (Button) findViewById(R.id.SettingsButtonReset);
        btn.setOnClickListener(new View.OnClickListener() {
            /**
             * Reset Button pressed -> launch the creation of a new budget
             * @param v
             */
            public void onClick(View v) {
                finish();
                //go new budget 1 with caller = settings
                Intent intent = new Intent(getApplicationContext(), New_Budget_1.class);
                intent.putExtra("caller", "Settings"); //permet à la nouvelle activity de connaitre son lanceur
                startActivity(intent);
            }
        });

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.settings);
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
                    case R.id.settings:
                        return true;
                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(), stats.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
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
    }
}