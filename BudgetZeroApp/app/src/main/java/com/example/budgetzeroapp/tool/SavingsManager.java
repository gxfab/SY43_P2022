package com.example.budgetzeroapp.tool;

import static java.lang.Math.abs;

import android.database.Cursor;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.fragment.DataBaseFragment;

import java.util.Date;

@SuppressWarnings({"UnusedDeclaration"})
public class SavingsManager {
    private static DBHelper database = new DBHelper(AppContext.getContext());

    public static void distributeSavings(Date date ,float amount){
        int day = 1;//DateManager.dateToDay(date);
        int month = 7;//DateManager.dateToDay(date);
        int year = 2022;//DateManager.dateToDay(date);
        String name;
        int id;
        float currentAmount, maxAmount, addedAmount, initAmount = amount;

        Cursor saving = database.getAllSavingsCat();
        saving.moveToFirst();
        while(amount>0 && !saving.isAfterLast()){

            id = saving.getInt(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_ID));
            name = saving.getString(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            maxAmount = saving.getFloat(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            currentAmount = -database.getSumFromCat(id, DBHelper.TYPE_SAV);
            addedAmount = amount;
            if(maxAmount < currentAmount+amount) {
                addedAmount = maxAmount-currentAmount;
            }
            amount -= addedAmount;

            database.insertExpense(-addedAmount, "Savings for "+name, DBHelper.TYPE_SAV, id, false,day, month, year);
            saving.moveToNext();
        }
    }

    public static void updateBudget(int month){
        float sumExp, budget;
        int id;
        Cursor cat = database.getAllExpenseCat();
        cat.moveToFirst();
        while(!cat.isAfterLast()){
            id = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID));
            budget = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            sumExp = abs(database.getSumExpCatMonth(id, month));
            database.updateExpenseCatBudget(id, (sumExp+budget)/2);
            cat.moveToNext();
        }
    }

    public static void updateDebts(){
        float sumExp, objective;
        int id, monthsLeft;
        Cursor cat = database.getAllDebts();
        cat.moveToFirst();
        while(!cat.isAfterLast()){
            id = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.DEBT_COL_ID));
            monthsLeft = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.DEBT_COL_MONTH_LEFT));
            objective = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
            sumExp = abs(database.getSumFromCat(id, DBHelper.TYPE_DEBT));

            //database();
            cat.moveToNext();
        }
    }



    public static void addStableExpenses(Date date){

        int y = DateManager.dateToYear(date);
        int m = DateManager.dateToMonth(date);
        int dayNB=DateManager.dateToDay(date);

        int prevYear = DateManager.previousMonthYear(y, m);
        int prevMonth = DateManager.previousMonth(m);

        int day, month, year, idCat, type;
        float amount;
        String label, cat;

        Cursor expenses = database.getDateStableExpenses(prevYear, prevMonth, dayNB);
        expenses.moveToFirst();
        while(!expenses.isAfterLast()){
            label = expenses.getString(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL));
            amount = expenses.getFloat(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
            type  = expenses.getInt(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_TYPE));
            if(type==DBHelper.TYPE_EXP) cat = DBHelper.EXP_COL_ID_EXP;
            else if(type==DBHelper.TYPE_INC) cat = DBHelper.EXP_COL_ID_INC;
            else if(type==DBHelper.TYPE_DEBT) cat = DBHelper.EXP_COL_ID_DEBT;
            else cat = DBHelper.EXP_COL_ID_SAV;
            idCat = expenses.getInt(expenses.getColumnIndexOrThrow(cat));
            database.insertExpense(amount, label, type, idCat, true, dayNB, m, y);
            DataBaseFragment.message("New stable movement added");
            expenses.moveToNext();
        }
    }

}
