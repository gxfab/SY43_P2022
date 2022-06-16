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
import com.example.budgetzeroapp.tool.adapter.SimpleListAdapter;
import com.example.budgetzeroapp.tool.item.ListItem;

import java.util.List;

public class EditExpenseCatFragment extends EditDataBaseFragment {
    private EditText name, budget;
    private CheckBox sub;
    private Spinner parentCat;
    private Button save, cancel;
    private String defaultName;
    private float defaultBudget;
    private int defaultSub, defaultParentCat;
    
    List<ListItem> list;


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
        list = ListItem.initList(database.getAllExpenseCat());
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultBudget = 0;
        defaultSub = 0;
        defaultParentCat = 0;
        parentCat.setAdapter(new SimpleListAdapter(list));

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
            defaultParentCat = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID_PARENT));
            int it = 0;
            for (ListItem i : list){
                if(i.getId() == defaultParentCat) parentCat.setSelection(it);
                it++;
            }
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        budget.setText(String.valueOf(defaultBudget));
        sub.setChecked(defaultSub!=0);
        // TODO: Faire fonctionner ce truc
        parentCat.setSelection(0);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newBudget = Float.parseFloat(budget.getText().toString());
            if(newBudget <= 0){
                message("Budget must have a positive value");
                return;
            }
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }
            boolean isSub = sub.isChecked();
            int idCat = ((ListItem) parentCat.getSelectedItem()).getId();

            //Temp
            int newParentCat = 0;

            if(id == 0) database.insertExpenseCat(newName,newBudget,isSub,newParentCat);
            else database.updateExpenseCat(id, newName,newBudget,isSub,newParentCat);

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