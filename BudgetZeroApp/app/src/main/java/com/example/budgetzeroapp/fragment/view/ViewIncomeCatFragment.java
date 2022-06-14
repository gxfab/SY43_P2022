package com.example.budgetzeroapp.fragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;

// nom, liste des revenus associés à la catégorie
public class ViewIncomeCatFragment extends DataBaseFragment {

    private TextView name;
    private TextView [] incList;
    private LinearLayout incLayout;
    private String nameVal;
    private String [] incListVal;

    public ViewIncomeCatFragment(){ super(); }
    public ViewIncomeCatFragment(int id){ super(id); }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_view_income_cat, parent, false);
        name = view.findViewById(R.id.textViewIncNameEntry);
        incLayout = view.findViewById(R.id.layoutCatInc);
        getValues();
        setValues();
        return view;
    }

    public void getValues() {
        //Get values
    }

    public void setValues() {
        name.setText(nameVal);
        for(int i=0;i<incListVal.length;i++) {
            incList[i] = new TextView(incLayout.getContext());
            incList[i].setText(incListVal[i]);
            incLayout.addView(incList[i]);
        }
    }
}
