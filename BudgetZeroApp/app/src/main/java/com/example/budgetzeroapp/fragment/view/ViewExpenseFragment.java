package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ViewExpenseFragment extends DataBaseFragment {

    private TextView name, date, amount, stable, type;
    private String nameVal;
    private Calendar dateVal;
    private float amountVal;
    private int stableVal, typeVal;

    public ViewExpenseFragment(){ super(); }
    public ViewExpenseFragment(int id){ super(id); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_expense, parent, false);
        name = view.findViewById(R.id.textViewExpNameEntry);
        date = view.findViewById(R.id.textViewExpDateEntry);
        amount = view.findViewById(R.id.textViewExpAmountEntry);
        stable = view.findViewById(R.id.textViewExpStableEntry);
        type = view.findViewById(R.id.textViewExpType);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        Cursor exp = database.getExpenseFromID(id);
        exp.moveToFirst();
        if (exp.isAfterLast()) redirect(new HomeFragment());
        else {
            nameVal = exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            amountVal = exp.getFloat(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
            dateVal = Calendar.getInstance();
            dateVal.set(Calendar.DATE, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_DAY)));
            dateVal.set(Calendar.MONTH, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_MONTH)));
            dateVal.set(Calendar.YEAR, exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_YEAR)));
            typeVal = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_TYPE));
            stableVal = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
        }
    }

    public void setValues() {
        name.setText(nameVal);
        amount.setText(String.valueOf(amountVal));
        Date dateString = dateVal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(dateFormat.format(dateString));
        switch(typeVal) {
            case 1:
                type.setText("Expense");
                break;
            case 2:
                type.setText("Income");
                break;
            case 3:
                type.setText("Debt");
                break;
            case 4:
                type.setText("Saving");
                break;
            default:
                type.setText("Unknown type");
                break;
        }
        if (stableVal==0)   {
            stable.setText("No");
        }   else    {
            stable.setText("Yes");
        }
    }
}
