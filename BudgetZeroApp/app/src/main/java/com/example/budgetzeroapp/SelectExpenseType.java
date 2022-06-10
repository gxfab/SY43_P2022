package com.example.budgetzeroapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import android.app.Activity;



public class SelectExpenseType extends Activity {

    private RadioGroup radioGroup;
    Button submit;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_expense_type);

        submit = (Button)findViewById(R.id.submit);
        radioGroup = (RadioGroup)findViewById(R.id.group_radio_type);

        submit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            int type;
            switch(selectedId){
                case R.id.type_exp: type = DBHelper.TYPE_EXP;
                    break;
                case R.id.type_inc: type = DBHelper.TYPE_INC;
                    break;
                case R.id.type_debt: type = DBHelper.TYPE_DEBT;
                    break;
                case R.id.type_sav: type = DBHelper.TYPE_SAV;
                    break;
            }
            //Léo: ouvrir ici page DisplayExpense en passant la variable "type"
        });
    }
}