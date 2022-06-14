package com.example.budgetzeroapp.fragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;

//nom, max amount, current amount, ordre de priorité/pourcentage
//liste des savings associés
public class ViewSavingCatFragment extends DataBaseFragment {

    private TextView name, goal, current, prio, perc;
    private TextView [] saveList;
    private LinearLayout saveLayout;
    private String nameVal;
    private float goalVal, currentVal;
    private int prioVal, percVal;
    private String [] saveListVal;

    public ViewSavingCatFragment(){ super(); }
    public ViewSavingCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_saving_cat, parent, false);
        name = view.findViewById(R.id.textViewSaveNameEntry);
        goal = view.findViewById(R.id.textViewSaveGoalEntry);
        current = view.findViewById(R.id.textViewSaveCurrentEntry);
        prio = view.findViewById(R.id.textViewSavePrioEntry);
        perc = view.findViewById(R.id.textViewSavePercEntry);
        saveLayout = view.findViewById(R.id.layoutSaveList);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        //get values
    }

    public void setValues()   {
        name.setText(nameVal);
        goal.setText(String.valueOf(goalVal));
        current.setText(String.valueOf(currentVal));
        prio.setText(String.valueOf(prioVal));
        perc.setText(String.valueOf(percVal));
        for(int i=0;i<saveListVal.length;i++) {
            saveList[i] = new TextView(saveLayout.getContext());
            saveList[i].setText(saveListVal[i]);
            saveLayout.addView(saveList[i]);
        }
    }
}
