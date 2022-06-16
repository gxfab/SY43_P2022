package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;

// name, max amount, current amount
//liste des expenses associées
public class EditSavingCatFragment extends EditDataBaseFragment{

    private EditText name, goal, percentage, priority;
    private Button save, cancel;
    private String defaultName;
    private float defaultGoal, defaultPercentage;
    private int defaultPriority;

    public EditSavingCatFragment(){ super(); }
    public EditSavingCatFragment(int id){ super(id); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_saving_cat, parent, false);
        save = view.findViewById(R.id.buttonSave);
        cancel = view.findViewById(R.id.buttonCancel);
        name = view.findViewById(R.id.editTextSaveName);
        goal = view.findViewById(R.id.editTextSaveGoal);
        percentage = view.findViewById(R.id.editTextSavePerc);
        priority = view.findViewById(R.id.editTextSavePrio);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultGoal = 0;
        defaultPercentage = 0;
        defaultPriority = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor cat = database.getData("select * from "+ DBHelper.SAV_CAT_TABLE_NAME+
                " where "+DBHelper.SAV_CAT_COL_ID+"="+id);
        cat.moveToFirst();
        if (cat.isAfterLast()) id = 0;
        else {
            defaultName = cat.getString(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            defaultGoal = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            defaultPercentage = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PERCENTAGE));
            defaultPriority = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PRIORITY_ORDER));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        goal.setText(String.valueOf(defaultGoal));
        percentage.setText(String.valueOf((defaultPercentage)));
        priority.setText(String.valueOf((defaultPriority)));
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
}
