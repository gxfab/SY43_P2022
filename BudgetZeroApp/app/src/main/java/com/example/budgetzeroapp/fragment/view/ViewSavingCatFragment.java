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
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
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

    private TextView name, goal, current;
    private TextView listTextView;
    private ListView saveList;
    private String nameVal;
    private float goalVal, currentVal;
    private List<ExpenseItem> saveListVal;
    private Button edit;

    public ViewSavingCatFragment(){ super(); }
    public ViewSavingCatFragment(int id){ super(id); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_saving_cat, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Listener edit button**/
        view.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            NavController navController = Navigation.findNavController(view);
            @Override
            public void onClick(View view) {
                navController.navigate(ViewSavingCatFragmentDirections.navigateToEditSavingCatFromSavingCat(id));
            }
        });

        /**Getting passed id**/
        id = ViewSavingCatFragmentArgs.fromBundle(getArguments()).getIdSavings();

        name = view.findViewById(R.id.textViewSaveNameEntry);
        goal = view.findViewById(R.id.textViewSaveGoalEntry);
        current = view.findViewById(R.id.textViewSaveCurrentEntry);
        saveList = view.findViewById(R.id.listViewSaveList);
        listTextView = view.findViewById(R.id.textViewSaveList);
        edit = view.findViewById(R.id.editButton);
        getValues();
        setValues();
    }

    public void getValues() {
        Cursor save = database.getCatFromType(id, DBHelper.TYPE_SAV);
        save.moveToFirst();
        if (!save.isAfterLast()){
            nameVal = save.getString(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            goalVal = save.getFloat(save.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            currentVal = -database.getSumFromCat(id, DBHelper.TYPE_SAV);
            saveListVal = ExpenseItem.ExpensesToList(database.getExpensesFromCat(id, DBHelper.TYPE_SAV));
        }
    }

    public void setValues()   {
        name.setText(nameVal);
        goal.setText(String.valueOf(goalVal));
        current.setText(String.valueOf(currentVal));
        ClickableListManager.clickableExpenseList(saveList,saveListVal);
        if (saveListVal.isEmpty()){
            listTextView.setVisibility(View.GONE);
            //saveList.setVisibility(View.GONE);
        } else if(saveList.getVisibility()==View.GONE) {
            listTextView.setVisibility(View.GONE);
            saveList.setVisibility(View.VISIBLE);
        }
    }
}