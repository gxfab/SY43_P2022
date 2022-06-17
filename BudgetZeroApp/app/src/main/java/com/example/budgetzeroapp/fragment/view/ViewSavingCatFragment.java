package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    private TextView listTextView;
    private ListView saveList;
    private String nameVal;
    private float goalVal, currentVal, percVal;
    private int prioVal;
    private List<ExpenseItem> saveListVal;
    private Button edit;

    public ViewSavingCatFragment(){ super(); }
    public ViewSavingCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_saving_cat, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Getting passed id**/
        id = ViewSavingCatFragmentArgs.fromBundle(getArguments()).getIdSavings();
        Toast.makeText(getActivity(),"id : " + id,Toast.LENGTH_SHORT).show();

        name = view.findViewById(R.id.textViewSaveNameEntry);
        goal = view.findViewById(R.id.textViewSaveGoalEntry);
        current = view.findViewById(R.id.textViewSaveCurrentEntry);
        prio = view.findViewById(R.id.textViewSavePrioEntry);
        perc = view.findViewById(R.id.textViewSavePercEntry);
        saveList = view.findViewById(R.id.listViewSaveList);
        listTextView = view.findViewById(R.id.textViewSaveList);
        edit = view.findViewById(R.id.editButton);
        getValues();
        setValues();
    }

    public void getValues() {
        Cursor save = database.getCatFromType(id, DBHelper.TYPE_SAV);
        save.moveToFirst();
        if (save.isAfterLast()) redirect(new HomeFragment(),id);
        else {
            nameVal = save.getString(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            goalVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            currentVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_CURRENT_AMOUNT));
            prioVal = save.getInt(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PRIORITY_ORDER));
            percVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PERCENTAGE));
            saveListVal = ExpenseItem.ExpensesToList(database.getExpensesFromCat(id, DBHelper.TYPE_SAV));
        }
    }

    public void setValues()   {
        name.setText(nameVal);
        goal.setText(String.valueOf(goalVal));
        current.setText(String.valueOf(currentVal));
        prio.setText(String.valueOf(prioVal));
        perc.setText(String.valueOf(percVal));
        ClickableListManager.clickableExpenseList(saveList,saveListVal);
        if (saveListVal.isEmpty()){
            listTextView.setVisibility(View.GONE);
            saveList.setVisibility(View.GONE);
        } else if(saveList.getVisibility()==View.GONE) {
            listTextView.setVisibility(View.GONE);
            saveList.setVisibility(View.VISIBLE);
        }
    }
}