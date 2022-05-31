package com.example.zeroday.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zeroday.R;

public class ExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Boolean inputType = getIntent().getBooleanExtra("inputType", true);
        Boolean outputType = getIntent().getBooleanExtra("outputType", false);

        TextView title = findViewById(R.id.expenses_title);
        TextView subTitle = findViewById(R.id.expenses_subtitle);

        if(inputType == false){
            title.setText("Add Incomes");
            subTitle.setText("Add one or more incomes");
        }
        else {
            title.setText("Add Expenses");
            subTitle.setText("Add one or more expenses");
        }




    }

}
