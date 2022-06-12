package com.example.budgetzeroapp;

import android.database.Cursor;
import java.util.Date;

@SuppressWarnings({"UnusedDeclaration"})
public class SavingsManager {

    public void distributeSavings(DBHelper database, float amount, boolean isPercentMode){
        Date date = new Date();
        Cursor saving;
        int id;
        float currentAmount, maxAmount, addedAmount, initAmount = amount;
        int percent;
        int currentPriority = 1;

        if(isPercentMode) saving = database.getAllSavingsCat();
        else saving = database.getSavingsFromPriority(currentPriority);

        while(amount>0){
            if(!saving.isAfterLast()){

                id = saving.getInt(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_ID));
                maxAmount = saving.getFloat(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
                currentAmount = saving.getFloat(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_CURRENT_AMOUNT));
                percent = saving.getInt(saving.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_PERCENTAGE));

                if(isPercentMode) addedAmount = initAmount*percent / 100;
                else addedAmount = amount;
                if(currentPriority == 0 || maxAmount == -1 || maxAmount> currentAmount) {
                    if(currentPriority==0||maxAmount == -1 || currentAmount+addedAmount <= maxAmount){
                        database.updateSavingsCurrentAmount(id,currentAmount+addedAmount);
                    }else{
                        database.updateSavingsCurrentAmount(id, maxAmount);
                        addedAmount = maxAmount-currentAmount;
                    }
                    amount -= addedAmount;
                    database.insertExpense(date,addedAmount, "", DBHelper.TYPE_SAV, id, false);

                }
                currentPriority++;
                if(isPercentMode) saving.moveToNext();
                else saving = database.getSavingsFromPriority(currentPriority);

            }else{
                currentPriority = 0;
                saving = database.getSavingsFromPriority(currentPriority);
            }
        }
        saving.close();
    }

    public void updateBudget(){
        //TODO
    }

    public void addStableExpenses(DBHelper database){

        Date date = new Date();
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
                if(type!=DBHelper.TYPE_DEBT) database.insertExpense(date, amount, label, type, idCat, true);
                else {
                    if(database.decrementDebtMonthLeft(idCat))
                        database.insertExpense(date, amount, label, type, idCat, true);
                }
            }
            expenses.moveToNext();
        }
    }

}
