package com.example.budgetzeroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


public class SelectExpenseTypeFragment extends Fragment {

    private MainActivity activity;
    private RadioGroup radioGroup;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_select_expense_type, parent, false);

        submit = view.findViewById(R.id.submit);
        radioGroup = view.findViewById(R.id.group_radio_type);

        submit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            int type;
            if(selectedId == R.id.type_exp) type = DBHelper.TYPE_EXP;
            else if(selectedId == R.id.type_inc) type = DBHelper.TYPE_INC;
            else if(selectedId == R.id.type_debt) type = DBHelper.TYPE_DEBT;
            else type = DBHelper.TYPE_SAV;

            if(isAdded())
                activity.replaceFragment(new ExpenseFragment(type));
        });

        return view;
    }
}