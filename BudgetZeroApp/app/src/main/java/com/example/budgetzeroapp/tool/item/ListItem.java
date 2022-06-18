package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.view.ViewDebtFragment;
import com.example.budgetzeroapp.fragment.view.ViewExpenseCatFragment;
import com.example.budgetzeroapp.fragment.view.ViewExpenseFragment;
import com.example.budgetzeroapp.fragment.view.ViewIncomeCatFragment;
import com.example.budgetzeroapp.fragment.view.ViewSavingCatFragment;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListItem {
    protected int id;
    protected String name;

    protected static final DBHelper database = new DBHelper(AppContext.getContext());

    public ListItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName(){ return name;}

    public static List<ListItem> initList(Cursor c) {
        List<ListItem> list = new ArrayList<>();
        int id;
        String name;
        c.moveToFirst();
        while(!c.isAfterLast()) {
            id = c.getInt(c.getColumnIndexOrThrow(DBHelper.DEBT_COL_ID));
            name = c.getString(c.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            list.add(new ListItem(id, name));
            c.moveToNext();
        }
        return list;
    }

    public void redirect(){}

    public static List<ListItem> cursorToSimpleList(Cursor rows){
        List<ListItem> list = new ArrayList<>();
        rows.moveToFirst();
        int id;
        String name;
        while(!rows.isAfterLast()){
            id = rows.getInt(rows.getColumnIndexOrThrow("id"));
            name = rows.getString(rows.getColumnIndexOrThrow("name"));
            list.add(new ListItem(id, name));
            rows.moveToNext();
        }
        return list;
    }
}