package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ListView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;

//champs : name (string), month_left (int), total_amount(float)
// montant déjà remboursé
// liste de tous les paiements de cette dette
public class ViewDebtFragment extends DataBaseFragment {

    private TextView name, monthLeft, totalAmount, refundedAmount;
    private ListView exp;
    private String nameVal;
    private int monthLeftVal;
    private float totalAmountVal, refundedAmountVal;
    private List<ExpenseItem> expVal;

    public ViewDebtFragment(){ super(); }
    public ViewDebtFragment(int id){ super(id); }

    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_debt, parent, false);
        name = view.findViewById(R.id.textViewDebtNameEntry);
        monthLeft = view.findViewById(R.id.textViewDebtMonthEntry);
        totalAmount = view.findViewById(R.id.textViewDebtTotalAmountEntry);
        refundedAmount = view.findViewById(R.id.textViewDebtRefundedEntry);
        exp = view.findViewById(R.id.listViewDebtExpenses);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        Cursor debt = database.getCatFromType(id, DBHelper.TYPE_DEBT);
        debt.moveToFirst();
        if (debt.isAfterLast()) redirect(new HomeFragment());
        else {
            nameVal = debt.getString(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            monthLeftVal=debt.getInt(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
            totalAmountVal=debt.getFloat(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
            refundedAmountVal = database.getSumFromCat(id, DBHelper.TYPE_DEBT);
            expVal = ExpenseItem.catExpensesToList(database, id, DBHelper.TYPE_DEBT);
        }
    }

    public void setValues() {
        name.setText(nameVal);
        monthLeft.setText(String.valueOf(monthLeftVal));
        totalAmount.setText(String.valueOf(totalAmountVal));
        refundedAmount.setText(String.valueOf(refundedAmountVal));
        ClickableListManager.clickableExpenseList(exp, expVal);
    }



}
