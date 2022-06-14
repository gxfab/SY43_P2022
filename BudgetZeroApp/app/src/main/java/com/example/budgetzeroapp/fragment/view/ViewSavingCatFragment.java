package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
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

//nom, max amount, current amount, ordre de priorité/pourcentage
//liste des savings associés
public class ViewSavingCatFragment extends DataBaseFragment {

    private TextView name, goal, current, prio, perc;
    private ListView saveList;
    private String nameVal;
    private float goalVal, currentVal, percVal;
    private int prioVal;
    private List<ExpenseItem> saveListVal;

    public ViewSavingCatFragment(){ super(); }
    public ViewSavingCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_saving_cat, parent, false);
        name = view.findViewById(R.id.textViewSaveNameEntry);
        goal = view.findViewById(R.id.textViewSaveGoalEntry);
        current = view.findViewById(R.id.textViewSaveCurrentEntry);
        prio = view.findViewById(R.id.textViewSavePrioEntry);
        perc = view.findViewById(R.id.textViewSavePercEntry);
        saveList = view.findViewById(R.id.listViewSaveList);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        Cursor save = database.getCatFromType(id, DBHelper.TYPE_SAV);
        save.moveToFirst();
        if (save.isAfterLast()) redirect(new HomeFragment());
        else {
            nameVal = save.getString(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            goalVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            currentVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_CURRENT_AMOUNT));
            prioVal = save.getInt(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PRIORITY_ORDER));
            percVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PERCENTAGE));
            saveListVal = ExpenseItem.catExpensesToList(database, id, DBHelper.TYPE_SAV);
        }
    }

    public void setValues()   {
        name.setText(nameVal);
        goal.setText(String.valueOf(goalVal));
        current.setText(String.valueOf(currentVal));
        prio.setText(String.valueOf(prioVal));
        perc.setText(String.valueOf(percVal));
        ClickableListManager.clickableExpenseList(saveList,saveListVal);
    }
}
