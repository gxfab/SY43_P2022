package com.example.budgetzeroapp;

import android.app.Application;
import android.content.Context;

import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.ToolBar;

import java.util.Calendar;
import java.util.Date;

public class AppContext extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() { return context; }
}
