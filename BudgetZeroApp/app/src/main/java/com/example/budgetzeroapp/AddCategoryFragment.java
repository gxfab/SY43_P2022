package com.example.budgetzeroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

//Activer les trucs en com ici?
public class AddCategoryFragment extends Fragment /*implements AdapterView.OnItemSelectedListener*/ {

    //TODO declare objects to get from XML file. example :


    private TextView name;
    private TextView budget;
    private CheckBox sub;
    private Spinner parentCat;
    private Button submit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        //TODO get xml file content. example :
        View view = inflater.inflate(R.layout.fragment_add_category, parent, false);

        //TODO get xml elements from. ID example:
        name = view.findViewById(R.id.editTextCatName);
        budget = view.findViewById(R.id.editTextCatBudget);
        sub = view.findViewById(R.id.checkBoxCatSub);
        parentCat = view.findViewById(R.id.spinnerCatParent);
        /*
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.exp_cat_parents,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentCat.setAdapter(adapter);
        parentCat.setOnItemSelectedListener(this);
        */
        submit = view.findViewById(R.id.submit);

        return view;
    }
    /*
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

     */
}