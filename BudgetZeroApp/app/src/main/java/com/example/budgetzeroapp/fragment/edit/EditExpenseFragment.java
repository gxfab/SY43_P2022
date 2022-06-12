package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budgetzeroapp.HomeFragment;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.Calendar;

public class EditExpenseFragment extends EditDataBaseFragment {

    private Button save, cancel;
    private EditText name, date, amount;
    private CheckBox stable;
    private String defaultName;
    private Calendar defaultDate;
    private float defaultAmount;
    private int defaultStable;


    public EditExpenseFragment(){ super(); }
    public EditExpenseFragment(int id){ super(id); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_expense, parent, false);
        save = view.findViewById(R.id.buttonSave);
        cancel = view.findViewById(R.id.buttonCancel);
        name = view.findViewById(R.id.editTextExpName);
        date = view.findViewById(R.id.editTextExpDate);
        amount = view.findViewById(R.id.editTextExpAmount);
        stable = view.findViewById(R.id.checkBoxExpStable);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultDate = Calendar.getInstance();
        defaultAmount = 0;
        defaultStable = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor exp = database.getData("select * from "+ DBHelper.EXP_TABLE_NAME+
                " where "+DBHelper.EXP_COL_ID+"="+id);
        exp.moveToFirst();
        if (exp.isAfterLast()) id = 0;
        else {
            defaultName = exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL));
            defaultDate.set(Calendar.DATE, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_DAY)));
            defaultDate.set(Calendar.MONTH, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_MONTH)));
            defaultDate.set(Calendar.YEAR, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_YEAR)));
            defaultAmount = exp.getFloat(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
            defaultStable = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_IS_STABLE));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        date.setText(defaultDate.toString());
        amount.setText(String.valueOf(defaultAmount));
        stable.setChecked(defaultStable!=0);
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