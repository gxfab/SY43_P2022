package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.SavingsFragment;
import com.example.budgetzeroapp.fragment.view.ViewExpenseCatFragment;
import com.example.budgetzeroapp.fragment.view.ViewIncomeCatFragment;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.ArrayList;

public class CategoryItem extends ListItem{

    private float budget, total, percentage;
    private DataBaseFragment frag;

    public CategoryItem(int id, String name, float total, float pOrB){
        super(id, name);
        percentage = pOrB;
        budget = pOrB;
        this.total = total;
    }

    public int getDrawable(){
        switch (name) {
            case "Health":
                return R.drawable.ic_heart;
            case "Shopping":
                return R.drawable.ic_shopping;
            case "Leisure":
                return R.drawable.ic_leisure;
            case "Vehicle":
                return R.drawable.ic_car;
            case "Miscellaneous":
                return R.drawable.ic_coffee;
            default:
                return R.drawable.ic_default_thumb;
        }
    }

    public static ArrayList<CategoryItem> initCategoryList(DBHelper database, boolean type){
        Cursor rows = database.getMainExpCat();
        ArrayList<CategoryItem> list = new ArrayList<>();
        int id;
        float amount, total, perOrBudget;
        total = database.getSumExp();
        String name;
        rows.moveToFirst();
        while (!rows.isAfterLast()) {
            id = rows.getInt(rows.getColumnIndexOrThrow("id"));
            name = rows.getString(rows.getColumnIndexOrThrow("name"));
            amount = database.getSumCatExp(id);
            if(type) perOrBudget = (100 * amount / total);
            else perOrBudget = rows.getFloat(rows.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            list.add(new CategoryItem(id, name, amount, perOrBudget));
            rows.moveToNext();
        }
        if(type){
            amount = database.getSumSav();
            list.add(new CategoryItem(-1, "Savings", amount, (int)(100*amount/total)));
            amount = database.getSumDebt();
            list.add(new CategoryItem(0, "Debts", amount, (int)(100*amount/total)));
        }
        return list;
    }

    public void redirect(){
        if(id == 0) redirect(DBHelper.TYPE_DEBT);
        else if(id == -1) redirect(DBHelper.TYPE_SAV);
        else redirect(DBHelper.TYPE_EXP);

    }
    public void redirect(int type){
        if(type == DBHelper.TYPE_DEBT || type == DBHelper.TYPE_SAV)
            MainActivity.getActivity().bottomNavigationRedirect(R.id.savingsFragment);
        if(type == DBHelper.TYPE_DEBT) DataBaseFragment.redirect(new SavingsFragment(false));
        else if(type == DBHelper.TYPE_SAV) DataBaseFragment.redirect(new SavingsFragment(true));
        else if(type == DBHelper.TYPE_INC) DataBaseFragment.redirect(new ViewIncomeCatFragment(id));
        else DataBaseFragment.redirect(new ViewExpenseCatFragment(id));
    }

    public float getTotal(){ return total; }
    public float getPercent(){ return percentage; }
    public float getBudget(){ return budget; }
}