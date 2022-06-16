package com.example.budgetzeroapp.tool;

import android.database.Cursor;

import com.example.budgetzeroapp.AppContext;

import java.util.Date;

@SuppressWarnings({"UnusedDeclaration"})
public class SavingsManager {
    private static DBHelper database = new DBHelper(AppContext.getContext());

    public static void distributeSavings(float amount){
        Date date = ToolBar.getInstance().getDate();
        int day = DateManager.dateToDay(date);
        int month = DateManager.dateToDay(date);
        Cursor saving;
        int year = DateManager.dateToDay(date);
        int id;
        float currentAmount, maxAmount, addedAmount, initAmount = amount;
        int percent;
        int currentPriority = 1;

        saving = database.getAllSavingsCat();

        while(amount>0){
            if(!saving.isAfterLast()){

                id = saving.getInt(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_ID));
                maxAmount = saving.getFloat(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
                currentAmount = saving.getFloat(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_CURRENT_AMOUNT));
                addedAmount = amount;
                if(currentPriority == 0 || maxAmount == -1 || maxAmount> currentAmount) {
                    if(currentPriority==0||maxAmount == -1 || currentAmount+addedAmount <= maxAmount){
                        database.updateSavingsCurrentAmount(id,currentAmount+addedAmount);
                    }else{
                        database.updateSavingsCurrentAmount(id, maxAmount);
                        addedAmount = maxAmount-currentAmount;
                    }
                    amount -= addedAmount;
                    database.insertExpense(addedAmount, "", DBHelper.TYPE_SAV, id, false,day, month, year);
                }
                currentPriority++;
                saving.moveToNext();

            }else{
                currentPriority = 0;
                saving = database.getSavingsFromPriority(currentPriority);
            }
        }
        saving.close();
    }

    public static void updateBudget(Date date){
        float sumExp, budget;
        int month = DateManager.dateToMonth(date), id;
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
                if(type==DBHelper.TYPE_EXP) cat = DBHelper.EXP_CAT_TABLE_NAME;
                else if(type==DBHelper.TYPE_INC) cat = DBHelper.INC_CAT_TABLE_NAME;
                else if(type==DBHelper.TYPE_DEBT) cat = DBHelper.DEBT_TABLE_NAME;
                else cat = DBHelper.SAV_CAT_TABLE_NAME;
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
