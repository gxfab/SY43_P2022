package com.example.budgetzeroapp.fragment.view;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.Calendar;

//nom, budget
//liens cliquables vers sous catégories
//liste des dépenses de la catégorie
public class ViewExpenseCatFragment extends DataBaseFragment {

    private TextView name, budget;
    private TextView [] subCat, exp;
    private LinearLayout subCatLayout, expLayout;
    private String nameVal;
    private float budgetVal;
    private String [] subCatVal;
    private String [] expVal;

    public ViewExpenseCatFragment(){ super(); }
    public ViewExpenseCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_expense_cat, parent, false);
        name = view.findViewById(R.id.textViewCatNameEntry);
        budget = view.findViewById(R.id.textViewCatBudgetEntry);
        expLayout = view.findViewById(R.id.layoutCatExpenses);
        subCatLayout = view.findViewById(R.id.layoutCatClickSub);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        /*
        int subCatIndex = 0;
        int expIndex = 0;
        Cursor cat = database.getData("select * from "+ DBHelper.EXP_CAT_TABLE_NAME+
                " where "+DBHelper.EXP_CAT_COL_ID+"="+id);
        cat.moveToFirst();
        if (cat.isAfterLast()) id = 0;
        else {
            nameVal = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            budgetVal = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            //Trouver toutes les valeurs de subCatVal et expVal
        }

         */
    }

    public void setValues()   {
        name.setText(nameVal);
        budget.setText(String.valueOf(budgetVal));
        for(int i=0;i<subCatVal.length;i++) {
            subCat[i] = new TextView(subCatLayout.getContext());
            subCat[i].setText(subCatVal[i]);
            subCatLayout.addView(subCat[i]);
        }
        for(int i=0;i<expVal.length;i++) {
            exp[i] = new TextView(expLayout.getContext());
            exp[i].setText(expVal[i]);
            expLayout.addView(exp[i]);
        }

    }
}
