package com.example.budgetzeroapp.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetzeroapp.AppContext;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.MainActivity;

public abstract class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected int id;

    public DataBaseFragment() { this(0);}

    public DataBaseFragment(int id) {
        database = new DBHelper(AppContext.getContext());
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setId(int id) {this.id = id;}

    public void message(String message) {
        Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void redirect(DataBaseFragment f, int id)
    {
        NavController navController = Navigation.findNavController(MainActivity.getActivity(), R.id.nav_host_fragment);

        NavDestination current_dest = navController.getCurrentDestination();
        int id_dest = current_dest.getId();
        if (id_dest == R.id.homeFragment) HomeFragment.redirectToViewExpenseCat(id);
        else if (id_dest == R.id.budgetFragment) BudgetFragment.redirectToViewExpenseCat(id);
        else if (id_dest == R.id.cashFlowFragment) CashFlowFragment.redirectToViewExpense(id);
        else if (id_dest == R.id.savingsFragment) SavingsFragment.redirectToViewSavingCat(id);
     }

}