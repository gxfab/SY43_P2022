package com.example.budgetzeroapp.tool;

import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.adapter.BudgetAdapter;
import com.example.budgetzeroapp.tool.adapter.ExpenseAdapter;
import com.example.budgetzeroapp.tool.adapter.ProgressBarAdapter;
import com.example.budgetzeroapp.tool.adapter.SavingsAdapter;
import com.example.budgetzeroapp.tool.adapter.SimpleListAdapter;
import com.example.budgetzeroapp.tool.item.ExpenseItem;
import com.example.budgetzeroapp.tool.item.ListItem;
import com.example.budgetzeroapp.tool.item.CategoryItem;
import com.example.budgetzeroapp.tool.item.SavingsItem;

import java.util.ArrayList;
import java.util.List;

public class ClickableListManager {

    /**public static ListView clickableList(View view, int listViewID,int layout,
     List<ListItem> items, DataBaseFragment frag){
     return clickableList((ListView) view.findViewById(listViewID), new SimpleListAdapter(layout, items), frag);
     }

     public static ListView clickableList(
     ListView list, int layout, List<ListItem> items, DataBaseFragment frag) {
     return clickableList(list, new SimpleListAdapter(layout, items), frag);
     }**/

    public static ListView clickableBudgetList(ListView list, List<CategoryItem> items){
        return clickableList(list, new BudgetAdapter(items));
    }

    public static ListView clickableProgressBarList(ListView list, List<CategoryItem> items){
        return clickableList(list, new ProgressBarAdapter(items));
    }

    public static ListView clickableSavingsList(ListView list, List<SavingsItem> items){
        return clickableList(list, new SavingsAdapter(items));
    }

    public static ListView clickableExpenseList(ListView list, List<ExpenseItem> items){
        return clickableList(list, new ExpenseAdapter(items));
    }

    public static ListView clickableList(ListView list, ArrayAdapter adapter){
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, v, position, id) -> {
            ListItem item = (ListItem) list.getItemAtPosition(position);
            item.redirect();
        });
        return list;
    }

    /**
     public static Spinner clickableSpinner(View view, int SpinnerID,int layout,
     List<ListItem> items, DataBaseFragment frag){
     return clickableSpinner((Spinner) view.findViewById(SpinnerID), new SimpleListAdapter(layout, items), frag);
     }

     public static Spinner clickableSpinner(
     Spinner list, int layout,
     List<ListItem> items, DataBaseFragment frag) {
     return clickableSpinner(list, new SimpleListAdapter(layout, items), frag);
     }

     public static Spinner clickableSpinner(Spinner list, ArrayAdapter<ListItem> adapter, DataBaseFragment frag){
     list.setAdapter(adapter);
     list.setOnItemClickListener((parent, v, position, id) -> {
     ListItem item = (ListItem)list.getItemAtPosition(position);
     frag.setId(item.getId());
     frag.redirect(frag);
     });
     return list;
     }
     **/

}