package com.example.budgetzeroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Date;

public class EditExpenseFragment extends EditDataBaseFragment {

    private Button save, cancel;
    private int type, idCat;
    private Date date;
    private float amount;
    private boolean isStable;
    private String label;


    public EditExpenseFragment(){ super(); }
    public EditExpenseFragment(int id){ super(id); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    public void initDefaultValues() {

    }

    @Override
    public void changeDefaultValues() {

    }

    @Override
    public void setDefaultValues() {

    }

    @Override
    public void setButtons() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_expense, parent, false);

        //TODO get xml elements from id

        save.setOnClickListener(v -> {
            //TODO controle saisie utilisateur
            database.insertExpense(date, amount, label, type, idCat, isStable);
            activity.replaceFragment(new HomeFragment());
            if(isAdded())
                Toast.makeText(activity.getApplicationContext(), "Expense successfully added !", Toast.LENGTH_SHORT).show();
        });
        cancel.setOnClickListener(v -> activity.replaceFragment(new HomeFragment()));
        return view;
    }


}