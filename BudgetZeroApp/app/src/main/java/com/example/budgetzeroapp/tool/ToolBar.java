package com.example.budgetzeroapp.tool;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.BudgetFragmentDirections;
import com.example.budgetzeroapp.fragment.CashFlowFragmentDirections;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.HomeFragmentDirections;
import com.example.budgetzeroapp.fragment.SavingsFragmentDirections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ToolBar{

    private Date date;
    private Calendar cal;
    private float moneyAmount;
    private boolean budgetAutoMode, savingsAutoMode;
    private TextView dateText, amountText;

    private static final String PREFS_NAME = "prefsFile";

    private static SharedPreferences settings = AppContext.getContext().getSharedPreferences(PREFS_NAME, 0);
    private static SharedPreferences.Editor editor = settings.edit();

    private static final DBHelper database = new DBHelper(AppContext.getContext());

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE);


    private static final NavController navController= Navigation.findNavController(
            MainActivity.getActivity(), R.id.nav_host_fragment);

    private static AppBarConfiguration appBarConfigBudget =
            new AppBarConfiguration.Builder(R.id.budgetFragment,R.id.editExpenseCatFragment).build();
    private static AppBarConfiguration appBarConfigSavings =
            new AppBarConfiguration.Builder(R.id.savingsFragment,R.id.editSavingCatFragment).build();
    private static AppBarConfiguration appBarConfigExpCat =
            new AppBarConfiguration.Builder(R.id.homeFragment,R.id.editExpenseCatFragment).build();

    public ToolBar(){
        initialize();
    }

    private void initialize(){
        try{
            date = format.parse(settings.getString("date",format.format(new Date())));
        }catch(ParseException p) {
            date = new Date();
        }
        budgetAutoMode = settings.getBoolean("budgetAutoMode", true);
        savingsAutoMode = settings.getBoolean("savingsAutoMode", true);
        cal = Calendar.getInstance();
        updateMoneyAmount();
    }

    @SuppressLint("NonConstantResourceId")
    public void initToolBar(View view, int idToolBar){
        if(date == null) initialize();
        Toolbar toolbar = view.findViewById(idToolBar);
        dateText = view.findViewById(R.id.date);
        amountText = view.findViewById(R.id.amount);
        CharSequence text = Float.toString(moneyAmount)+"€";
        amountText.setText(text);
        text = getStrDate();
        dateText.setText(text);
        if(idToolBar == R.id.toolbar_budget) {
            NavigationUI.setupWithNavController(
                    toolbar, navController, appBarConfigBudget);
        }else if(idToolBar == R.id.toolbar_savings){
            NavigationUI.setupWithNavController(
                    toolbar, navController, appBarConfigSavings);
        }
        toolbar.setOnMenuItemClickListener(item -> {
            NavController navController = Navigation.findNavController(view);
            switch(item.getItemId()){
                case R.id.addCategory:
                    int idDest = navController.getCurrentDestination().getId();
                    if(idDest == R.id.budgetFragment) navController.navigate(BudgetFragmentDirections.actionBudgetFragmentToEditExpenseCatFragment(0));
                    else if(idDest == R.id.homeFragment) navController.navigate(HomeFragmentDirections.actionHomeFragmentToEditExpenseCatFragment(0));
                    break;
                case R.id.addSavingsCat: navController.navigate(SavingsFragmentDirections.actionSavingsFragmentToEditSavingCatFragment());
                    break;
                case R.id.addDebt: navController.navigate(SavingsFragmentDirections.actionSavingsFragmentToEditDebtFragment());
                    break;
                case R.id.expense:
                    int idDest2 = navController.getCurrentDestination().getId();

                    if(idDest2 == R.id.savingsFragment) navController.navigate(SavingsFragmentDirections.actionSavingsFragmentToSelectExpenseTypeFragment2());
                    else if(idDest2 == R.id.budgetFragment) navController.navigate(BudgetFragmentDirections.actionBudgetFragmentToSelectExpenseTypeFragment2());
                    else if(idDest2 == R.id.homeFragment) navController.navigate(HomeFragmentDirections.actionHomeFragmentToSelectExpenseTypeFragment2());
                    else if(idDest2 == R.id.cashFlowFragment) navController.navigate(CashFlowFragmentDirections.actionCashFlowFragmentToSelectExpenseTypeFragment2());
                    break;

                case R.id.next_day:
                    incrementDate();
                    saveDate();
                    if(DateManager.dateToDay(date) == 1){
                        String message = "Last day of the month";
                        if(savingsAutoMode) {
                            SavingsManager.distributeSavings(date, moneyAmount);
                            message += "\nSavings updated";
                        }
                        if(budgetAutoMode) {
                            SavingsManager.updateBudget(DateManager.previousMonth(DateManager.dateToMonth(date)));
                            message += "\nBudget updated";
                        }
                        DataBaseFragment.message(message);
                    }
                    SavingsManager.addStableExpenses(date);
                    CharSequence mess = format.format(date);
                    dateText.setText(mess);
                    updateMoneyAmount();
                    CharSequence txt = Float.toString(moneyAmount)+"€";
                    amountText.setText(txt);
                    break;
                case R.id.mode:
                    if(idToolBar == R.id.toolbar_budget) {
                        budgetAutoMode = !budgetAutoMode;
                        saveBudgetMode();
                    }else{
                        savingsAutoMode = !savingsAutoMode;
                        saveSavingsMode();
                    }
                    break;

            }
            return true;
        });
    }


    public static Date getDate(){
        try{
            return format.parse(settings.getString("date",format.format(new Date())));
        }catch(ParseException p) {
                return new Date();
        }
    }

    public void saveDate(){
        editor.putString("date", getStrDate());
        editor.commit();
    }

    public void saveBudgetMode(){
        editor.putBoolean("budgetAutoMode", budgetAutoMode);
        editor.commit();
    }
    public void saveSavingsMode(){
        editor.putBoolean("savingsAutoMode", savingsAutoMode);
        editor.commit();
    }
    public void incrementDate(){
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
    }

    public String getStrDate(){ return format.format(date); }

    public float getMoneyAmount(){ return moneyAmount; }

    public void updateMoneyAmount(){
        moneyAmount = database.getAccountMoneyAmount();
    }
}
