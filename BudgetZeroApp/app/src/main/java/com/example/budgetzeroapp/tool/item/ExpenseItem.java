package com.example.budgetzeroapp.tool.item;

import android.database.Cursor;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.view.ViewExpenseFragment;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.DateManager;

import java.util.ArrayList;
import java.util.List;

public class ExpenseItem extends ListItem {
    private final int date;
    private final int type;
    private final String categoryName;
    private final float amount;

    public ExpenseItem(int id, String name, int date, int type, String catName, float amount) {
        super(id, name);
        this.date = date;
        categoryName = catName;
        this.amount = amount;
        this.type = type;
    }

    public static List<ExpenseItem> allExpensesToList(DBHelper database){
        return ExpensesToList(database.getAllExpenses(), database);
    }

    public static List<ExpenseItem> ExpensesToList(Cursor row, DBHelper database){
        List<ExpenseItem> items = new ArrayList<>();
        row.moveToFirst();
        while(!row.isAfterLast()){
            items.add(ExpenseToItem(row, database));
            row.moveToNext();
        }
        return items;
    }
    public static ExpenseItem ExpenseToItem(Cursor c, DBHelper database){
        int day, month, year, type, idCat;

        type = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID));
        day = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_DAY));
        month = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_MONTH));
        year  = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_YEAR));

        String table;
        switch(type){
            default :
            case DBHelper.TYPE_EXP:
                idCat = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_EXP));
                table = DBHelper.EXP_CAT_TABLE_NAME;
                break;
            case DBHelper.TYPE_INC:
                idCat = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_INC));
                table = DBHelper.INC_CAT_TABLE_NAME;
                break;
            case DBHelper.TYPE_SAV:
                idCat = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_SAV));
                table =DBHelper. SAV_CAT_TABLE_NAME;
                break;
            case DBHelper.TYPE_DEBT:
                idCat = c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_DEBT));
                table = DBHelper.DEBT_TABLE_NAME;
                break;
        }
        return new ExpenseItem(
                c.getInt(c.getColumnIndexOrThrow(DBHelper.EXP_COL_ID)),
                c.getString(c.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL)),
                DateManager.dateToInt(year,month, day),
                type, database.getNameFromID(idCat, table),
                c.getFloat(c.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT))
        );
    }

    public void redirect(){
        DataBaseFragment.redirect(new ViewExpenseFragment(id));
    }

    public int getType(){return type;}
    public int getDate(){return date;}
    public String getCategoryName(){ return categoryName; }
    public float getAmount(){ return amount; }


}
