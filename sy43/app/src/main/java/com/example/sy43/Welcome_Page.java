package com.example.sy43;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Category;
import com.example.sy43.database.Income;
import com.example.sy43.database.MonthlyRevenue;

import java.util.Calendar;
import java.util.List;

public class Welcome_Page extends AppCompatActivity {

    Button get_started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        toDefineCategories();
        /*SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE );

        String firstTime = sharedPreferences.getString("First Time Install","");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(Welcome_Page.this, Define_categories.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("First Time Install", "Yes");
            editor.apply();
        }*/
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<Category> cat = db.categoryDao().getAll();
        List<Income> inc = db.incomeDao().getAll();

        if (cat.isEmpty() && inc.isEmpty()){
            toDefineCategories();
        } else {
            String total_income;
            double income_sum = 0.0;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH)+1;
            int currentYear = calendar.get(Calendar.YEAR);
            MonthlyRevenue mr = db.monthlyRevenueDao().findByMonthAndYear(currentMonth, currentYear);
            if (mr == null){
                db.monthlyRevenueDao().insertAll(new MonthlyRevenue(db.incomeDao().incomeSum(), currentMonth, currentYear));
                mr = db.monthlyRevenueDao().findByMonthAndYear(currentMonth, currentYear);
            }
            int month = mr.id;
//            List<Income> incList = db.incomeDao().findByMonth(month);
//            for(int i = 0; i < incList.size(); i++){
//                income_sum += incList.get(i).value;
//            }
            total_income = Double.toString(db.incomeDao().incomeSum()) + "$";
            Intent intent = new Intent(Welcome_Page.this, Home.class);
            intent.putExtra("total_income_value", total_income);
            startActivity(intent);
        }


    }

    private void toDefineCategories(){
        get_started = findViewById(R.id.getStarted_button);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome_Page.this, Define_categories.class));
            }
        });
    }
}