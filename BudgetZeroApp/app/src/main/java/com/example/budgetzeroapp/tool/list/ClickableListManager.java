package com.example.budgetzeroapp.tool.list;

import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.list.adapter.ProgressBarAdapter;
import com.example.budgetzeroapp.tool.list.adapter.SimpleListAdapter;
import com.example.budgetzeroapp.tool.list.item.ListItem;
import com.example.budgetzeroapp.tool.list.item.ProgressBarItem;

import java.util.ArrayList;
import java.util.List;

public class ClickableListManager {

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



    public static ListView clickableList(View view, int listViewID,int layout,
                                  List<ListItem> items, DataBaseFragment frag){
        return clickableList((ListView) view.findViewById(listViewID), new SimpleListAdapter(layout, items), frag);
    }

    public static ListView clickableList(
            ListView list, int layout, List<ListItem> items, DataBaseFragment frag) {
        return clickableList(list, new SimpleListAdapter(layout, items), frag);
    }

    public static ListView clickableList(ListView list, ArrayAdapter<ListItem> adapter, DataBaseFragment frag){
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, v, position, id) -> {
            ListItem item = (ListItem)list.getItemAtPosition(position);
            frag.setId(item.getId());
            frag.redirect(frag);
        });
        return list;
    }

    public static ListView clickableListPB(ListView list, List<ProgressBarItem> items){
        list.setAdapter(new ProgressBarAdapter(items));
        list.setOnItemClickListener((parent, v, position, id) -> {
            ProgressBarItem item = (ProgressBarItem) list.getItemAtPosition(position);
            DataBaseFragment frag = item.getFragment();
            frag.setId(item.getId());
            frag.redirect(frag);
        });
        return list;
    }

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

}
