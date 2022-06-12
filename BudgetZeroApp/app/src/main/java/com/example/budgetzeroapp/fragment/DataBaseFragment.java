package com.example.budgetzeroapp.fragment;

import android.content.Context;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.list.item.ListItem;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.tool.list.adapter.SimpleListAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected MainActivity activity;
    protected Context context;
    protected int id;

    public DataBaseFragment() { this(0);}

    public DataBaseFragment(int id) {
        activity = (MainActivity) getActivity();
        if (isAdded()) {
            context = activity.getApplicationContext();
            database = new DBHelper(context);
        }
        this.id = id;
    }

    public void setId(int id) {this.id = id;}

    public void message(String message) {
        if (isAdded())
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void redirect(Fragment f) {activity.replaceFragment(f); }
}
