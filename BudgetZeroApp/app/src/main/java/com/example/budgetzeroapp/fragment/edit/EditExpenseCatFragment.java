package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.R;

//Activer les trucs en com ici?
public class EditExpenseCatFragment extends EditDataBaseFragment /*implements AdapterView.OnItemSelectedListener*/ {

    private EditText name, budget;
    private CheckBox sub;
    private Spinner parentCat;
    private Button save, cancel;
    private String defaultName, defaultParentCat;
    private float defaultBudget;
    private int defaultSub;

    public EditExpenseCatFragment(){ super(); }
    public EditExpenseCatFragment(int id){ super(id); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_expense_cat, parent, false);
        save = view.findViewById(R.id.buttonSave);
        cancel = view.findViewById(R.id.buttonCancel);
        name = view.findViewById(R.id.editTextCatName);
        budget = view.findViewById(R.id.editTextCatBudget);
        sub = view.findViewById(R.id.checkBoxCatSub);
        parentCat = view.findViewById(R.id.spinnerCatParent);
        //Pas hyper sur de comment ça marche
        /*
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.exp_cat_parents,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentCat.setAdapter(adapter);
        parentCat.setOnItemSelectedListener(this);
         */
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultBudget = 0;
        defaultSub = 0;
        defaultParentCat = "";        //existe si sub=false?
    }

    @Override
    public void changeDefaultValues() {
        Cursor cat = database.getData("select * from "+DBHelper.EXP_CAT_TABLE_NAME+
                " where "+DBHelper.EXP_CAT_COL_ID+"="+id);
        cat.moveToFirst();
        if (cat.isAfterLast()) id = 0;
        else {
            defaultName = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            defaultBudget = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            defaultSub = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_IS_SUB));
            defaultParentCat = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID_PARENT));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        budget.setText(String.valueOf(defaultBudget));
        sub.setChecked(defaultSub!=0);
        parentCat.setSelection(0);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {
            //Save
        });
        cancel.setOnClickListener(v -> {
            //Cancel
        });
    }
    /*
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedParentCat = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, selectedParentCat,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

     */
}