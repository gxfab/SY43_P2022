package com.example.budgetzeroapp.tool;

import android.annotation.SuppressLint;
import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetzeroapp.AppVars;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ToolBar{

    private Date date;
    private Calendar cal;
    private float moneyAmount;
    private boolean autoMode;
    private TextView dateText, amountText;

    private static ToolBar instance = new ToolBar();

    private static final NavController navContBudget= Navigation.findNavController(
            MainActivity.getActivity(), R.id.nav_host_fragment);

    private static AppBarConfiguration appBarConfigBudget =
            new AppBarConfiguration.Builder(R.id.budgetFragment,R.id.addCategoryFragment).build();

    // Getter-Setters
    public static ToolBar getInstance() {
        return instance;
    }

    public static void setInstance(ToolBar instance) {
        ToolBar.instance = instance;
    }

    private void initialize(){
        date = new Date();
        autoMode = true;
        cal = Calendar.getInstance();
        updateMoneyAmount(new DBHelper(AppVars.getContext()));
    }

    @SuppressLint("NonConstantResourceId")
    public Toolbar initToolBar(View view, int idToolBar){
        if(date == null) initialize();
        Toolbar toolbar = view.findViewById(idToolBar);
        dateText = view.findViewById(R.id.date);
        amountText = view.findViewById(R.id.amount);
        CharSequence text = Float.toString(moneyAmount)+"€";
        amountText.setText(text);
        text = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(date);
        dateText.setText(text);
        if(idToolBar == R.id.toolbar_budget) {
            NavigationUI.setupWithNavController(
                toolbar, navContBudget, appBarConfigBudget);
        }
        toolbar.setOnMenuItemClickListener(item -> {
            NavController navController = Navigation.findNavController(view);
            switch(item.getItemId()){
                case R.id.addCategory:
                    navController.navigate(R.id.navigate_to_addCategory);
                    break;
                case R.id.next_day:
                    incrementDate();
                    SavingsManager.addStableExpenses(new DBHelper(AppVars.getContext()), date);
                    CharSequence mess = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(date);
                    dateText.setText(mess);
                    //TODO POPUP récapitulant les nouvelles dépenses si il y en a
                    break;
                case R.id.mode: autoMode = !autoMode;
                    break;
            }
            return true;
        });
        return toolbar;
    }


    public Date getDate(){ return date; }

    public void incrementDate(){
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
    }



    public float getMoneyAmount(){ return moneyAmount; }

    public void updateMoneyAmount(DBHelper database){
        moneyAmount = database.getAccountMoneyAmount();
    }
}
