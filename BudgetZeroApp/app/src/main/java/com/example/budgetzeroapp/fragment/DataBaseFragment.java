package com.example.budgetzeroapp.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.budgetzeroapp.AppContext;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.MainActivity;

public class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected int id;

    public DataBaseFragment() { this(0);}

    public DataBaseFragment(int id) {
        database = new DBHelper(AppContext.getContext());
        this.id = id;
    }

    public void setId(int id) {this.id = id;}

    public void message(String message) {
        if (isAdded())
            Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void redirect(Fragment f) {MainActivity.getActivity().replaceFragment(f); }
}
