package com.example.budgetzeroapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

//Activer les trucs en com ici?
public class EditExpenseCatFragment extends Fragment /*implements AdapterView.OnItemSelectedListener*/ {

    private DBHelper database;
    private MainActivity activity;

    private int id;

    private EditText name, budget;
    private CheckBox sub;
    private Spinner parentCat;
    private Button submit;


    public EditExpenseCatFragment(){}

    public EditExpenseCatFragment(int id){
        activity = (MainActivity) getActivity();
        database = new DBHelper(activity.getApplicationContext());
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_expense_cat, parent, false);

        //TODO get xml elements from. ID example:
        name = view.findViewById(R.id.editTextCatName);
        budget = view.findViewById(R.id.editTextCatBudget);
        sub = view.findViewById(R.id.checkBoxCatSub);
        parentCat = view.findViewById(R.id.spinnerCatParent);
        submit = view.findViewById(R.id.submit);

        String defaultName="";
        boolean defaultIsSub = false;
        float defaultBudget = 0;
        int defaultParentID = 0;

        if(id != 0){
            Cursor cat = database.getExpenseCatFromID(id);
            if(cat.isAfterLast()) id = 0;
            else{
                defaultName = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
                defaultBudget = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
                defaultIsSub = (1==cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_IS_SUB)));
                defaultParentID = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID_PARENT));
            }
        }
        name.setText(defaultName);
        budget.setText(""+defaultBudget);
        sub.setChecked(defaultIsSub);
        if(id!=0) {
            String compareValue = database.getExpCatName(defaultParentID);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(), R.array.exp_cat_parents, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            parentCat.setAdapter(adapter);
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                parentCat.setSelection(spinnerPosition);
            }
        }
        /*
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.exp_cat_parents,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentCat.setAdapter(adapter);
        parentCat.setOnItemSelectedListener(this);
        */

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