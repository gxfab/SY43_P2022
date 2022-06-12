package com.example.budgetzeroapp;

import android.database.Cursor;

public class ListItem {
    protected int id;
    protected String name;

    public ListItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
