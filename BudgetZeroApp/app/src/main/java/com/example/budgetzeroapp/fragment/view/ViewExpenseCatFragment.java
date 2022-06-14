package com.example.budgetzeroapp.fragment.view;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;

//nom, budget
//liens cliquables vers sous catégories
//liste des dépenses de la catégorie
public class ViewExpenseCatFragment extends DataBaseFragment {

    private TextView name, budget;
    private ListView subCatList, expList;
    private String nameVal;
    private float budgetVal;
    private List<CategoryItem> subCatVal;
    private List<ExpenseItem> expVal;

    public ViewExpenseCatFragment(){ super(); }
    public ViewExpenseCatFragment(int id){ super(id); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_expense_cat, parent, false);
        name = view.findViewById(R.id.textViewCatNameEntry);
        budget = view.findViewById(R.id.textViewCatBudgetEntry);
        expList = view.findViewById(R.id.listViewCatExpenses);
        subCatList = view.findViewById(R.id.listViewCatClickSub);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {

        Cursor cat = database.getCatFromType(id, DBHelper.TYPE_EXP);
        cat.moveToFirst();
        if (cat.isAfterLast()) redirect(new HomeFragment());
        else {
            nameVal = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            budgetVal = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            subCatVal = CategoryItem.initCategoryList(database, false);
            expVal = ExpenseItem.catExpensesToList(database, id, DBHelper.TYPE_EXP);
        }
    }

    public void setValues()   {
        name.setText(nameVal);
        budget.setText(String.valueOf(budgetVal));
        subCatList = ClickableListManager.clickableBudgetList(expList, subCatVal);
        ClickableListManager.clickableExpenseList(expList, expVal);

    }
}
