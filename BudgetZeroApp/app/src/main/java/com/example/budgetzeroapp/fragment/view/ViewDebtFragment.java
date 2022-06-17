package com.example.budgetzeroapp.fragment.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.BudgetFragmentDirections;
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
    private TextView listTextView;
    private ListView exp;
    private String nameVal;
    private int monthLeftVal;
    private float totalAmountVal, refundedAmountVal;
    private List<ExpenseItem> expVal;
    private Button edit;

    public ViewDebtFragment(){ super(); }
    public ViewDebtFragment(int id){ super(id); }


    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_debt, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**Listener edit button**/
        /*view.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            NavController navController = Navigation.findNavController(view);
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.navigate_to_editDebt_from_debt);
            }
        });*/


        /**Getting passed id**/
        id = ViewDebtFragmentArgs.fromBundle(getArguments()).getIdDebt();
        Toast.makeText(getActivity(),"id : " + id,Toast.LENGTH_SHORT).show();

        name = view.findViewById(R.id.textViewDebtNameEntry);
        monthLeft = view.findViewById(R.id.textViewDebtMonthEntry);
        totalAmount = view.findViewById(R.id.textViewDebtTotalAmountEntry);
        refundedAmount = view.findViewById(R.id.textViewDebtRefundedEntry);
        exp = view.findViewById(R.id.listViewDebtExpenses);
        listTextView = view.findViewById(R.id.textViewDebtExpenses);
        edit = view.findViewById(R.id.editButton);
        getValues();
        setValues();
    }

    public void getValues() {
        Cursor debt = database.getCatFromType(id, DBHelper.TYPE_DEBT);
        debt.moveToFirst();
        if (debt.isAfterLast()) redirect(new HomeFragment(),id);
        else {
            nameVal = debt.getString(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            monthLeftVal=debt.getInt(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
            totalAmountVal=debt.getFloat(debt.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
            refundedAmountVal = database.getSumFromCat(id, DBHelper.TYPE_DEBT);
            expVal = ExpenseItem.ExpensesToList(database.getExpensesFromCat(id, DBHelper.TYPE_DEBT));
        }
    }

    public void setValues() {
        name.setText(nameVal);
        monthLeft.setText(String.valueOf(monthLeftVal));
        totalAmount.setText(String.valueOf(totalAmountVal));
        refundedAmount.setText(String.valueOf(refundedAmountVal));
        ClickableListManager.clickableExpenseList(exp, expVal);
        if (expVal.isEmpty()){
            listTextView.setVisibility(View.GONE);
            exp.setVisibility(View.GONE);
        } else if(exp.getVisibility()==View.GONE) {
            listTextView.setVisibility(View.VISIBLE);
            exp.setVisibility(View.VISIBLE);
        }
    }

    public static void redirectToEditDebt(int id)
    {
        NavController navController= Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);
        NavDirections action = ViewDebtFragmentDirections.navigateToEditDebtFromDebt(id);
        navController.navigate(action);
    }



}