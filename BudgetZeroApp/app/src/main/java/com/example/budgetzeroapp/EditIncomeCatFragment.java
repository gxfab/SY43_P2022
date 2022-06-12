package com.example.budgetzeroapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// nom de la catégorie, bouton save
public class EditIncomeCatFragment extends EditDataBaseFragment{

    private Button save, cancel;
    private String defaultName;
    private EditText name;

    public EditIncomeCatFragment(){ super(); }
    public EditIncomeCatFragment(int id){ super(id); }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.fragment_edit_income_cat, parent, false);
        save = view.findViewById(R.id.saveButton);
        name = view.findViewById(R.id.editTextIncCatName);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
    }

    @Override
    public void changeDefaultValues() {
        defaultName = database.getNameFromID(id,DBHelper.INC_CAT_TABLE_NAME);
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {
            String n = name.getText().toString(), mess;
            if(!n.equals("")) {
                if(id ==0) database.insertIncomeCat(n);
                else database.updateIncomeCat(id, n);
                activity.replaceFragment(new HomeFragment());
                redirect(new ViewIncomeCatFragment(id));
                mess = "Income category '"+n;
                if(id == 0) mess+=" added";
                else mess+=" updated";
                message(mess);
            }else message("No category name");
        });
    }
}
