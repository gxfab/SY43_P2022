package com.example.budgetzeroapp.fragment.edit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.DateManager;
import com.example.budgetzeroapp.tool.ToolBar;
import com.example.budgetzeroapp.tool.adapter.SimpleListAdapter;
import com.example.budgetzeroapp.tool.item.ListItem;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EditExpenseFragment extends EditDataBaseFragment {

    private Button save, cancel;
    private EditText name, amount;
    private DatePicker date;
    private CheckBox stable;
    private Spinner category;
    private String defaultName;
    private float defaultAmount;
    private int defaultStable;
    private int type, day, month, year, newDay, newMonth, newYear, catID;
    List<ListItem> list;


    public EditExpenseFragment(int id){
        super(id);
        type = 0;
    }


    public EditExpenseFragment(int id, int type){
        super(id);
        this.type=type;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_expense, parent, false);
        save = view.findViewById(R.id.buttonSave);
        cancel = view.findViewById(R.id.buttonCancel);
        name = view.findViewById(R.id.editTextExpName);
        date = view.findViewById(R.id.datePicker);
        category = view.findViewById(R.id.spinner_cat);
        amount = view.findViewById(R.id.editTextExpAmount);
        stable = view.findViewById(R.id.checkBoxExpStable);

        switch(type){
            case DBHelper.TYPE_DEBT: list = ListItem.initList(database.getAllDebts());
                break;
            case DBHelper.TYPE_EXP:list = ListItem.initList(database.getAllExpenseCat());
                break;
            case DBHelper.TYPE_SAV:list = ListItem.initList(database.getAllSavingsCat());
                break;
            case DBHelper.TYPE_INC:list = ListItem.initList(database.getAllIncomeCat());
                break;
        }
        category.setAdapter(new SimpleListAdapter(list));
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
        Date date = ToolBar.getInstance().getDate();
        day = DateManager.dateToDay(date);
        month = DateManager.dateToMonth(date);
        year = DateManager.dateToYear(date);
        defaultAmount = 0;
        defaultStable = 0;
    }

    @Override
    public void changeDefaultValues() {
        Cursor exp = database.getData("select * from "+ DBHelper.EXP_TABLE_NAME+
                " where "+DBHelper.EXP_COL_ID+"="+id);
        exp.moveToFirst();
        if (exp.isAfterLast()) id = 0;
        else {
            defaultName = exp.getString(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_LABEL));
            day = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_DAY));
            month = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_MONTH));
            year = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_YEAR));

            switch(type){
                case DBHelper.TYPE_DEBT: catID = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_DEBT));
                    break;
                case DBHelper.TYPE_EXP:catID = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_EXP));
                    break;
                case DBHelper.TYPE_SAV:catID = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_SAV));
                    break;
                case DBHelper.TYPE_INC:catID = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_ID_INC));
                    break;
            }
            int it = 0;
            for (ListItem i : list){
                if(i.getId() == catID) category.setSelection(it);
                it++;
            }

            defaultAmount = exp.getFloat(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_AMOUNT));
            defaultStable = exp.getInt(exp.getColumnIndexOrThrow(DBHelper.EXP_COL_IS_STABLE));

        }
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
        date.init( year, month , day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                newYear = year;
                newDay = day;
                newMonth = month;
            }
        });
        amount.setText(String.valueOf(defaultAmount));
        stable.setChecked(defaultStable!=0);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            float newAmount = Float.parseFloat(amount.getText().toString());
            if(newAmount <= 0){
                message("Amount must have a positive value");
                return;
            }
            if(type != DBHelper.TYPE_INC) newAmount = -newAmount;
            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
                return;
            }
            if(DateManager.dateToInt(newYear, newMonth, newDay)>DateManager.dateToInt(year, month, day)){
                message("Date can't be posterior to app date");
            }

            int idCat = ((ListItem) category.getSelectedItem()).getId();

            boolean isStable = stable.isChecked();

            if(id == 0) database.insertExpense(newAmount, newName, type, idCat, isStable, newDay, newMonth, newYear);
            else database.updateExpense(id, newAmount, newName, type, idCat, isStable, newDay, newMonth, newYear);

        });
        cancel.setOnClickListener(v -> {
            //Cancel
        });
    }


}