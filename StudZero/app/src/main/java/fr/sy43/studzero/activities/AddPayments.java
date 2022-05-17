package fr.sy43.studzero.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.List;

import fr.sy43.studzero.New_Budget_4;
import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Payment;
import fr.sy43.studzero.vue.layout.ListPaymentLayout;

/**
 * Activity that shows the payments that have bee done for the current budget
 */
public class AddPayments extends AppCompatActivity {

    /**
     * onCreate is called when the activity is created.
     * This method lists the payments that have been done for the current budget.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payements);

        //set status bar name
        getSupportActionBar().setTitle("Payments");

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

        // Add a scrollView + layout that show the payments of the month
        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollViewPayments);
        ListPaymentLayout listPaymentLayout = new ListPaymentLayout(this);
        scrollView.addView(listPaymentLayout);
        // Get the payments of the current budget from the database
        DatabaseHelper db = new DatabaseHelper(this.getApplicationContext());
        List<Payment> payments = db.getAllPaymentsOfBudget(db.getCurrentBudget().getIdBudget());

        // Add the payments to the layout
        for(int i = 0; i < payments.size(); ++i) {
            listPaymentLayout.addPayment(payments.get(i));
        }
        db.closeDB();
    }

    /**
     * Sets the menu at the top of the screen
     * @param menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_payments, menu);
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(R.color.purple_200), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *  Calls the AddPayment activity when the + button is tapped at the top right the screen
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_payment:
                Intent intent = new Intent(getApplicationContext(), AddPayment.class);
                intent.putExtra("caller", "AddPayments");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}