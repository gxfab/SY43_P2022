package com.example.zeroday.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.zeroday.R;

public class AddFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Button incomeButton = view.findViewById(R.id.add_income_button);
        incomeButton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(),ExpenseActivity.class);
                intent.putExtra("inputType", false);
                intent.putExtra("outputType",false);
                startActivity(intent);
            }
        });


        Button expenseButton = view.findViewById(R.id.add_expense_button);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ExpenseActivity.class);
                intent.putExtra("inputType", true);
                intent.putExtra("outputType",true);
                startActivity(intent);
            }
        });


        return view;
    }
}
