package com.example.budgetzeroapp.fragment.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;

// Champs à éditer : name (string), month_left(int) et total_amount (float)
//bouton save
public class EditDebtFragment extends EditDataBaseFragment{

    private Button save, cancel;
    private String defaultName;
    private int defaultMonthLeft;
    private float defaultTotalAmount;
    private EditText name, monthLeft, totalAmount;

    public EditDebtFragment(){ super(); }
    public EditDebtFragment(int id){ super(id); }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_debt, parent, false);
        save = view.findViewById(R.id.buttonSave);
        cancel = view.findViewById(R.id.buttonCancel);
        name = view.findViewById(R.id.editTextDebtName);
        monthLeft = view.findViewById(R.id.editTextDebtMonth);
        totalAmount = view.findViewById(R.id.editTextDebtAmount);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        defaultMonthLeft = 0;
        defaultTotalAmount = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor debt = database.getData("select * from "+DBHelper.DEBT_TABLE_NAME+
                " where "+DBHelper.DEBT_COL_ID+"="+id);
        debt.moveToFirst();
        if (debt.isAfterLast()) id = 0;
        else {
            defaultName = debt.getString(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            defaultMonthLeft = debt.getInt(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
            defaultTotalAmount = debt.getFloat(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        monthLeft.setText(String.valueOf(defaultMonthLeft));
        totalAmount.setText(String.valueOf(defaultTotalAmount));
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newAmount = Float.parseFloat(totalAmount.getText().toString());
            if(newAmount <= 0){
                message("Amount must have a positive value");
                return;
            }
            int newMonthLeft = Integer.parseInt(monthLeft.getText().toString());
            if(newAmount <= 0){
                message("Months left must have a positive value");
                return;
            }
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }

            if(id == 0) database.insertDebtCat(newName, newMonthLeft, newAmount);
            else database.updateDebtCat(id, newName, newMonthLeft, newAmount);

        });
        cancel.setOnClickListener(v -> {
            //Cancel
        });
    }
}
