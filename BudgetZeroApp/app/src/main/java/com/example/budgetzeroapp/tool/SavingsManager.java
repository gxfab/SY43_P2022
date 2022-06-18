package com.example.budgetzeroapp.tool;

import android.database.Cursor;

import com.example.budgetzeroapp.AppContext;

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
            if(maxAmount > currentAmount) {
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
            sumExp = database.getSumExpCatMonth(id, month);
            database.updateExpenseCatBudget(id, (sumExp+budget)/2);
            cat.moveToNext();
        }
    }

    public static void updateDebts(int month){
        float sumExp, budget;
        int id;
        Cursor cat = database.getAllExpenseCat();
        cat.moveToFirst();
        while(!cat.isAfterLast()){
            id = cat.getInt(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID));
            budget = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_BUDGET));
            sumExp = database.getSumExpCatMonth(id, month);
            database.updateExpenseCatBudget(id, (sumExp+budget)/2);
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

        Cursor expenses;
        if(DateManager.isLastMonthDay(y, m, dayNB)) expenses = database.getDateExpenses(prevYear, prevMonth, dayNB);
        else expenses = database.getEndMonthExpenses(prevYear, prevMonth, dayNB);

        while(!expenses.isAfterLast()){
            if(expenses.getInt(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_IS_STABLE))==1){
                label = expenses.getString(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL));
                amount = expenses.getFloat(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
                type  = expenses.getInt(expenses.getColumnIndexOrThrow(DBHelper.EXP_COL_TYPE));
                if(type==DBHelper.TYPE_EXP) cat = DBHelper.EXP_COL_ID_EXP;
                else if(type==DBHelper.TYPE_INC) cat = DBHelper.EXP_COL_ID_INC;
                else if(type==DBHelper.TYPE_DEBT) cat = DBHelper.EXP_COL_ID_DEBT;
                else cat = DBHelper.EXP_COL_ID_SAV;
                idCat = expenses.getInt(expenses.getColumnIndexOrThrow(cat));
                if(type!=DBHelper.TYPE_DEBT) database.insertExpense(amount, label, type, idCat, true, dayNB, m, y);
                else {
                    if(database.decrementDebtMonthLeft(idCat))
                        database.insertExpense(amount, label, type, idCat, true, dayNB, m, y);
                }
            }
            expenses.moveToNext();
        }
    }

}
