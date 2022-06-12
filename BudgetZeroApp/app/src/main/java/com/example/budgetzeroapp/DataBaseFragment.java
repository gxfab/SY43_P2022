package com.example.budgetzeroapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public abstract class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected MainActivity activity;
    protected Context context;
    protected int id;

    public DataBaseFragment() {
        activity = (MainActivity) getActivity();
        if (isAdded()) {
            context = activity.getApplicationContext();
            database = new DBHelper(context);
        }
        id = 0;
    }

    public DataBaseFragment(int id) {
        activity = (MainActivity) getActivity();
        if (isAdded()) {
            context = activity.getApplicationContext();
            database = new DBHelper(context);
        }
        this.id = id;
    }


    public void message(String message) {
        if (isAdded())
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void redirect(Fragment f) {activity.replaceFragment(f); }

    // Cursor must contain 2 columns : "name" and "id"
    public ListView clickableListNameID(Cursor rows) {
        //TODO
    }

    public Spinner clickableSpinnerNameID(Cursor rows) {
        //TODO
    }

}
