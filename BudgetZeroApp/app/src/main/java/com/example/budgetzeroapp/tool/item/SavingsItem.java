package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.view.ViewDebtFragment;
import com.example.budgetzeroapp.fragment.view.ViewSavingCatFragment;
import com.example.budgetzeroapp.tool.DBHelper;

import java.util.ArrayList;

public class SavingsItem extends ListItem{
    private boolean type;
    private float objectiveAmount, reachedAmount;

    public SavingsItem(int id, String name, boolean type, float objectiveAmount, float reachedAmount){
        super(id, name);
        this.type = type;
        this.objectiveAmount = objectiveAmount;
        this.reachedAmount = reachedAmount;
    }

    public float getObjective() { return objectiveAmount; }

    public int getProgress(){ return (int) ((-reachedAmount)/objectiveAmount);}

    public static ArrayList<SavingsItem> initSavingsList(boolean type){
        Cursor cat;
        if(type) cat = database.getAllSavingsCat();
        else cat = database.getAllDebts();
        ArrayList<SavingsItem> list = new ArrayList<>();
        int id;
        float objAmount, reachedAmount;
        String name;
        cat.moveToFirst();
        while (!cat.isAfterLast()) {
            id = cat.getInt(cat.getColumnIndexOrThrow("id"));
            name = cat.getString(cat.getColumnIndexOrThrow("name"));
            if(type) objAmount = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.SAV_CAT_COL_MAX_AMOUNT));
            else objAmount = cat.getFloat(cat.getColumnIndexOrThrow(DBHelper.DEBT_COL_TOTAL_AMOUNT));
            reachedAmount = database.getSumFromCat(id, getType(type));
            list.add(new SavingsItem(id, name, type, objAmount, reachedAmount));
            cat.moveToNext();
        }
        return list;
    }
    
    public static int getType(boolean type){
        if(!type) return DBHelper.TYPE_DEBT;
        else return DBHelper.TYPE_SAV;
    }

    public void redirect(){
        if(type) DataBaseFragment.redirect(new ViewSavingCatFragment(),id);
        else DataBaseFragment.redirect(new ViewDebtFragment(),id);
    }
}
