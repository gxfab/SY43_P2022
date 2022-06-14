package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

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

    public ListItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName(){ return name;}

    public ListItem(Cursor c, int dataType) {
        if(!c.isAfterLast()) {
            if (dataType == DBHelper.TYPE_DEBT) {
                id = c.getInt(c.getColumnIndexOrThrow(DBHelper.DEBT_COL_ID));
                name = c.getString(c.getColumnIndexOrThrow(DBHelper.DEBT_COL_NAME));
            } else if (dataType == DBHelper.TYPE_INC) {
                id = c.getInt(c.getColumnIndexOrThrow(DBHelper.INC_CAT_COL_ID));
                name = c.getString(c.getColumnIndexOrThrow(DBHelper.INC_CAT_COL_NAME));
            } else if (dataType == DBHelper.TYPE_SAV) {
                id = c.getInt(c.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_ID));
                name = c.getString(c.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_NAME));
            } else if (dataType == DBHelper.TYPE_EXP) {
                id = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_ID));
                name = c.getString(c.getColumnIndexOrThrow(DBHelper.EXP_CAT_COL_NAME));
            } else {
                id = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID));
                name = c.getString(c.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL));
            }
        }
    }

    public DataBaseFragment getFrag(int type){
        switch(type){
            case DBHelper.TYPE_EXP: return new ViewExpenseCatFragment(id);
            case DBHelper.TYPE_DEBT: return new ViewDebtFragment(id);
            case DBHelper.TYPE_INC: return new ViewIncomeCatFragment(id);
            case DBHelper.TYPE_SAV: return new ViewSavingCatFragment(id);
            default:
            case DBHelper.TYPE_CASH_FLOW: return new ViewExpenseFragment(id);
        }
    }

    public void redirect(){
        //DataBaseFragment.redirect(new ViewExpenseFragment(id));
    }

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
