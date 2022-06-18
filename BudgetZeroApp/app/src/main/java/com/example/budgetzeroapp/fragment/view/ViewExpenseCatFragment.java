package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.tool.ClickableListManager;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.ExpenseItem;

import java.util.List;


public class ViewExpenseCatFragment extends DataBaseFragment {

    private TextView name, budget;
    private TextView subListTextView, expListTextView;
    private ListView subCatList, expList;
    private String nameVal;
    private float budgetVal;
    private List<CategoryItem> subCatVal;
    private List<ExpenseItem> expVal;
    private Button edit;

    public ViewExpenseCatFragment(){ super(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_expense_cat, parent, false);
        return view;
    }

    public void getValues() {

        Cursor cat = database.getCatFromType(id, DBHelper.TYPE_EXP);
        cat.moveToFirst();
        if (!cat.isAfterLast()){
            nameVal = cat.getString(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            budgetVal = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            subCatVal = CategoryItem.initCategoryList(database.getSubCat(id), false);
            expVal = ExpenseItem.ExpensesToList(database.getExpCatExpAndSub(id));
        }
    }

    public void setValues()   {
        name.setText(nameVal);
        budget.setText(String.valueOf(budgetVal));
        ClickableListManager.clickableBudgetList(subCatList, subCatVal);
        if (subCatVal.isEmpty()){
            subListTextView.setVisibility(View.GONE);
            //subCatList.setVisibility(View.GONE);
        } else if(subCatList.getVisibility()==View.GONE) {
            subListTextView.setVisibility(View.VISIBLE);
            subCatList.setVisibility(View.VISIBLE);
        }
        ClickableListManager.clickableExpenseList(expList, expVal);
        if (expVal.isEmpty()){
            expListTextView.setVisibility(View.GONE);
        } else if(expList.getVisibility()==View.GONE) {
            expListTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Getting passed id**/
        id = ViewExpenseCatFragmentArgs.fromBundle(getArguments()).getIdExpenseCat();
        //Toast.makeText(getActivity(),"id : " + id,Toast.LENGTH_SHORT).show();

        NavController navController = Navigation.findNavController(view);

        /**Getting passed id**/
        id = ViewExpenseCatFragmentArgs.fromBundle(getArguments()).getIdExpenseCat();

        name = view.findViewById(R.id.textViewCatNameEntry);
        budget = view.findViewById(R.id.textViewCatBudgetEntry);
        expList = view.findViewById(R.id.listViewCatExpenses);
        subCatList = view.findViewById(R.id.listViewCatClickSub);
        subListTextView = view.findViewById(R.id.textViewCatClickSub);
        expListTextView = view.findViewById(R.id.textViewCatExpenses);
        edit = view.findViewById(R.id.editButton);

        if(edit != null){
            edit.setOnClickListener(view1 -> {
                navController.navigate(ViewExpenseCatFragmentDirections.actionViewExpenseCatFragmentToEditExpenseCatFragment(id));
            });
        }

        getValues();
        setValues();
    }
}