package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.BudgetFragmentDirections;
import com.example.budgetzeroapp.fragment.HomeFragmentDirections;
import com.example.budgetzeroapp.fragment.view.ViewExpenseCatFragmentDirections;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.ArrayList;

public class CategoryItem extends ListItem{

    private float budget, total, percentage;

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

    public static ArrayList<CategoryItem> initCategoryList(Cursor rows,boolean type){
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
        NavController navController = Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
        int idDest = navController.getCurrentDestination().getId();
        if(id <= 0) MainActivity.getActivity().bottomNavigationRedirect(R.id.savingsFragment);
        else if(idDest == R.id.homeFragment) navController.navigate(HomeFragmentDirections.actionHomeFragmentToViewExpenseCatFragment(id));
        else if(idDest == R.id.budgetFragment) navController.navigate(BudgetFragmentDirections.actionBudgetFragmentToViewExpenseCatFragment(id));
        else if(idDest == R.id.viewExpenseCatFragment) navController.navigate(ViewExpenseCatFragmentDirections.actionViewExpenseCatFragmentSelf(id));
    }

    public float getTotal(){ return total; }
    public float getPercent(){ return percentage; }
    public float getBudget(){ return budget; }
}