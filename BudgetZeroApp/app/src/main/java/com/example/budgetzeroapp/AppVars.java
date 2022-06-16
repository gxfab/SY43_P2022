package com.example.budgetzeroapp;

import android.app.Application;
import android.content.Context;

import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.ToolBar;

import java.util.Calendar;
import java.util.Date;

public class AppVars extends Application {
    private static Context context;
    private float moneyAmount;
    private boolean autoMode;
    private Date date;
    private Calendar cal;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        updateMoneyAmount(new DBHelper(context));
        autoMode = true;
        date = new Date();
        cal = Calendar.getInstance();
    }

    public static Context getContext() { return context; }

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
