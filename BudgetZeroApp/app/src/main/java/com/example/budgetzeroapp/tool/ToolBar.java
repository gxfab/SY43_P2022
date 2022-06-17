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

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE);
    private static ToolBar instance = new ToolBar();

    public ToolBar(){
        initialize();
    }

    private static final NavController navController= Navigation.findNavController(
            MainActivity.getActivity(), R.id.nav_host_fragment);

    private static AppBarConfiguration appBarConfigBudget =
            new AppBarConfiguration.Builder(R.id.budgetFragment,R.id.addCategoryFragment).build();

    private static AppBarConfiguration appBarConfigSavings =
            new AppBarConfiguration.Builder(R.id.savingsFragment,R.id.addCategoryFragment).build();

    public static ToolBar getInstance() {
        return instance;
    }

    public static void setInstance(ToolBar instance) {
        ToolBar.instance = instance;
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
        updateMoneyAmount(new DBHelper(AppContext.getContext()));
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
                    navController.navigate(R.id.navigate_to_addCategory);
                    break;
                case R.id.next_day:
                    if(DateManager.isLastMonthDay(date)){
                        if(savingsAutoMode) SavingsManager.distributeSavings(moneyAmount);
                        if(budgetAutoMode) SavingsManager.updateBudget(date);
                    }
                    incrementDate();
                    saveDate();
                    SavingsManager.addStableExpenses(date);

                    CharSequence mess = format.format(date);
                    dateText.setText(mess);
                    break;
                case R.id.mode:
                    if(idToolBar == R.id.toolbar_budget) budgetAutoMode = !budgetAutoMode;
                    else saveBudgetMode();
                    break;

            }
            return true;
        });
    }


    public Date getDate(){ return date; }

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

    public void updateMoneyAmount(DBHelper database){
        moneyAmount = database.getAccountMoneyAmount();
    }
}
