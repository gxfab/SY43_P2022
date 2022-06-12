package com.example.budgetzeroapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class EditDataBaseFragment extends DataBaseFragment{

    public EditDataBaseFragment(){ super(); }
    public EditDataBaseFragment(int id){ super(id); }


    public abstract View initView(LayoutInflater inflater, ViewGroup parent);

    public abstract void initDefaultValues();

    public abstract void changeDefaultValues();

    public abstract void setDefaultValues();

    public abstract void setButtons();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = initView(inflater, parent);
        initDefaultValues();
        if (id != 0) changeDefaultValues();
        setDefaultValues();
        setButtons();
        return view;
    }
}
