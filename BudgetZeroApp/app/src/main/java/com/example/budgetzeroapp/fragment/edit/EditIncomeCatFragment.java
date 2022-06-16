package com.example.budgetzeroapp.fragment.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.fragment.HomeFragment;
import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.view.ViewIncomeCatFragment;

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
        save = view.findViewById(R.id.buttonSave);
        name = view.findViewById(R.id.editTextIncName);
        return view;
    }

    @Override
    public void initDefaultValues() {
        defaultName = "";
    }

    @Override
    public void changeDefaultValues() {
        defaultName = database.getNameFromID(id, DBHelper.INC_CAT_TABLE_NAME);
    }

    @Override
    public void setDefaultValues() {
        name.setText(defaultName);
    }

    @Override
    public void setButtons() {
        save.setOnClickListener(v -> {

            String newName = name.getText().toString();
            if(newName.equals("")){
                message("Name can't be empty");
            }

            if(id == 0) database.insertIncomeCat(newName);
            else database.updateIncomeCat(id, newName);

        });
        cancel.setOnClickListener(v -> {
            //Cancel
        });
    }
}