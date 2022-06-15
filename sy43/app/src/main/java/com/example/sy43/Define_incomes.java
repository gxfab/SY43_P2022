package com.example.sy43;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Define_incomes extends AppCompatActivity {

    private CardView continue_btn;
    private EditText et1,et2,et3,et4;
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

        EditText[] et = new EditText[]{et1, et2, et3, et4};

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0; i<et.length; i++){
                    if (!et[i].getText().toString().matches("")){
                        income_sum  += parseInt(et[i].getText().toString());
                    }
                }
                total_income= Integer.toString(income_sum) + "$";
                Intent intent = new Intent(Define_incomes.this, Define_Projects.class);
                intent.putExtra("total_income_value", total_income);
                startActivity(intent);
            }
        });
    }
}