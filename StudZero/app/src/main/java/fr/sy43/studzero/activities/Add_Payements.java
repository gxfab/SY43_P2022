package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.model.Payment;
import fr.sy43.studzero.vue.layout.ListPaymentLayout;

public class Add_Payements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payements);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.history);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.add_payements);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_payements:
                        return true;
                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(), stats.class));
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

        RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.PaymentsParentLayout);
        ListPaymentLayout listPaymentLayout = new ListPaymentLayout(this);
        parentLayout.addView(listPaymentLayout);
        listPaymentLayout.addPayment(new Payment(1, 20, new Date(), 1));
        listPaymentLayout.addPayment(new Payment(1, 20, new Date(), 1));

    }
}