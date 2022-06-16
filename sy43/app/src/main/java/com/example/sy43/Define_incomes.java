package com.example.sy43;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sy43.database.AppDatabase;
import com.example.sy43.database.Income;
import com.example.sy43.database.MonthlyRevenue;

import java.util.Calendar;

public class Define_incomes extends AppCompatActivity {

    private CardView continue_btn;
    private EditText et1,et2,et3,et4;
    private TextView tv1,tv2,tv3,tv4;
    public String total_income;
    private static int income_sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_incomes);

        continue_btn = findViewById(R.id.continue_to_projects_btn);
        et1 = findViewById(R.id.salary_income_et);
        et2 = findViewById(R.id.help_income_et);
        et3 = findViewById(R.id.interest_income_et);
        et4 = findViewById(R.id.money_transfer_et);
        tv1 = findViewById(R.id.salary_income_tv);
        tv2 = findViewById(R.id.help_income_tv);
        tv3 = findViewById(R.id.interest_income_tv);
        tv4 = findViewById(R.id.money_transfer_tv);

        EditText[] et = new EditText[]{et1, et2, et3, et4};
        TextView[] tv = new TextView[]{tv1, tv2, tv3, tv4};

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.getInstance(view.getContext());


                for (int i=0; i<et.length; i++){
                    if (!et[i].getText().toString().matches("")|| (!et[i].getText().toString().matches("0"))){
                        income_sum  += parseInt(et[i].getText().toString());
                    }
                }
                total_income= Integer.toString(income_sum) + "$";
                Calendar calendar = Calendar.getInstance();
                int currentMonth = calendar.get(Calendar.MONTH)+1;
                int currentYear = calendar.get(Calendar.YEAR);
                db.monthlyRevenueDao().insertAll(new MonthlyRevenue(Double.valueOf(income_sum), currentMonth, currentYear));
                int monthID = db.monthlyRevenueDao().findByMonth(currentMonth).id;
                for (int i=0; i<et.length; i++){
                    db.incomeDao().insertAll(new Income(tv[i].getText().toString(),Double.parseDouble(et[i].getText().toString()), monthID ));
                }

                Intent intent = new Intent(Define_incomes.this, Home.class);
                intent.putExtra("total_income_value", total_income);
                startActivity(intent);
            }
        });
    }
}